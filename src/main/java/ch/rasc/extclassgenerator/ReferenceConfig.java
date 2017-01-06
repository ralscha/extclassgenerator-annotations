/**
 * Copyright 2013-2017 Ralph Schaer <ralphschaer@gmail.com>
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
 * Annotation that configures different aspects of a reference. This annotation is the
 * value for the {@link ModelField#reference()} attribute
 * <p>
 * See <a href=
 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.field.Field-cfg-reference"
 * >Ext.data.field.Field#reference</a> for more information
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ReferenceConfig {

	/**
	 * The type which this field references. This is the value set by the string form of
	 * reference. If the referenced entity has an ownership relationship this field should
	 * be omitted and reference.parent or reference.child should be specified instead.
	 */
	String type() default "";

	/**
	 * The name of the association. By default, the name of the assocation is the
	 * capitalized inverse plus "By" plus the capitalized role.
	 */
	String association() default "";

	/**
	 * Set this property instead of reference.type to indicate that the referenced entity
	 * is an owned child of this entity. That is, the reference entity should be deleted
	 * when this entity is deleted.
	 */
	String child() default "";

	/**
	 * Set this property instead of reference.type to indicate that the referenced entity
	 * is the owning parent of this entity. That is, this entity should be deleted when
	 * the reference entity is deleted.
	 */
	String parent() default "";

	/**
	 * The name of the role played by the referenced entity. By default, this is the field
	 * name (minus its "Id" suffix if present).
	 */
	String role() default "";

	/**
	 * The name of the inverse role (of this entity with respect to the reference entity).
	 * By default, this is the pluralized name of this entity, unless this reference is
	 * unique, in which case the default name is the singularized name of this entity.
	 */
	String inverse() default "";

}
