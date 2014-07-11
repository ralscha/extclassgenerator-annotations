/**
 * Copyright 2013-2014 Ralph Schaer <ralphschaer@gmail.com>
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
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that configures different aspects of a model field. The annotation does not
 * have to be present on the field to be included in the generated JS code. The generator
 * takes all public readable fields into account.
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Repeatable(ModelFields.class)
public @interface ModelField {
	/**
	 * Name of the field. Property '<a
	 * href="http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.Field-cfg-name" >name</a>'
	 * in JS.
	 * <p>
	 * If not present the name of the field is used.
	 */
	String value() default "";

	/**
	 * Type of the field. Property '<a
	 * href="http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.Field-cfg-type" >type</a>'
	 * in JS.
	 * <p>
	 * If not present the library tries to figure out the type.
	 */
	ModelType type() default ModelType.AUTO;

	/**
	 * Type of the field. Property '<a
	 * href="http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.Field-cfg-type" >type</a>'
	 * in JS. This is used for the new Ext JS 5 Custom Field Types feature.
	 * <p>
	 * If not present the library tries to figure out the type.
	 * <p>
	 * If {@link #type()} and {@link #customType()} are present {@link #customType()} has
	 * precedence.
	 */
	String customType() default "";

	/**
	 * The default value. Property '<a href=
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.Field-cfg-defaultValue"
	 * >defaultValue</a>' in JS.
	 * <p>
	 * Can be set to {@link #DEFAULTVALUE_UNDEFINED} to set defaultValue to the value
	 * undefined. This prevents defaulting a value.
	 */
	String defaultValue() default "";

	/**
	 * Specifies format of date. Property '<a href=
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.Field-cfg-dateFormat"
	 * >dateFormat</a>' in JS.<br>
	 * For a list of all supported formats see Sencha Doc: <a
	 * href="http://docs.sencha.com/ext-js/4-2/#!/api/Ext.Date">Ext.Date</a>
	 * <p>
	 * Will be ignored if the field is not a {@link ModelType#DATE} field.
	 */
	String dateFormat() default "";

	/**
	 * If true null value is used if value cannot be parsed. If false default values are
	 * used (0 for integer and float, "" for string and false for boolean).<br>
	 * Property '<a href=
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.Field-cfg-useNull" >useNull</a>'
	 * in JS.<br>
	 * <p>
	 * Only used if type of field is {@link ModelType#INTEGER}, {@link ModelType#FLOAT},
	 * {@link ModelType#STRING} or {@link ModelType#BOOLEAN}.
	 */
	boolean useNull() default false;

	/**
	 * Typical use for a virtual field to extract field data from the model object <br>
	 * Property '<a href=
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.Field-cfg-mapping" >mapping</a>'
	 * in JS.
	 * <p>
	 */
	String mapping() default "";

	/**
	 * If set to false prevents the value of this field to be serialized or written with
	 * Ext.data.writer.Writer <br>
	 * Typical use for a virtual field <br>
	 * Property '<a href=
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.Field-cfg-persist" >persist</a>'
	 * in JS.
	 * <p>
	 * Defaults to true
	 */
	boolean persist() default true;

	/**
	 * A critical field is a field that must always be sent to the server even if it has
	 * not changed.
	 * <p>
	 * See <a href=
	 * "http://docs.sencha.com/ext/5.0.0/apidocs/#!/api/Ext.data.field.Field-cfg-critical"
	 * >Ext.data.field.FieldView#critical</a>
	 * <p>
	 * Defaults to false
	 */
	boolean critical() default false;

	/**
	 * The field name or names within the Model on which the value of this field depends,
	 * and from which a new value may be calculated. These values are the values used by
	 * the {@link #convert()} method. If you do not have a {@link #convert()} method then
	 * this config should not be specified. Before using this config you should consider
	 * if using a {@link #calculate()} method instead of a {@link #convert()} method would
	 * be simpler.
	 * <p>
	 * See <a href=
	 * "http://docs.sencha.com/ext/5.0.0/apidocs/#!/api/Ext.data.field.Field-cfg-depends"
	 * >Ext.data.field.FieldView#depends</a>
	 * <p>
	 * Defaults to null
	 */
	String[] depends() default {};

	/**
	 * Function which coerces string values in raw data into the field's type <br>
	 * Typical use for a virtual field <br>
	 * Property '<a href=
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.Field-cfg-convert" >
	 * Ext.data.Field.convert</a>' in JS.
	 * <p>
	 */
	String convert() default "";

	/**
	 * This config defines a simple field calculation function. A calculate method only
	 * has access to the record data and should return the value of the calculated field.
	 * When provided in this way, the depends config is automatically determined by
	 * parsing the calculate function.
	 * 
	 * See <a href=
	 * "http://docs.sencha.com/ext/5.0.0/apidocs/#!/api/Ext.data.field.Field-cfg-calculate"
	 * >Ext.data.Field.calculate</a>
	 */
	String calculate() default "";

	/**
	 * Constant for the value undefined. Can only be used for the property
	 * {@link #defaultValue()}. According to the <a href=
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.Field-cfg-defaultValue"
	 * >documentation</a> setting defaultValue to undefined prevents defaulting a value.
	 */
	public final static String DEFAULTVALUE_UNDEFINED = "undefined";
}
