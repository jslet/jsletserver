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

package com.jslet.dataset.record;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Dynamic record which fields are not specified.
 *
 * @author Tony Tong
 */
public class DynamicRecord extends AbstractDataRecord implements DataRecord, Map<String, Object>, Serializable {

	private static final long serialVersionUID = -2139409380339731912L;

	private static final String DATA_STATE = "rs";

	private static final String AUDITLOG = "al";

	private Map<String, Object> record = new HashMap<String, Object>();

	private RecordAuditLog auditLog;

	public DynamicRecord() {
	}

	/**
	 * Create a dynamic record with another dynamic record.
	 *
	 * @param record
	 */
	public DynamicRecord(DynamicRecord record) {
		this.record = record.getRecord();
	}

	/**
	 * Create a dynamic record with a map object.
	 *
	 * @param record Map object.
	 * @param defaultRecordStatus Record status.
	 */
	public DynamicRecord(Map<String, Object> record, RecordStatus defaultRecordStatus) {
		this.record = record;
		if (this.getRs() == null) {
			this.setRs(defaultRecordStatus.getValue());
		}
	}

	/**
	 * Get record object.
	 *
	 * @return Record
	 */
	public Map<String, Object> getRecord() {
		return this.record;
	}

	/**
	 * Get field value。
	 *
	 * @param <T>
	 *
	 * @param fieldName Field name.
	 * @return Field value.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getValue(String fieldName) {
		return (T) this.record.get(fieldName);
	}

	/**
	 * Set field value.
	 *
	 * @param <T>
	 *
	 * @param fieldName Field name.
	 * @param value Field value.
	 * @return Dynamic record object.
	 */
	@Override
	public <T> DynamicRecord setValue(String fieldName, T value) {
		this.record.put(fieldName, value);
		return this;
	}

	@Override
	public String getRs() {
		return (String) this.record.get(DATA_STATE);
	}

	@Override
	public void setRs(String rs) {
		this.record.put(DATA_STATE, rs);
	}

	@Override
	public RecordAuditLog auditLog() {
		@SuppressWarnings("unchecked")
		Map<String, Map<String, String>> auditLog = (Map<String, Map<String, String>>) this.record.get(AUDITLOG);
		if (this.auditLog == null) {
			this.auditLog = new RecordAuditLog();
			this.auditLog.setOriginalLog(auditLog);
		}
		return this.auditLog;
	}

	@Override
	public int size() {
		return this.record.size();
	}

	@Override
	public boolean isEmpty() {
		return this.record.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return this.record.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return this.record.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return this.record.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		return this.record.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return this.record.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		this.record.putAll(m);
	}

	@Override
	public void clear() {
		this.record.clear();
	}

	@Override
	public Set<String> keySet() {
		return this.record.keySet();
	}

	@Override
	public Collection<Object> values() {
		return this.record.values();
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return this.record.entrySet();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return this.record.toString();
	}

}
