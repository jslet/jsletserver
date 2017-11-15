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

/**
 * Jslet response object that is used to send data to the client。
 *
 * @author Tony Tong
 */
public class JsletResponse extends AbstractExchangeEntity {

	private int pageCount;

	private String code;

	private String message;

	private String info;

	public JsletResponse() {

	}

	public JsletResponse(List<?> main) {
		this.setMain(main);
	}

	/**
	 * Get page count.
	 *
	 * @return pageCount Page count.
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * Set page count.
	 *
	 * @param pageCount Page count.
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * Get response status code: "0" for success, other for error.
	 *
	 * @return Response status code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Get response status: "0" for success, other for error.
	 *
	 * @param code Response status code.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Get error message.
	 *
	 * @return Error message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set error message.
	 *
	 * @param message Error message.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Get extra information.
	 *
	 * @return info Extra information.
	 */
	public final String getInfo() {
		return info;
	}

	/**
	 * Set extra information.
	 *
	 * @param info Extra information.
	 */
	public final void setInfo(String info) {
		this.info = info;
	}

}
