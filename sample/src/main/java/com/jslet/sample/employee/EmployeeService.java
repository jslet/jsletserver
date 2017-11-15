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

package com.jslet.sample.employee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslet.dataset.record.DynamicRecord;
import com.jslet.sample.auditlog.AuditLogService;

/**
 * Employee Service
 *
 * @author Tony Tong
 */
@Service
public class EmployeeService {
	private static List<DynamicRecord> allEmployees = new ArrayList<DynamicRecord>();

	static {
		Calendar cal = Calendar.getInstance();
		DynamicRecord r = new DynamicRecord();
		r.setValue("id", "001");
		r.setValue("name", "Tom");
		r.setValue("gender", "M");
		cal.set(1980, 10, 1);
		r.setValue("birthday", cal.getTime());
		r.setValue("salary", 5000);
		allEmployees.add(r);

		r = new DynamicRecord();
		r.setValue("id", "002");
		r.setValue("name", "Jerry");
		r.setValue("gender", "F");
		cal.set(1982, 5, 11);
		r.setValue("birthday", cal.getTime());
		r.setValue("salary", 8000);
		allEmployees.add(r);
	}

	@Autowired
	private AuditLogService auditLogService;

	public List<DynamicRecord> findAll() {
		return allEmployees;
	}

	public List<DynamicRecord> save(List<DynamicRecord> employees) {
		for (DynamicRecord rec : employees) {
			if (rec.inserted()) {
				allEmployees.add(rec);
			} else if (rec.updated()) {
				this.auditLogService.saveAuditLog("Employee", "id", rec.auditLog(), "Jerry");
				DynamicRecord emp;
				String oldId;
				for (int i = 0, len = allEmployees.size(); i < len; i++) {
					emp = allEmployees.get(i);
					oldId = emp.getValue("id");
					if (oldId.equalsIgnoreCase((String) rec.getValue("id"))) {
						allEmployees.set(i, rec);
						break;
					}
				}
			} else if (rec.deleted()) {
				DynamicRecord emp;
				String oldId;
				for (int i = 0, len = allEmployees.size(); i < len; i++) {
					emp = allEmployees.get(i);
					oldId = emp.getValue("id");
					if (oldId.equalsIgnoreCase((String) rec.getValue("id"))) {
						allEmployees.remove(i);
						break;
					}
				}
			}

		}
		return employees;
	}
}
