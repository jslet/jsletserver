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

/**
 * The base class of data record.
 *
 * @author Tony Tong
 */
public abstract class AbstractDataRecord implements DataRecord {
	private final static String INSERTED = "i";

	private final static String UPDATED = "u";

	private final static String DELETED = "d";

	private final static String SELECTED = "s";

	@Override
	public boolean inserted() {
		String state = this.getRs();
		return state != null && state.startsWith(INSERTED);
	}

	@Override
	public boolean updated() {
		String state = this.getRs();
		return state != null && state.startsWith(UPDATED);
	}

	@Override
	public boolean deleted() {
		String state = this.getRs();
		return state != null && state.startsWith(DELETED);
	}

	@Override
	public boolean unchanged() {
		String state = this.getRs();
		return state != null && state.startsWith(SELECTED);
	}
}
