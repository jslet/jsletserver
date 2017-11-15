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

/**
 * Data Record.
 *
 * @author Tony Tong
 */
public interface DataRecord {
	/**
	 * Get the record status.
	 *
	 * @return Record status
	 */
	String getRs();

	/**
	 * Set the record status.
	 *
	 * @param rs Record status.
	 */
	void setRs(String rs);

	/**
	 * Get audit log.
	 *
	 * @return Audit log。
	 */
	RecordAuditLog auditLog();

	/**
	 * Identify if this record is inserted or not.
	 *
	 * @return True - inserted record，false - otherwise.
	 */
	boolean inserted();

	/**
	 * Identify if this record is updated or not.
	 *
	 * @return True - updated record，false - otherwise.
	 */
	boolean updated();

	/**
	 * Identify if this record is deleted or not.
	 *
	 * @return True - deleted record，false - otherwise.
	 */
	boolean deleted();

	/**
	 * Identify if this record is unchanged or not.
	 *
	 * @return True - unchanged，false - otherwise.
	 */
	boolean unchanged();

	/**
	 * Get the specified field value。
	 *
	 * @param <T> Data type of field.
	 *
	 * @param fieldName Field name.
	 * @return Field value.
	 */
	<T> T getValue(String fieldName);

	/**
	 * Get the specified field value。
	 *
	 * @param <T> Data type of field.
	 *
	 * @param fieldName Field name.
	 * @param value Field value.
	 * @return
	 */
	<T> DataRecord setValue(String fieldName, T value);
}
