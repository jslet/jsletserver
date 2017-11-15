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
import java.util.Map;
import java.util.Set;

/**
 * Record audit log to log the field old value and new value.
 *
 * @author Tony Tong
 */
public class RecordAuditLog implements Serializable {

	private static final long serialVersionUID = -3544165015500694021L;

	private static final String OLD = "o";

	private static final String NEW = "n";

	private static final String LABEL = "l";

	private Map<String, Map<String, String>> auditLog;

	/**
	 * Set the original log。
	 *
	 * @param auditLog Audit log。
	 */
	void setOriginalLog(Map<String, Map<String, String>> auditLog) {
		this.auditLog = auditLog;
	}

	/**
	 * Identify wheather the audit log is empty or not.
	 *
	 * @return True - the audit log is empty, false - otherwise.
	 */
	public boolean isEmpty() {
		return this.auditLog == null || this.auditLog.keySet().isEmpty();
	}

	/**
	 * Get the audit log field names。
	 *
	 * @return field names。
	 */
	public Set<String> getAuditLogFields() {
		if (this.auditLog != null) {
			return this.auditLog.keySet();
		}
		return null;
	}

	/**
	 * Get the old value of the specified field.
	 *
	 * @param fieldName Field name.
	 * @return Old value.
	 */
	public String getOldValue(String fieldName) {
		Map<String, String> fieldLog = getFieldLog(fieldName);
		if (fieldLog != null) {
			return fieldLog.get(OLD);
		}
		return null;
	}

	/**
	 * Get the new value of the specified field.
	 *
	 * @param fieldName Field name.
	 * @return New value.
	 */
	public String getNewValue(String fieldName) {
		Map<String, String> fieldLog = getFieldLog(fieldName);
		if (fieldLog != null) {
			return fieldLog.get(NEW);
		}
		return null;
	}

	/**
	 * Get field label.
	 *
	 * @param fieldCode Field name.
	 * @return Field label.
	 */
	public String getFieldLabel(String fieldCode) {
		Map<String, String> fieldLog = getFieldLog(fieldCode);
		if (fieldLog != null) {
			return fieldLog.get(LABEL);
		}
		return null;
	}

	private Map<String, String> getFieldLog(String fieldName) {
		if (this.auditLog == null) {
			return null;
		}

		return this.auditLog.get(fieldName);
	}

	@Override
	public String toString() {
		if (this.auditLog == null) {
			return "No audit Log!";
		}
		StringBuilder sb = new StringBuilder();
		Map<String, String> fieldLog;
		boolean notFirst = false;
		for (String fieldName : this.auditLog.keySet()) {
			if (notFirst) {
				sb.append(", ");
			}
			sb.append(fieldName).append(": ");
			fieldLog = this.auditLog.get(fieldName);
			sb.append(fieldLog.get(OLD)).append(" -> ").append(fieldLog.get(NEW));
			notFirst = true;
		}
		return sb.toString();
	}

}
