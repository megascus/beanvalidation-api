/*
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package javax.validation.constraintvalidation;

import javax.validation.ConstraintValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@link ConstraintValidator}の検証できる対象を定義します。
 * <p>
 * {@code ConstraintValidator}は制約によってアノテーションの付けられた(返された)要素、
 * メソッドまたはコンストラクタのパラメーターの配列(別名クロスパラメーター)、もしくはその両方を対象にできます。
 * <p>
 * {@code @SupportedValidationTarget}が存在しない場合、{@code ConstraintValidator}は制約によってアノテーションの付けられた(返された)要素を対象にします。
 * <p>
 * クロスパラメーターを対象とする{@code ConstraintValidator}は検証するオブジェクトの型として{@code Object[]}(もしくは{@code Object})を受け入れる必要があります。
 *
 * @author Emmanuel Bernard
 * @since 1.1
 */
@Documented
@Target({ TYPE })
@Retention(RUNTIME)
public @interface SupportedValidationTarget {

	ValidationTarget[] value();
}
