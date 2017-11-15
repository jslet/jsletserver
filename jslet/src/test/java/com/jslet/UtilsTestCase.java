/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.jslet;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.jslet.utils.DateUtil;

/**
 * TODO
 *
 * @author tony
 */
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration
public class UtilsTestCase {

	@Test
	public void testFormatDate() {
		Date d = new Date(2017, 10, 1, 11, 11, 11);
		Calendar.getInstance().getTime();
		System.out.println(DateUtil.formatISODate(d));
	}
}
