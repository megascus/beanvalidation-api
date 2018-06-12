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
import javax.validation.constraints.Max.List;

/**
 * アノテーションの付けられた要素は値が指定された最大値以下の数値でなければなりません。
 * <p>
 * サポートされる型は
 * <ul>
 *     <li>{@code BigDecimal}</li>
 *     <li>{@code BigInteger}</li>
 *     <li>{@code byte}、 {@code short}、 {@code int}、 {@code long}、およびそれらを表すラッパー</li>
 * </ul>
 * 丸め誤差のため{@code double}と{@code float}はサポートされていないことに注意してください。
 * (一部のプロバイダは近似的なサポートを提供するかもしれません)
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
public @interface Max {

	String message() default "{javax.validation.constraints.Max.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	/**
	 * @return 要素が以下でなければいけない値
	 */
	long value();

	/**
         * 同じ要素にいくつかの{@link Max}アノテーションを定義します。
	 *
	 * @see Max
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		Max[] value();
	}
}
