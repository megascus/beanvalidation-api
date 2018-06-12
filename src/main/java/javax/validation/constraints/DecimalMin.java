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
import javax.validation.constraints.DecimalMin.List;

/**
 * アノテーションの付けられた要素は値が指定された最小値以上の数値でなければなりません。
 * <p>
 * サポートされる型は
 * <ul>
 *     <li>{@code BigDecimal}</li>
 *     <li>{@code BigInteger}</li>
 *     <li>{@code CharSequence}</li>
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
public @interface DecimalMin {

	String message() default "{javax.validation.constraints.DecimalMin.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	/**
         * {@code BigDecimal}文字列表現による最小値の{@code String}表現です。
	 *
	 * @return 要素がそれ以上である必要がある値
	 */
	String value();

	/**
         * 最小値の境界値が含まれるか含まれないかを指定します。
         * 
         * デフォルトでは境界値は含まれます。
	 *
	 * @return 指定された最小値以上である必要がある場合は{@code true}、超えている必要がある場合は{@code false}
         * 
	 * @since 1.1
	 */
	boolean inclusive() default true;

	/**
         * 同じ要素にいくつかの{@link DecimalMin}アノテーションを定義します。
	 *
	 * @see DecimalMin
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		DecimalMin[] value();
	}
}
