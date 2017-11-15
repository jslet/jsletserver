/*
 * Copyright 2017-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jslet.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.jslet.dataset.record.DynamicRecord;
import com.jslet.utils.DateUtil;

/**
 * JSON converter that converts class RequestExchangeEntity.
 *
 * @author Tony Tong。
 */
public class FastJsonRequestMessageConverter extends FastJsonHttpMessageConverter {
	private static Logger logger = LoggerFactory.getLogger(FastJsonRequestMessageConverter.class);

	private final static String DATATYPE = "dataType";

	private final static String DATATYPE_DATE = "D";

	private final static String DATATYPE_DETAIL = "V";

	private final static String DETAILDATASET = "detailDataset";

	private DatasetClassNameHandler datasetClassNameHandler = null;

	/**
	 * Get the handler that to get the entity class name with dataset name.
	 *
	 * @return datasetClassNameHandler Handler
	 */
	public final DatasetClassNameHandler getDatasetClassNameHandler() {
		return datasetClassNameHandler;
	}

	/**
	 * Set the handler that to get the entity class name with dataset name.
	 *
	 * @param datasetClassNameHandler Handler
	 */
	public final void setDatasetClassNameHandler(DatasetClassNameHandler datasetClassNameHandler) {
		this.datasetClassNameHandler = datasetClassNameHandler;
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return JsletRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return false;
	}

	@Override
	protected boolean canWrite(MediaType mediaType) {
		return false;
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		JsletRequest exchgEntity = (JsletRequest) super.readInternal(JsletRequest.class, inputMessage);

		List<?> main = exchgEntity.getMain();
		Map<String, DatasetMeta> metas = exchgEntity.getMeta();
		main = this.convertToBean("main", main, metas);
		exchgEntity.setMain(main);
		Map<String, List<?>> others = exchgEntity.getOthers();
		if (others == null || others.isEmpty()) {
			return exchgEntity;
		}
		List records;
		Set<String> datasetNames = others.keySet();
		for (String dsName : datasetNames) {
			records = others.get(dsName);
			records = this.convertToBean(dsName, records, metas);
			others.put(dsName, records);
		}
		return exchgEntity;
	}

	@SuppressWarnings({ "rawtypes" })
	private List convertToBean(String dsName, List records, Map<String, DatasetMeta> metas) {
		Boolean isDynamic = false;
		String recordClass = null;
		if (metas == null) {
			isDynamic = true;
		} else {
			DatasetMeta meta = metas.get(dsName);
			if (meta == null) {
				isDynamic = true;
			} else {
				recordClass = meta.getRecordClass();
			}
		}
		if (!isDynamic) {
			if (StringUtils.isEmpty(recordClass) && this.datasetClassNameHandler != null) {
				recordClass = this.datasetClassNameHandler.getClassName(dsName);
			}
			if (recordClass == null) {
				isDynamic = true;
			}
		}
		List convertedResult = null;
		if (isDynamic) {
			convertedResult = this.convertDynamicEntity(dsName, records, metas);
		} else {
			convertedResult = this.convertStaticEntity(dsName, recordClass, records, metas);
		}
		return convertedResult;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List convertStaticEntity(String dsName, String recordClass, List records, Map<String, DatasetMeta> metas) {
		if (records == null || records.isEmpty()) {
			return null;
		}
		JSONObject recObj;
		Object newRec;
		List result = new ArrayList();
		String dataType;
		Set<String> fields;
		Object fldValue;
		Map<String, Object> fldObj;
		DatasetMeta meta = metas.get(dsName);
		Map<String, Map<String, Object>> fieldMetaMap = this.getFieldMetaMap(meta.getFields());
		for (int i = 0, len = records.size(); i < len; i++) {
			recObj = (JSONObject) records.get(i);
			if (fieldMetaMap != null) {
				fields = recObj.keySet();
				for (String fldName : fields) {
					fldObj = fieldMetaMap.get(fldName);
					if (fldObj == null) {
						continue;
					}
					fldValue = recObj.get(fldName);
					dataType = fldObj.get(DATATYPE).toString().trim();
					// 日期
					if (DATATYPE_DATE.equalsIgnoreCase(dataType)) {
						recObj.put(fldName, this.convertDate(fldValue));
						continue;
					}
					// 明细数据集
					if (DATATYPE_DETAIL.equalsIgnoreCase(dataType)) {
						String dtlDsName = (String) fldObj.get(DETAILDATASET);
						fldValue = this.convertToBean(dtlDsName, (List) fldValue, metas);
					}
					recObj.put(fldName, fldValue);
				}
			}
			try {
				newRec = JSON.toJavaObject(recObj, Class.forName(recordClass));
			} catch (ClassNotFoundException e) {
				logger.error("Class NOT found: " + recordClass, e);
				newRec = null;
			}
			result.add(newRec);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	private List convertDynamicEntity(String dsName, List records, Map<String, DatasetMeta> metas) {
		if (records == null) {
			return null;
		}
		DatasetMeta meta = metas != null ? metas.get(dsName) : null;
		Map<String, Map<String, Object>> fieldMetaMap = meta != null ? this.getFieldMetaMap(meta.getFields()) : null;
		List<DynamicRecord> result = new ArrayList<DynamicRecord>();
		JSONObject record;
		String dataType;
		Set<String> fields;
		Object fldValue;
		Map<String, Object> fldObj;
		for (Object item : records) {
			record = (JSONObject) item;
			fields = record.keySet();
			DynamicRecord entity = new DynamicRecord();
			result.add(entity);
			for (String fldName : fields) {
				fldValue = record.get(fldName);
				if (fldValue == null) {
					entity.setValue(fldName, null);
					continue;
				}
				fldObj = null;
				if (fieldMetaMap != null) {
					fldObj = fieldMetaMap.get(fldName);
				}
				if (fldObj == null) {
					entity.setValue(fldName, fldValue);
					continue;
				}
				dataType = fldObj.get(DATATYPE).toString().trim();
				// 日期
				if (DATATYPE_DATE.equalsIgnoreCase(dataType)) {
					entity.setValue(fldName, this.convertDate(fldValue));
					continue;
				}
				// 明细数据集
				if (DATATYPE_DETAIL.equalsIgnoreCase(dataType)) {
					String dtlDsName = (String) fldObj.get(DETAILDATASET);
					fldValue = this.convertToBean(dtlDsName, (List) fldValue, metas);
				}
				entity.setValue(fldName, fldValue);
			}
		}
		return result;
	}

	private Map<String, Map<String, Object>> getFieldMetaMap(List<Map<String, Object>> meta) {
		if (meta == null) {
			return null;
		}
		String fldName;
		Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();
		for (Map<String, Object> metaObj : meta) {
			fldName = (String) metaObj.get("name");
			if (fldName != null) {
				result.put(fldName, metaObj);
			}
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object convertDate(Object date) {
		if (date instanceof String) {
			return DateUtil.parseISODate((String) date);
		}
		if (date instanceof String[]) {
			String[] strDates = (String[]) date;
			Date[] dates = new Date[strDates.length];
			for (int i = 0, len = strDates.length; i < len; i++) {
				dates[i] = DateUtil.parseISODate(strDates[i]);
			}
			return dates;
		}

		if (date instanceof List) {
			List dates = (List) date;

			for (int i = 0, len = dates.size(); i < len; i++) {
				dates.set(i, DateUtil.parseISODate((String) dates.get(i)));
			}
			return dates;
		}

		return date;
	}
}
