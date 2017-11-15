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

import java.util.Map;

/**
 * Jslet request object that is used to receive the client data。
 *
 * @author Tony Tong
 */
public class JsletRequest extends AbstractExchangeEntity {

	private int pageNo;

	private int pageSize;

	private Map<String, Object> criteria;

	/**
	 * Get page number.
	 *
	 * @return pageNo Page number.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * Set page number.
	 *
	 * @param pageNo Page number.
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * Get page size.
	 *
	 * @return pageSize Page size.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Set page size.
	 *
	 * @param pageSize Page size.
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * Get query criteria，key for field name, value for field value.
	 *
	 * @return criteria Query criteria.
	 */
	public Map<String, Object> getCriteria() {
		return criteria;
	}

	/**
	 * Set query criteria，key for field name, value for field value.
	 *
	 * @param criteria Query criteria.
	 */
	public void setCriteria(Map<String, Object> criteria) {
		this.criteria = criteria;
	}

}
