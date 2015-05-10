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
package net.formio.render;

import net.formio.Field;
import net.formio.FormField;

/**
 * Renders CSS styles of forms.
 * @author Radek Beran
 */
class StyleRenderer {
	private final BasicFormRenderer renderer;

	StyleRenderer(BasicFormRenderer renderer) {
		if (renderer == null) {
			throw new IllegalArgumentException("renderer cannot be null");
		}
		this.renderer = renderer;
	}
	
	protected String getFormGroupClasses() {
		return "form-group";
	}
	
	protected <T> String getInputEnvelopeClasses(FormField<T> field) {
		StringBuilder sb = new StringBuilder();
		boolean withoutLeadingLabel = isWithoutLeadingLabel(field);
		if (withoutLeadingLabel) {
			sb.append("col-sm-offset-" + getLabelWidth() + " ");
		}
		sb.append("col-sm-4");
		return sb.toString();
	}
	
	protected String getLabelClasses() {
		return "control-label col-sm-" + getLabelWidth();
	}
	
	/**
	 * Returns value of class attribute for the input of given form field.
	 * @param field
	 * @return
	 */
	protected <T> String getInputClasses(FormField<T> field) {
		StringBuilder sb = new StringBuilder();
		if (field.getProperties().getDataAjaxActionWithoutEvent() != null) {
			sb.append("tdi");
		}
		if (isFullWidthInput(field)) {
			sb.append(" " + getFullWidthInputClasses());
		}
		if (Field.SUBMIT_BUTTON.getType().equals(field.getType())) {
			sb.append(" " + getButtonClasses(field));
		}
		return sb.toString();
	}
	
	private int getLabelWidth() {
		return 2;
	}

	private <T> boolean isWithoutLeadingLabel(FormField<T> field) {
		return Field.SUBMIT_BUTTON.getType().equals(field.getType()) || 
			Field.CHECK_BOX.getType().equals(field.getType()) ||
			!field.getProperties().isLabelVisible();
	}
	
	private <T> boolean isFullWidthInput(FormField<T> field) {
		String type = field.getType();
		return !Field.FILE_UPLOAD.getType().equals(type) // otherwise border around field with "Browse" text is drawn
			&& !Field.HIDDEN.getType().equals(type)
			&& !Field.CHECK_BOX.getType().equals(type)
			&& !Field.SUBMIT_BUTTON.getType().equals(type)
			&& !Field.MULTIPLE_CHECK_BOX.getType().equals(type)
			&& !Field.RADIO_CHOICE.getType().equals(type);
	}
	
	private String getFullWidthInputClasses() {
		return "input-sm form-control";
	}
	
	private <T> String getButtonClasses(@SuppressWarnings("unused") FormField<T> field) {
		return "btn btn-default";
	}
}
