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

package com.jslet.sample.auditlog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Service;

import com.jslet.dataset.record.DynamicRecord;
import com.jslet.dataset.record.RecordAuditLog;

/**
 * Audit Log Service
 *
 * @author Tony Tong
 */
@Service
public class AuditLogService {

	private final static List<DynamicRecord> auditLogs = new ArrayList<DynamicRecord>();

	public void saveAuditLog(String name, String keyValue, RecordAuditLog auditLog, String user) {
		DynamicRecord log = new DynamicRecord();
		Set<String> fields = auditLog.getAuditLogFields();
		log.setValue("boName", name);
		log.setValue("keyValue", keyValue);
		StringBuilder sb = new StringBuilder();
		String oldValue, newValue;
		for (String fldName : fields) {
			sb.append(auditLog.getFieldLabel(fldName));
			sb.append(": ");
			oldValue = auditLog.getOldValue(fldName);
			oldValue = StringUtils.isEmpty(oldValue) ? "(空)" : oldValue;
			sb.append(oldValue);
			sb.append(" -> ");
			newValue = auditLog.getNewValue(fldName);
			newValue = StringUtils.isEmpty(newValue) ? "(空)" : newValue;
			sb.append(newValue);
			sb.append("| ");
		}
		log.setValue("content", sb.toString());
		log.setValue("user", user);
		log.setValue("updateTime", Calendar.getInstance().getTime());
		auditLogs.add(log);
	}

	public List<DynamicRecord> queryAuditLog(String boName) {
		if (StringUtils.isEmpty(boName)) {
			return auditLogs;
		}
		List<DynamicRecord> result = new ArrayList<DynamicRecord>();
		String name;
		for (DynamicRecord log : auditLogs) {
			name = log.getValue("boName");
			if (name.equalsIgnoreCase(boName)) {
				result.add(log);
			}
		}
		return result;
	}
}
