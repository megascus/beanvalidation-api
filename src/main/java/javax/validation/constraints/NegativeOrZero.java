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
import javax.validation.constraints.NegativeOrZero.List;

/**
 * アノテーションの付けられた要素は負の数もしくは0でなければなりません。
 * <p>
 * サポートされる型は
 * <ul>
 *     <li>{@code BigDecimal}</li>
 *     <li>{@code BigInteger}</li>
 *     <li>{@code byte}、 {@code short}、 {@code int}、 {@code long}、 {@code float}、
 *     {@code double}、およびそれらを表すラッパー</li>
 * </ul>
 * <p>
 * {@code null} 要素は有効とみなされます。
 *
 * @author Gunnar Morling
 * @since 2.0
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(List.class)
@Documented
@Constraint(validatedBy = { })
public @interface NegativeOrZero {

	String message() default "{javax.validation.constraints.NegativeOrZero.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	/**
         * 同じ要素にいくつかの{@link NegativeOrZero}アノテーションを定義します。
	 *
	 * @see NegativeOrZero
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		NegativeOrZero[] value();
	}
}
