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

package com.jslet.sample.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jslet.converter.JsletRequest;
import com.jslet.converter.JsletResponse;

/**
 * User Controller
 *
 * @author Tony Tong
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public JsletResponse findAll(@RequestBody JsletRequest jsletRequest) {
		// 获取前端传入的查询条件
		Map<String, Object> criteria = jsletRequest.getCriteria();

		List<User> users = userService.findUserList(criteria);
		// 返回数据
		return new JsletResponse(users);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsletResponse save(@RequestBody JsletRequest jsletRequest) {
		// 获取前端修改的数据
		List<User> users = (List<User>) jsletRequest.getMain();

		userService.save(users);

		// 如果后端修改数据，则需将修改后的数据返回给前端同步
		return new JsletResponse(users);
		// 如果数据没有被修改，直接返回null即可
		// return null;
	}
}
