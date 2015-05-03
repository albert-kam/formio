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

import java.util.Arrays;
import java.util.List;

import net.formio.FormField;
import net.formio.ajax.AjaxParams;
import net.formio.ajax.JsEvent;
import net.formio.ajax.action.HandledJsEvent;
import net.formio.internal.FormUtils;

/**
 * Renders script for invoking AJAX events.
 * @author Radek Beran
 */
class AjaxEventRenderer {
	private final BasicFormRenderer renderer;

	AjaxEventRenderer(BasicFormRenderer renderer) {
		if (renderer == null) {
			throw new IllegalArgumentException("renderer cannot be null");
		}
		this.renderer = renderer;
	}
	
	/**
	 * Renders script for handling form field.
	 * @param field
	 * @param multipleInputs
	 * @return
	 */
	protected <T> String renderFieldScript(FormField<T> field, boolean multipleInputs) {
		StringBuilder sb = new StringBuilder();
		List<HandledJsEvent> urlEvents = Arrays.asList(field.getProperties().getDataAjaxActions());
		if (urlEvents.size() > 0) {
			sb.append("<script>" + renderer.newLine());
			if (multipleInputs) {
				if (field.getChoices() != null && field.getChoiceRenderer() != null) {
					List<?> items = field.getChoices().getItems();
					if (items != null) {
						for (int i = 0; i < items.size(); i++) {
							String itemId = field.getElementIdWithIndex(i);
							sb.append(renderTdiSend(field, itemId, urlEvents));
						}
					}
				}
			} else {
				sb.append(renderTdiSend(field, field.getElementId(), urlEvents));
			}
			sb.append("</script>" + renderer.newLine());
		}
		return sb.toString();
	}
	
	/**
	 * Composes JavaScript for given form field that initiates TDI AJAX request when
	 * some given event occurs - different JavaScript events can have different URL addresses
	 * for handling the AJAX request. The value of form field is part of the AJAX request
	 * (if some value is filled).
	 * @param formField
	 * @param inputId
	 * @param events
	 * @return
	 */
	private <T> String renderTdiSend(FormField<T> formField, String inputId, List<HandledJsEvent> events) {
		StringBuilder sb = new StringBuilder();
		if (events != null && events.size() > 0) {
			String elm = "$(\"#" + inputId + "\")";
			sb.append(elm + ".on({" + renderer.newLine());
			for (int i = 0; i < events.size(); i++) {
				HandledJsEvent eventToUrl = events.get(i);
				JsEvent eventType = eventToUrl.getEvent();
				if (eventType != null) {
					String url = eventToUrl.getUrl(formField.getParent().getConfig().getUrlBase(), formField);
					if (url == null || url.isEmpty()) {
						throw new IllegalArgumentException("No URL for AJAX request is specified");
					}
					url = FormUtils.urlWithAppendedParameter(url, AjaxParams.SRC_ELEMENT_NAME, formField.getName());
					sb.append(eventType.getEventName() + ": function(evt) {"  + renderer.newLine());
					// Remember previous data-ajax-url (to revert it back) and set it temporarily to custom URL
					sb.append("var prevUrl = " + elm + ".attr(\"data-ajax-url\");" + renderer.newLine());
					sb.append(elm + ".attr(\"data-ajax-url\", \"" + url + "\");" + renderer.newLine());
					sb.append("TDI.Ajax.send(" + elm + ");" + renderer.newLine());
					sb.append(elm + ".attr(\"data-ajax-url\", prevUrl);" + renderer.newLine());
					sb.append("var prevUrl = null;" + renderer.newLine());
					sb.append("}");
					if (i < events.size() - 1) {
						// not the last event handler
						sb.append(",");
					}
					sb.append(renderer.newLine());
				}
			}
			sb.append("});" + renderer.newLine());
		}
		return sb.toString();
	}
}
