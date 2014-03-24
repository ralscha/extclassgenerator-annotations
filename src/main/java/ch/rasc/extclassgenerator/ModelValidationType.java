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

public enum ModelValidationType {
	GENERIC(false) {
		@Override
		public boolean isValid(ModelValidation modelValidationAnnotation) {
			return parameterExists(modelValidationAnnotation.parameters(), "type");
		}
	},
	CREDITCARDNUMBER(false) {
		@Override
		public boolean isValid(ModelValidation modelValidationAnnotation) {
			return true;
		}
	},
	DIGITS(false) {
		@Override
		public boolean isValid(ModelValidation modelValidationAnnotation) {
			ModelValidationParameter[] parameters = modelValidationAnnotation.parameters();
			return (parameters != null) && (parameters.length == 2) && parameterExists(parameters, "integer")
					&& parameterExists(parameters, "fraction") && parameters[0].value().matches("\\d+")
					&& parameters[1].value().matches("\\d+");
		}
	},
	EMAIL(true) {
		@Override
		public boolean isValid(ModelValidation modelValidationAnnotation) {
			return true;
		}
	},
	FORMAT(true) {
		@Override
		public boolean isValid(ModelValidation modelValidationAnnotation) {
			ModelValidationParameter[] parameters = modelValidationAnnotation.parameters();
			return (parameters != null) && (parameters.length == 1) && parameters[0].name().equals("matcher")
					&& !parameters[0].value().trim().isEmpty();
		}
	},
	FUTURE(false) {
		@Override
		public boolean isValid(ModelValidation modelValidationAnnotation) {
			return true;
		}
	},
	INCLUSION(true) {
		@Override
		public boolean isValid(ModelValidation modelValidationAnnotation) {
			ModelValidationParameter[] parameters = modelValidationAnnotation.parameters();
			return (parameters != null) && (parameters.length == 1) && parameters[0].name().equals("list");
		}
	},
	LENGTH(true) {
		@Override
		public boolean isValid(ModelValidation modelValidationAnnotation) {
			ModelValidationParameter[] parameters = modelValidationAnnotation.parameters();
			if ((parameters != null) && (parameters.length == 1 || parameters.length == 2)
					&& (parameterExists(parameters, "min") || parameterExists(parameters, "max"))) {

				if (parameters.length == 1) {
					return parameters[0].value().matches("\\d+");
				}

				return parameters[0].value().matches("\\d+") && parameters[1].value().matches("\\d+");
			}
			return false;
		}
	},
	NOTBLANK(false) {
		@Override
		public boolean isValid(ModelValidation modelValidationAnnotation) {
			return true;
		}
	},
	PAST(false) {
		@Override
		public boolean isValid(ModelValidation modelValidationAnnotation) {
			return true;
		}
	},
	PRESENCE(true) {
		@Override
		public boolean isValid(ModelValidation modelValidationAnnotation) {
			return true;
		}
	},
	RANGE(false) {
		@Override
		public boolean isValid(ModelValidation modelValidationAnnotation) {
			ModelValidationParameter[] parameters = modelValidationAnnotation.parameters();
			if ((parameters != null) && (parameters.length == 1 || parameters.length == 2)
					&& (parameterExists(parameters, "min") || parameterExists(parameters, "max"))) {

				if (parameters.length == 1) {
					return parameters[0].value().matches("\\d+(\\.\\d+)?");
				}

				return parameters[0].value().matches("\\d+(\\.\\d+)?")
						&& parameters[1].value().matches("\\d+(\\.\\d+)?");
			}
			return false;
		}
	};

	private boolean builtin;

	private ModelValidationType(boolean builtin) {
		this.builtin = builtin;
	}

	public boolean isBuiltin() {
		return builtin;
	}

	public abstract boolean isValid(ModelValidation modelValidationAnnotation);

	private static boolean parameterExists(ModelValidationParameter[] parameters, String parameterName) {
		for (ModelValidationParameter parameter : parameters) {
			if (parameterName.equals(parameter.name())) {
				return true;
			}
		}

		return false;
	}
}
