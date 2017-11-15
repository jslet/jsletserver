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

import java.io.IOException;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * The converter for convert class ResponseExchangeEntity to JSON.
 *
 * @author Tony Tong。
 */
public class FastJsonResponseMessageConverter extends FastJsonHttpMessageConverter {

	@Override
	protected boolean supports(Class<?> clazz) {
		return JsletResponse.class.isAssignableFrom(clazz);
	}

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return false;
	}

	@Override
	protected boolean canRead(MediaType mediaType) {
		return false;
	}

	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		super.writeInternal(obj, outputMessage);
	}

}
