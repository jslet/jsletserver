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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The base class is used to send/receive the data to/from Jslet framework.
 *
 * @author Tony Tong
 */
public abstract class AbstractExchangeEntity {

	public final static String MAINDATASET = "main";

	private String mainName;

	private List<?> main;

	private Map<String, List<?>> others;

	private Map<String, DatasetMeta> meta;

	private Object extraData;

	/**
	 * Get the main dataset name.
	 *
	 * @return mainName Main dataset name.
	 */
	public final String getMainName() {
		return mainName;
	}

	/**
	 * Set the main dataset name.
	 *
	 * @param mainName Main dataset name.
	 */
	public final void setMainName(String mainName) {
		this.mainName = mainName;
	}

	/**
	 * Get the records of the main dataset.
	 *
	 * @return The records of the main dataset.
	 */
	public final List<?> getMain() {
		return main;
	}

	/**
	 * Set the records of the main dataset.
	 *
	 * @param main The records of the main dataset.
	 */
	public final void setMain(List<?> main) {
		this.main = main;
	}

	/**
	 * Get the records of other datasets.
	 *
	 * @return others Other datasets' records.
	 */
	public final Map<String, List<?>> getOthers() {
		return others;
	}

	/**
	 * Set the records of other datasets.
	 *
	 * @param others Other datasets' records.
	 */
	public final void setOthers(Map<String, List<?>> others) {
		this.others = others;
	}

	/**
	 * Get the dataset meta of the main dataset.
	 *
	 * @return meta Dataset meta.
	 */
	public final DatasetMeta mainMeta() {
		if (meta == null || meta.isEmpty()) {
			return null;
		}
		return meta.get(MAINDATASET);
	}

	/**
	 * Set the dataset meta of the main dataset.
	 *
	 * @param mainMeta Dataset meta.
	 */
	public final void mainMeta(DatasetMeta mainMeta) {
		if (meta == null) {
			this.meta = new LinkedHashMap<String, DatasetMeta>();
		}
		this.meta.put(MAINDATASET, mainMeta);
	}

	/**
	 * Get the dataset meta of all datasets.
	 *
	 * @return meta Dataset meta.
	 */
	public final Map<String, DatasetMeta> getMeta() {
		return meta;
	}

	/**
	 * Set the dataset meta of all datasets.
	 *
	 * @param meta Dataset meta.
	 */
	public final void setMeta(Map<String, DatasetMeta> meta) {
		this.meta = meta;
	}

	/**
	 * Get the extra data.
	 *
	 * @return extraData Extra data.
	 */
	public final Object getExtraData() {
		return extraData;
	}

	/**
	 * Set the extra data.
	 *
	 * @param extraData Extra data.
	 */
	public final void setExtraData(Object extraData) {
		this.extraData = extraData;
	}

}
