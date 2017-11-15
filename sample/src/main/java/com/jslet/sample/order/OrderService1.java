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

package com.jslet.sample.order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jslet.dataset.record.DynamicRecord;

/**
 * Order Service
 *
 * @author Tony Tong
 */
@Service
public class OrderService1 {
	private static List<DynamicRecord> allOrders = new ArrayList<DynamicRecord>();

	static {
		Calendar cal = Calendar.getInstance();
		DynamicRecord r, d;
		r = new DynamicRecord();
		r.setValue("saleid", "S001");
		r.setValue("customer", "01");
		r.setValue("saledate", cal.getTime());
		r.setValue("amount", 5000);
		List<DynamicRecord> details = new ArrayList<DynamicRecord>();
		d = new DynamicRecord();
		d.setValue("seqno", 1);
		d.setValue("product", "PC");
		d.setValue("qty", 12);
		d.setValue("price", 1000);
		details.add(d);

		d = new DynamicRecord();
		d.setValue("seqno", 2);
		d.setValue("product", "KB");
		d.setValue("qty", 100);
		d.setValue("price", 89);
		details.add(d);

		r.setValue("details", details);
		allOrders.add(r);

		r = new DynamicRecord();
		r.setValue("saleid", "S002");
		r.setValue("customer", "02");
		r.setValue("saledate", cal.getTime());
		r.setValue("amount", 6000);
		allOrders.add(r);
	}

	public List<DynamicRecord> findAll() {
		return allOrders;
	}

	public List<DynamicRecord> save(List<DynamicRecord> employees) {
		for (DynamicRecord rec : employees) {
			if (rec.inserted()) {
				allOrders.add(rec);
			} else if (rec.updated()) {
				DynamicRecord emp;
				String oldId;
				for (int i = 0, len = allOrders.size(); i < len; i++) {
					emp = allOrders.get(i);
					oldId = emp.getValue("saleid");
					if (oldId.equalsIgnoreCase((String) rec.getValue("saleid"))) {
						allOrders.set(i, rec);
						break;
					}
				}
			} else if (rec.deleted()) {
				DynamicRecord emp;
				String oldId;
				for (int i = 0, len = allOrders.size(); i < len; i++) {
					emp = allOrders.get(i);
					oldId = emp.getValue("id");
					if (oldId.equalsIgnoreCase((String) rec.getValue("id"))) {
						allOrders.remove(i);
						break;
					}
				}
			}

		}
		return employees;
	}
}
