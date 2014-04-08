/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
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

import java.util.List;

import javax.validation.groups.Default;

import net.formio.binding.ParseError;
import net.formio.upload.RequestProcessingError;


/**
 * Bean validator.
 * @author Radek Beran
 */
public interface BeanValidator {

	/**
	 * Validates object and returns report with validation errors.
	 * @param <T> 
	 * @param inst filled object
	 * @param propPrefix path to validated object (properties of validated object should be prefixed by this path
	 * when constructing resulting validation error messages)
	 * @param requestErrors request processing errors that should be translated to field or global messages
	 * @param parseErrors parse errors that should be translated to field messages
	 * @param groups the group or list of groups targeted for validation (defaults to implicit 
	 * {@link Default} group, but other groups (interfaces) can be created - extended or not extended
	 * from {@link Default} - and their classes used in attribute "groups" of validation annotations)
	 * @return validation report with validation errors
	 */
	<T> ValidationResult validate(
		T inst, 
		String propPrefix, 
		List<RequestProcessingError> requestErrors, 
		List<ParseError> parseErrors, 
		Class<?>... groups);
	
	/**
	 * Returns true if given property of given class is required (must be filled with data).
	 * @param cls
	 * @param propertyName
	 * @return
	 */
	boolean isRequired(Class<?> cls, String propertyName);
}
