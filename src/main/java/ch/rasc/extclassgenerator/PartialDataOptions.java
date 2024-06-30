/*
 * Copyright the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.rasc.extclassgenerator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is the value for the {@link Model#partialDataOptions()} attribute
 * <p>
 * See <a href=
 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.writer.Writer-cfg-partialDataOptions"
 * >Ext.data.writer.Writer#partialDataOptions</a> for more information
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PartialDataOptions {

	/**
	 * True to include associated data
	 * <p>
	 * Defaults to false
	 */
	boolean associated() default false;

	/**
	 * True to only include fields that have been modified
	 * <p>
	 * Defaults to true
	 */
	boolean changes() default true;

	/**
	 * True to include fields set as critical. This is only meaningful when changes is
	 * true
	 * <p>
	 * Defaults to true
	 */
	boolean critical() default true;

	/**
	 * Pass true to only return persistent fields
	 * <p>
	 * Defaults to false
	 */
	boolean persist() default false;

}
