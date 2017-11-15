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

package com.jslet.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Date util class.
 *
 * @author Tony Tong
 */
public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static String ISO_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public final static String FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";

	/**
	 * Convert ISODate string to Date object.
	 *
	 * @param dateStr ISODate String
	 * @return Date object
	 */
	public static Date parseISODate(String dateStr) {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		if (dateStr.length() < 11) {
			dateStr += " 00:00:00";
		}
		SimpleDateFormat isoDateFormat = new SimpleDateFormat(ISO_DATE_FORMAT);
		try {
			return isoDateFormat.parse(dateStr.replace("T", " "));
		} catch (ParseException e) {
			String errorMsg = "Error date format: " + dateStr;
			logger.error(errorMsg, e);
			throw new RuntimeException(errorMsg);
		}
	}

	/**
	 * Convert Date object to ISO Date string.
	 *
	 * @param date Date object
	 * @return ISO Date String
	 */
	public static String formatISODate(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat isoDateFormat = new SimpleDateFormat(ISO_DATE_FORMAT);
		return isoDateFormat.format(date);
	}

}
