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
package net.formio.props;

import net.formio.common.heterog.HeterogMap;
import net.formio.props.types.InlinePosition;

/**
 * Default implementation of {@link FormFieldProperties}. Immutable.
 * @author Radek Beran
 */
public class FormFieldPropertiesImpl extends FormPropertiesImpl implements FormFieldProperties {
	// public because of introspection required by some template frameworks
	
	private static final long serialVersionUID = 8353865315646591562L;
	
	/** For internal use only. */
	public FormFieldPropertiesImpl(final HeterogMap<String> properties) {
		super(properties);
	}
	
	/** For internal use only. */
	public FormFieldPropertiesImpl(final FormFieldProperties src) {
		this(src, (FormElementProperty<?>)null, null);
	}
	
	/** For internal use only. */
	public <T> FormFieldPropertiesImpl(final FormFieldProperties src, FormElementProperty<T> property, T value) {
		super(src, property, value);
	}
	
	@Override
	public boolean isChooseOptionDisplayed() {
		Boolean b = getProperty(FormElementProperty.CHOOSE_OPTION_DISPLAYED);
		return b != null && b.booleanValue();
	}
	
	@Override
	public String getChooseOptionTitle() {
		return getProperty(FormElementProperty.CHOOSE_OPTION_TITLE);
	}
	
	@Override
	public String getPlaceholder() {
		return getProperty(FormElementProperty.PLACEHOLDER);
	}
	
	@Override
	public InlinePosition getInline() {
		return getProperty(FormElementProperty.INLINE);
	}
	
	@Override
	public Integer getColInputWidth() {
		return getProperty(FormElementProperty.COL_INPUT_WIDTH);
	}
	
	@Override
	public String getConfirmMessage() {
		return getProperty(FormElementProperty.CONFIRM_MESSAGE);
	}
}
