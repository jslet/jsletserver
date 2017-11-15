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

import java.util.List;
import java.util.Map;

/**
 * The dataset meta that is sent/received the client.
 *
 * @author Tony Tong
 */
public class DatasetMeta {
	private String name;

	private String recordClass;

	private List<Map<String, Object>> fields;

	/**
	 * Get the dataset name.
	 *
	 * @return name Dataset name.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Set the dataset name.
	 *
	 * @param name Dataset name.
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the dataset record class name.
	 *
	 * @return recordClass Record class name.
	 */
	public final String getRecordClass() {
		return recordClass;
	}

	/**
	 * Set the dataset record class name.
	 *
	 * @param recordClass Record class name
	 */
	public final void setRecordClass(String recordClass) {
		this.recordClass = recordClass;
	}

	/**
	 * Get the field meta list of the dataset.
	 *
	 * @return fields Field meta list.
	 */
	public final List<Map<String, Object>> getFields() {
		return fields;
	}

	/**
	 * Set the field meta list of the dataset.
	 *
	 * @param fields Field meta list.
	 */
	public final void setFields(List<Map<String, Object>> fields) {
		this.fields = fields;
	}

}
