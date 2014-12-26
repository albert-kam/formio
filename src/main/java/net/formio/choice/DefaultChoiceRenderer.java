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
package net.formio.choice;

import net.formio.common.MessageTranslator;

/**
 * Default implementation of {@link ChoiceRenderer}.
 * @author Radek Beran
 *
 * @param <T>
 */
public class DefaultChoiceRenderer<T> implements ChoiceRenderer<T>{

	/**
	 * Returns an item index as a String; or name of enum constant
	 * if the item is of an enum type.
	 */
	@Override
	public String getId(T item, int itemIndex) {
		String id = "" + itemIndex;
		if (item != null && item.getClass().isEnum()) {
			id = ((Enum<?>)item).name();
		}
		return id;
	}

	/**
	 * Returns toString of the item as its title; or item's title
	 * if the item implements {@link Titled} interface.
	 */
	@Override
	public Object getTitle(T item, int itemIndex) {
		String title = "null";
		if (item instanceof Titled) {
			title = ((Titled)item).getTitle();
		} else if (item != null && item.getClass().isEnum()) {
			Enum<?> e = (Enum<?>)item;
			MessageTranslator tr = new MessageTranslator(item.getClass());
			title = tr.getMessage(e.name());
		} else if (item != null) {
			title = "" + item.toString();
		}
		return title;
	}

}
