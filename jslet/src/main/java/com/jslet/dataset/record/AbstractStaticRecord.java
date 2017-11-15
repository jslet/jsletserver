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
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;

/**
 * The abstract class for static record.
 *
 * @author Tony Tong
 */
public abstract class AbstractStaticRecord extends AbstractDataRecord implements Serializable {

	private static final long serialVersionUID = 1949242965928471869L;

	private String rs;

	private RecordAuditLog auditLog;

	@Override
	public final String getRs() {
		return this.rs;
	}

	@Override
	public final void setRs(String rs) {
		this.rs = rs;
	}

	/**
	 * 设置客户端变更日志属性，内部使用，外部勿用。
	 *
	 * @param _audit_ 客户端变更日志属性。
	 */
	public final void setAl(Map<String, Map<String, String>> auditLog) {
		if (this.auditLog == null) {
			this.auditLog = new RecordAuditLog();
		}
		this.auditLog.setOriginalLog(auditLog);
	}

	@Override
	public RecordAuditLog auditLog() {
		return this.auditLog;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(String fieldName) throws RuntimeException {
		try {
			return (T) BeanUtilsBean.getInstance().getProperty(this, fieldName);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException("Cannot get value of field: " + fieldName);
		}
	}

	@Override
	public <T> DataRecord setValue(String fieldName, T value) throws RuntimeException {
		try {
			BeanUtilsBean.getInstance().setProperty(this, fieldName, value);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("Cannot set value of field: " + fieldName);
		}
		return this;
	}

}
