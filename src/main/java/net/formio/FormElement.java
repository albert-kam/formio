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

import java.util.List;

import net.formio.validation.ConstraintViolationMessage;
import net.formio.validation.ValidationResult;

/**
 * Element in a form - form field, group of fields (form mapping), ...
 * @author Radek Beran
 */
public interface FormElement extends FormProperties {
	
	/**
	 * Parent of this form element.
	 * @return
	 */
	FormMapping<?> getParent();
	
	/**
	 * Name of this form element (full path from outer object to potentially nested property).
	 * It represents an identifier of this element in the form.
	 * @return
	 */
	String getName();
	
	/**
	 * Key for the label (derived from name).
	 * Does not contain any brackets with indexes as the name does. 
	 * Useful especially for repeated fields that are part of list mapping
	 * and should have the same labels, but different (unique) indexed names.
	 * @return
	 */
	String getLabelKey();
	
	/**
	 * Returns view with properties of this form element.
	 * @return
	 */
	FormProperties getFormProperties();
	
	/**
	 * Returns ordinal index of this form element.
	 * @return
	 */
	int getOrder();
	
	/**
	 * Returns result with validation messages, {@code null} if form data was not validated yet.
	 * @return
	 */
	ValidationResult getValidationResult();
	
	/**
	 * Returns validation messages of form element.
	 * @return
	 */
	List<ConstraintViolationMessage> getValidationMessages();
}
