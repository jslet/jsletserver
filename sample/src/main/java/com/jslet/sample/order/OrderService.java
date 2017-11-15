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

/**
 * Order Service
 *
 * @author Tony Tong
 */
@Service
public class OrderService {
	private static List<Order> allOrders = new ArrayList<Order>();

	static {
		Calendar cal = Calendar.getInstance();
		Order r;
		OrderItem d;
		r = new Order();
		r.setOrderNo("S001");
		r.setCustomer("01");
		r.setOrderDate(cal.getTime());
		List<OrderItem> details = new ArrayList<OrderItem>();
		d = new OrderItem();
		d.setSeqno(1);
		d.setProduct("PC");
		d.setQty(12);
		d.setPrice(1000d);
		details.add(d);

		d = new OrderItem();
		d.setSeqno(1);
		d.setProduct("KB");
		d.setQty(10);
		d.setPrice(89.5d);
		details.add(d);

		r.setValue("items", details);
		allOrders.add(r);

		r = new Order();
		r.setOrderNo("S002");
		r.setCustomer("02");
		r.setOrderDate(cal.getTime());
		allOrders.add(r);
	}

	public List<Order> findAll() {
		return allOrders;
	}

	public List<Order> save(List<Order> orders) {
		for (Order rec : orders) {
			if (rec.inserted()) {
				allOrders.add(rec);
			} else if (rec.updated()) {
				Order emp;
				String oldId;
				for (int i = 0, len = allOrders.size(); i < len; i++) {
					emp = allOrders.get(i);
					oldId = emp.getOrderNo();
					if (oldId.equalsIgnoreCase(rec.getOrderNo())) {
						allOrders.set(i, rec);
						break;
					}
				}
			} else if (rec.deleted()) {
				Order emp;
				String oldId;
				for (int i = 0, len = allOrders.size(); i < len; i++) {
					emp = allOrders.get(i);
					oldId = emp.getOrderNo();
					if (oldId.equalsIgnoreCase(rec.getOrderNo())) {
						allOrders.remove(i);
						break;
					}
				}
			}

		}
		return orders;
	}
}
