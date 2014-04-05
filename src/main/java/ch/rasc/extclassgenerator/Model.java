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
	 * "Classname" of the model. See <a
	 * href="http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.Model"
	 * >Ext.data.Model</a>.
	 * <p>
	 * If not present full qualified name of the class is used.
	 */
	String value() default "";

	/**
	 * Name of the id property. See <a href=
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.Model-cfg-idProperty"
	 * >Ext.data.Model#idProperty</a>. This also sets the <a href=
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.proxy.Server-cfg-idParam"
	 * >idParam</a> property on the proxy.
	 * <p>
	 * If not present default value of 'id' is used.
	 */
	String idProperty() default "id";

	/**
	 * If true a reader config with root : 'records' (Ext JS 4) or rootProperty
	 * : 'records' (Sencha Touch 2 and Ext JS 5) will be added to the model
	 * object.
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
	 * If set to true the pageParam, startParam and limitParam option of the
	 * proxy will be set to undefined (Ext JS 4), false (Sencha Touch 2) or ''
	 * (Ext JS 5). This prevents the proxy of sending the page, start and limit
	 * parameter to the server.
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
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.proxy.Direct-cfg-api"
	 * >Ext.data.proxy.Direct#api</a>.
	 * <p>
	 * If only the readMethod is specified the generator will write the property
	 * <a href=
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.proxy.Direct-cfg-directFn"
	 * >directFn</a> instead.
	 */
	String readMethod() default "";

	/**
	 * Specifies the create method. This is a ExtDirect reference in the form
	 * action.methodName. See <a href=
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.proxy.Direct-cfg-api"
	 * >Ext.data.proxy.Direct#api</a>.
	 */
	String createMethod() default "";

	/**
	 * Specifies the update method. This is a ExtDirect reference in the form
	 * action.methodName. See <a href=
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.proxy.Direct-cfg-api"
	 * >Ext.data.proxy.Direct#api</a>.
	 */
	String updateMethod() default "";

	/**
	 * Specifies the destroy method. This is a ExtDirect reference in the form
	 * action.methodName. See <a href=
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.proxy.Direct-cfg-api"
	 * >Ext.data.proxy.Direct#api</a>.
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
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.reader.Reader-cfg-messageProperty"
	 * >Ext.data.reader.Reader#messageProperty</a>
	 *
	 */
	String messageProperty() default "";

	/**
	 * If set add a writer property to the proxy.
	 *
	 * <pre>
	 *   proxy: {
	 *     type: 'direct',
	 *     writer: 'mywriter'
	 *   }
	 * </pre>
	 *
	 * See <a href=
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.proxy.Proxy-cfg-writer"
	 * >Ext.data.proxy.Proxy#writer</a>
	 *
	 */
	String writer() default "";

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
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.reader.Reader-cfg-successProperty"
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
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.reader.Reader-cfg-totalProperty"
	 * >Ext.data.reader.Reader#totalProperty</a>
	 * <p>
	 * If not present default value 'total' is used.
	 */
	String totalProperty() default "";

	/**
	 * If set a reader config with root : 'rootProperty' (Ext JS 4) or
	 * rootProperty : 'rootProperty' (Sencha Touch 2 and Ext JS 5) will be added
	 * to the model object.
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
	 * "http://docs.sencha.com/ext-js/4-2/#!/api/Ext.data.reader.Reader-cfg-root"
	 * >Ext.data.reader.Reader#root</a>
	 */
	String rootProperty() default "";

}
