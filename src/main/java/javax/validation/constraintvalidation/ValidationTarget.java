/*
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package javax.validation.constraintvalidation;

import javax.validation.ConstraintValidator;

/**
 * 
 * {@link ConstraintValidator}の有効な対象のリスト。
 *
 * @author Emmanuel Bernard
 * @since 1.1
 */
public enum ValidationTarget {

	/**
	 * (返された) 制約によってアノテーションの付けられた要素。
	 */
	ANNOTATED_ELEMENT,

	/**
	 * アノテーションの付けられたメソッドまたはコンストラクタのパラメーターの配列(別名クロスパラメーター)。
	 */
	PARAMETERS
}
