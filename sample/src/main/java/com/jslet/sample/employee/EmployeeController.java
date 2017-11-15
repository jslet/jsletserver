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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jslet.converter.JsletRequest;
import com.jslet.converter.JsletResponse;
import com.jslet.dataset.record.DynamicRecord;

/**
 * Employee Controller
 *
 * @author Tony Tong
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/find")
	public JsletResponse find(@RequestBody JsletRequest request) {
		List<DynamicRecord> employees = this.employeeService.findAll();

		// 返回查询结果到前端
		return new JsletResponse(employees);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/save")
	public JsletResponse save(@RequestBody JsletRequest request) {
		// 获取前端修改的数据
		List<DynamicRecord> employees = (List<DynamicRecord>) request.getMain();

		employees = this.employeeService.save(employees);

		// 如果后端修改数据，则需将修改后的数据返回给前端同步
		return new JsletResponse(employees);
		// 如果数据没有被修改，直接返回null即可
		// return null;
	}
}
