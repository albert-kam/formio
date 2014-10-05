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
package net.formio.validation;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ValidationResultTest {

	@Test
	public void testToString() {
		Map<String, List<ConstraintViolationMessage>> fieldMsgs = new LinkedHashMap<String, List<ConstraintViolationMessage>>();
		List<ConstraintViolationMessage> msgs = new ArrayList<ConstraintViolationMessage>();
		msgs.add(new ConstraintViolationMessage(Severity.ERROR, "Invalid e-mail", "email.invalid", new LinkedHashMap<String, Serializable>()));
		msgs.add(new ConstraintViolationMessage(Severity.ERROR, "E-mail required", "email.required", new LinkedHashMap<String, Serializable>()));
		fieldMsgs.put("email", msgs);
		List<ConstraintViolationMessage> msgs2 = new ArrayList<ConstraintViolationMessage>();		
		msgs2.add(new ConstraintViolationMessage(Severity.ERROR, "Last name required", "lastName.required", new LinkedHashMap<String, Serializable>()));
		fieldMsgs.put("lastName", msgs2);
		
		List<ConstraintViolationMessage> globalMsgs = new ArrayList<ConstraintViolationMessage>();
		globalMsgs.add(new ConstraintViolationMessage(Severity.WARNING, "Optional parameters are not filled", "subject.optional", new LinkedHashMap<String, Serializable>()));
		globalMsgs.add(new ConstraintViolationMessage(Severity.ERROR, "Subject has not valid business number", "subject.bn", new LinkedHashMap<String, Serializable>()));
		
		ValidationResult result = new ValidationResult(fieldMsgs, globalMsgs);
		String str = result.toString();
		assertTrue(str.contains("Invalid e-mail"));
		assertTrue(str.contains("E-mail required"));
		assertTrue(str.contains("Last name required"));
		assertTrue(str.contains("Optional parameters are not filled"));
		assertTrue(str.contains("Subject has not valid business number"));
	}

}
