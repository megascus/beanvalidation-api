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
import javax.validation.constraints.NotEmpty.List;

/**
 * アノテーションの付けられた要素は{@code null}もしくは空であってはなりません。
 * 
 * サポートされる型は
 * <ul>
 * <li>{@code CharSequence} (文字列の長さが評価されます)</li>
 * <li>{@code Collection} (コレクションの要素数が評価されます)</li>
 * <li>{@code Map} (マップの要素数が評価されます)</li>
 * <li>配列 (配列の長さが評価されます)</li>
 * </ul>
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 *
 * @since 2.0
 */
@Documented
@Constraint(validatedBy = { })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(List.class)
public @interface NotEmpty {

	String message() default "{javax.validation.constraints.NotEmpty.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	/**
         * 同じ要素に複数の{@code @NotEmpty}アノテーションを定義します。
	 *
	 * @see NotEmpty
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		NotEmpty[] value();
	}
}
