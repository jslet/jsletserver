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

/**
 * The handler that to get the qualified entity class name with dataset name.
 *
 * @author Tony Tong
 */
public interface DatasetClassNameHandler {
	/**
	 * Get the entity class name.
	 *
	 * @param datasetName Dataset name which is from the client, like: "employee"
	 * @return Entity class name, like: "com.foo.crm.Employee".
	 */
	String getClassName(String datasetName);
}
