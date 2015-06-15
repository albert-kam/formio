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
package net.formio.validation.validators;

import static org.junit.Assert.assertEquals;
import net.formio.validation.InterpolatedMessage;
import net.formio.validation.Severity;
import net.formio.validation.constraints.IPv6Address;

import org.junit.Test;

public class IPv6AddressValidatorTest extends ValidatorTest {

	@Test
	public void testValid() {
		IPv6AddressValidator validator = IPv6AddressValidator.getInstance();
		assertValid(validator.validate(value((String)null)));
		assertValid(validator.validate(value("")));
		assertValid(validator.validate(value("fe80::84e7:d5ca:3a25:428e%3")));
		assertValid(validator.validate(value("2001:0:9d38:90d7:3492:1a70:a698:f57f")));
	}
	
	@Test
	public void testInvalid() {
		IPv6AddressValidator validator = IPv6AddressValidator.getInstance();
		assertInvalid(validator.validate(value("255.255.255.255")));
		InterpolatedMessage msg = assertInvalid(validator.validate(value("1a:2b:3c:4d")));
		assertEquals(Severity.ERROR, msg.getSeverity());
		assertEquals(IPv6Address.MESSAGE, msg.getMessageKey());
	}
	
}
