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

/**
 * Definable properties of form field/mapping.
 * These properties contain typical accessibility flags like required, enabled, readonly, visible
 * in addition to custom user-defined form properties.
 * @author Radek Beran
 */
public interface FormProperties {
	/**
	 * Returns true if this field/mapping is visible.
	 * @return
	 */
	boolean isVisible();
	
	/**
	 * Returns true if this field/mapping is enabled.
	 * @return
	 */
	boolean isEnabled();
	
	/**
	 * Returns true if this field/mapping is read-only.
	 * @return
	 */
	boolean isReadonly();
	
	/**
	 * Returns help for filling this field/mapping.
	 * @return
	 */
	String getHelp();
	
	/**
	 * Returns true if the label should be shown.
	 * @return
	 */
	boolean isLabelVisible();
	
	/**
	 * Returns JavaScript events that invoke custom actions using AJAX request. 
	 * @return
	 */
	JsEventUrlResolvable[] getDataAjaxActions();
	
	/**
	 * CSS selector (mostly ID selector) for finding AJAX-request-source element related elements. 
	 * The selector is applied to the whole document. All related elements are informed about the TDI process 
	 * flow the same way as TDI source element is. For example, data-related-element="#basket-container".
	 * @return
	 */
	String getDataRelatedElement();
	
	/**
	 * CSS selector for finding the TDI AJAX-request-source element ancestor. The closest ancestor, 
	 * which suits the CSS selector, is found. All related elements are informed about the TDI process 
	 * flow the same way as TDI source element is. For example, data-related-element="tr".
	 * @return
	 */
	String getDataRelatedAncestor();
	
	/**
	 * Confirm message. It allows confirming or rejecting of a TDI action. In case of rejection 
	 * no TDI request will be sent to the server.
	 * @return
	 */
	String getDataConfirm();
	
	/**
	 * Returns properties of this form field/mapping in a map.
	 * @return
	 */
	HeterogMap<String> getPropertiesMap();
}
