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
package net.formio.binding;

/**
 * Abstract Instantiator.
 * @author Radek Beran
 */
abstract class AbstractInstantiator implements Instantiator {

	protected Object[] prepareArgs(Class<?>[] argTypes, Object[] args) {
		Object[] arguments = new Object[args.length];
		for (int i = 0; i < args.length; i++) {
			if (args[i] == null) {
				PrimitiveType pt = PrimitiveType.byPrimitiveClass(argTypes[i]);
				if (pt != null) {
					// argument is of primitive type, using default value for primitive instead of incompatible null
					arguments[i] = pt.getInitialValue();
				} else {
					arguments[i] = args[i];
				}
			} else {
				arguments[i] = args[i];
			}
		}
		return arguments;
	}
}
