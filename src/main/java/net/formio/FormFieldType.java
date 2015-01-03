/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.formio;

/**
 * Type of form field.
 * @author Radek Beran
 */
public enum FormFieldType {
	TEXT_FIELD("text"),
	TEXT_AREA("textarea"),
	PASSWORD("password"),
	HIDDEN_FIELD("hidden"),
	CHECK_BOX("checkbox"),
	MULTIPLE_CHECK_BOX("checkbox-multiple"),
	RADIO_CHOICE("radio"),
	DROP_DOWN_CHOICE("select"),
	MULTIPLE_CHOICE("select-multiple"),
	DATE_PICKER("date-picker"),
	FILE_UPLOAD("file"),
	SUBMIT_BUTTON("submit"),
	
	// HTML 5:
	COLOR("color"),
	DATE("date"),
	DATE_TIME("datetime"),
	DATE_TIME_LOCAL("datetime-local"),
	TIME("time"),
	EMAIL("email"),
	MONTH("month"),
	NUMBER("number"),
	RANGE("range"),
	SEARCH("search"),
	TEL("tel"),
	URL("url"),
	WEEK("week");
	
	// LABEL("label"),
	// LINK("link"),
	// TODO: Multiple date and file?
	
	private final String type;
	
	private FormFieldType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public boolean isChoice() {
		return this == MULTIPLE_CHECK_BOX || this == RADIO_CHOICE || this == DROP_DOWN_CHOICE || this == MULTIPLE_CHOICE;
	}
	
	public static FormFieldType findByType(String typeName) {
		for (FormFieldType fc : FormFieldType.values()) {
			if (fc.getType().equals(typeName)) {
				return fc;
			}
		}
		return null;
	}
}
