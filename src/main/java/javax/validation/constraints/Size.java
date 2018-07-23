/*
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package javax.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size.List;

/**
 * アノテーションの付けられた要素のサイズは指定された境界値(境界値を含む)の間でなければなりません。
 * <p>
 * サポートされる型は
 * <ul>
 *     <li>{@code CharSequence} (文字列の長さが評価されます)</li>
 *     <li>{@code Collection} (コレクションの要素数が評価されます)</li>
 *     <li>{@code Map} (マップの要素数が評価されます)</li>
 *     <li>配列 (配列の長さが評価されます)</li>
 * </ul>
 * <p>
 * {@code null} 要素は有効とみなされます。
 *
 * @author Emmanuel Bernard
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(List.class)
@Documented
@Constraint(validatedBy = { })
public @interface Size {

	String message() default "{javax.validation.constraints.Size.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	/**
	 * @return 要素が以上でなければならないサイズ
	 */
	int min() default 0;

	/**
	 * @return 要素が以下でなければならないサイズ
	 */
	int max() default Integer.MAX_VALUE;

	/**
         * 同じ要素に複数の{@link Size}アノテーションを定義します。
	 *
	 * @see Size
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		Size[] value();
	}
}
