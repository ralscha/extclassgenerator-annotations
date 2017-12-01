/**
 * Copyright 2013-2017 the original author or authors.
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
 * Annotation to configure different aspects of a model object
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Model {

	/**
	 * If true the model generator tries to auto-detect the type of a field when no
	 * {@link ModelField} annotation is present.
	 * <p>
	 * If false every field that is not annotated with {@link ModelField} or contains an
	 * empty {@link ModelField#type()} attribute is set to the type {@link ModelType#AUTO}
	 */
	boolean autodetectTypes() default true;

	/**
	 * "Classname" of the model. See
	 * <a href="http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.Model" >Ext
	 * .data.Model </a>.
	 * <p>
	 * If not present full qualified name of the class is used.
	 */
	String value() default "";

	/**
	 * "Superclass" of this model.
	 * <p>
	 * Defaults to "Ext.data.Model"
	 */
	String extend() default "Ext.data.Model";

	/**
	 * Name of the id property. See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.Model-cfg-idProperty"
	 * > Ext.data.Model#idProperty</a>. This also sets the <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.proxy.Server-cfg-idParam"
	 * > idParam</a> property on the proxy.
	 * <p>
	 * If not present default value of 'id' is used.
	 * <p>
	 * This is an alternative to the {@link ModelId} annotation. The annotation takes
	 * precedence if both are present.
	 */
	String idProperty() default "id";

	/**
	 * If specified, this is the name of the property that contains the entity "version".
	 * The version property is used to manage a long-running transaction and allows the
	 * detection of simultaneous modification.
	 * <p>
	 * This is an alternative to the {@link ModelVersion} annotation. The annotation takes
	 * precedence if both are present.
	 * <p>
	 * See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.Model-cfg-versionProperty"
	 * >Ext.data.Model#versionProperty</a>
	 *
	 * <p>
	 * Defaults to null
	 */
	String versionProperty() default "";

	/**
	 * The name of the property a server will use to send back a client-generated id in a
	 * create or update operation. This also sets the property clientIdProperty on the
	 * writer config.
	 * <p>
	 * This is an alternative to the {@link ModelClientId} annotation. The annotation
	 * takes precedence if both are present.
	 * <p>
	 * See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.Model-cfg-clientIdProperty"
	 * >Ext.data.Model#clientIdProperty</a>
	 *
	 * <p>
	 * Defaults to null
	 */
	String clientIdProperty() default "";

	/**
	 * If true a reader config with root : 'records' (Ext JS 4) or rootProperty :
	 * 'records' (Sencha Touch 2 and Ext JS 5) will be added to the model object.
	 *
	 * <pre>
	 * reader : {
	 *   rootProperty : 'records'
	 * }
	 * </pre>
	 *
	 * Default value is false. To set a specific value to the root property use
	 * {@link #rootProperty()}
	 */
	boolean paging() default false;

	/**
	 * If set to true the pageParam, startParam and limitParam option of the proxy will be
	 * set to undefined (Ext JS 4), false (Sencha Touch 2) or '' (Ext JS 5). This prevents
	 * the proxy of sending the page, start and limit parameter to the server.
	 *
	 * <pre>
	 *   proxy: {
	 *     type: 'direct',
	 *     pageParam: undefined,
	 *     startParam: undefined,
	 *     limitParam: undefined,
	 *   }
	 * </pre>
	 *
	 * Default value is false
	 */
	boolean disablePagingParameters() default false;

	/**
	 * Specifies the read method. This is a ExtDirect reference in the form
	 * action.methodName. See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.proxy.Direct-cfg-api"
	 * > Ext.data.proxy.Direct#api</a>.
	 * <p>
	 * If only the readMethod is specified the generator will write the property <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.proxy.Direct-cfg-directFn"
	 * > directFn</a> instead.
	 */
	String readMethod() default "";

	/**
	 * Specifies the create method. This is a ExtDirect reference in the form
	 * action.methodName. See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.proxy.Direct-cfg-api"
	 * > Ext.data.proxy.Direct#api</a>.
	 */
	String createMethod() default "";

	/**
	 * Specifies the update method. This is a ExtDirect reference in the form
	 * action.methodName. See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.proxy.Direct-cfg-api"
	 * > Ext.data.proxy.Direct#api</a>.
	 */
	String updateMethod() default "";

	/**
	 * Specifies the destroy method. This is a ExtDirect reference in the form
	 * action.methodName. See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.proxy.Direct-cfg-api"
	 * > Ext.data.proxy.Direct#api</a>.
	 */
	String destroyMethod() default "";

	/**
	 * If set add to reader
	 *
	 * <pre>
	 * reader : {
	 *   messageProperty : 'your property name'
	 * }
	 * </pre>
	 *
	 * It is useful to add a customized message in case of error See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.reader.Reader-cfg-messageProperty"
	 * >Ext.data.reader.Reader#messageProperty</a>
	 *
	 */
	String messageProperty() default "";

	/**
	 * If set adds a writer config to the proxy object.
	 *
	 * <pre>
	 *   proxy: {
	 *     type: 'direct',
	 *     writer: {
	 *       type: 'mywriter'
	 *     }
	 *   }
	 * </pre>
	 *
	 * See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.proxy.Proxy-cfg-writer"
	 * >Ext.data.proxy.Proxy#writer</a>
	 * <p>
	 * Defaults to "json"
	 */
	String writer() default "json";

	/**
	 * If set adds a reader config to the proxy object.
	 *
	 * <pre>
	 *   proxy: {
	 *     type: 'direct',
	 *     reader: {
	 *       type: 'myreader'
	 *     }
	 *   }
	 * </pre>
	 *
	 * See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.proxy.Proxy-cfg-reader"
	 * >Ext.data.proxy.Proxy#reader</a>
	 * <p>
	 * Defaults to "json"
	 */
	String reader() default "json";

	/**
	 * If set add to reader
	 *
	 * <pre>
	 * reader : {
	 *   successProperty : 'success'
	 * }
	 * </pre>
	 *
	 * See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.reader.Reader-cfg-successProperty"
	 * >Ext.data.reader.Reader#successProperty</a>
	 * <p>
	 * If not present default value 'success' is used.
	 */
	String successProperty() default "";

	/**
	 * If set add to reader
	 *
	 * <pre>
	 * reader : {
	 *   totalProperty : 'total'
	 * }
	 * </pre>
	 *
	 * See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.reader.Reader-cfg-totalProperty"
	 * >Ext.data.reader.Reader#totalProperty</a>
	 * <p>
	 * If not present default value 'total' is used.
	 */
	String totalProperty() default "";

	/**
	 * If set a reader config with root : 'rootProperty' (Ext JS 4) or rootProperty :
	 * 'rootProperty' (Sencha Touch 2 and Ext JS 5) will be added to the model object.
	 *
	 * <pre>
	 * reader : {
	 *   rootProperty : 'rootProperty'
	 * }
	 * </pre>
	 *
	 * If {@link #paging()} and {@link #rootProperty()} are present
	 * {@link #rootProperty()} has precedence.
	 * <p>
	 * See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.reader.Reader-cfg-rootProperty"
	 * >Ext.data.reader.Reader#rootProperty</a>
	 */
	String rootProperty() default "";

	/**
	 * If specified the generator adds a writer config object to the proxy with
	 * writeAllFields.
	 *
	 * <pre>
	 *   proxy: {
	 *     type: 'direct',
	 *     writer: {
	 *       writeAllFields: true
	 *     }
	 *   }
	 * </pre>
	 *
	 * See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.writer.Writer-cfg-writeAllFields"
	 * >Ext.data.writer.WriterView#writeAllFields</a>
	 * <p>
	 * Defaults to true
	 */
	boolean writeAllFields() default true;

	/**
	 * The id generator to use for this model.
	 * <p>
	 * See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.Model-cfg-identifier"
	 * > Ext.data.Model#identifier</a>
	 *
	 * <p>
	 * Defaults to null
	 */
	String identifier() default "";

	/**
	 * Configuration for the writer
	 * <p>
	 * This object contains the options passed to Ext.data.Model.getData when writing
	 * Ext.data.Model.phantom records or when writeAllFields is set to true.
	 * <p>
	 * See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.writer.Writer-cfg-allDataOptions"
	 * > Ext.data.Model#allDataOptions</a>
	 */
	AllDataOptions allDataOptions() default @AllDataOptions;

	/**
	 * Configuration for the writer
	 * <p>
	 * This object contains the options passed to Ext.data.Model.getData when writing non
	 * Ext.data.Model.phantom records or when writeAllFields is set to false.
	 * <p>
	 * See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.writer.Writer-cfg-partialDataOptions"
	 * > Ext.data.Model#partialDataOptions</a>
	 */
	PartialDataOptions partialDataOptions() default @PartialDataOptions;

	/**
	 * Configures One-to-Many associations without foreign keys
	 * <p>
	 * See <a href=
	 * "http://docs.sencha.com/extjs/6.0/6.0.2-classic/#!/api/Ext.data.Model-cfg-hasMany">Ext.data.Model-cfg-hasMany</a>
	 */
	String[] hasMany() default "";

}
