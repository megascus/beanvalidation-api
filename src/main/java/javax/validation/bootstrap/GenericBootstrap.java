/*
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package javax.validation.bootstrap;

import javax.validation.Configuration;
import javax.validation.NoProviderFoundException;
import javax.validation.ValidationException;
import javax.validation.ValidationProviderResolver;
import javax.validation.ValidatorFactory;

/**
 * Bean Validationを自動実行するために使用される状態を定義し、プロバイダに依存しない{@link Configuration}を作ります。
 *
 * @author Emmanuel Bernard
 */
public interface GenericBootstrap {

	/**
         * プロバイダの解決戦略を定義します。
         * 
         * このリゾルバーは{@link Configuration}を構築するために評価されたプロバイダのリストを返します。
         * <p>
         * リゾルバーが定義されていない場合はデフォルトの{@link ValidationProviderResolver}の実装が使用されます。
	 *
	 * @param resolver 自動実行に使用する {@code ValidationProviderResolver}
	 * @return 連鎖するメソッドのパターンに従った {@code this}
	 */
	GenericBootstrap providerResolver(ValidationProviderResolver resolver);

	/**
         * 汎用の {@link Configuration} 実装を返します。
         * 
         * この段階では{@link ValidatorFactory}の構築に使用されたプロバイダは定義されていません。
         * <p>
         * {@code Configuration}の実装は{@link ValidationProviderResolver}戦略によって返された最初のプロバイダによって提供されます。
	 *
	 * @return 自動実行の状態に準拠した{@code Configuration}の実装
	 * @throws NoProviderFoundException Bean Validationのプロバイダが存在しない場合
	 * @throws ValidationException Bean Validationのプロバイダが見つかったが、 {@code Configuration}オブジェクトが構築できない場合。
         * これは一般に {@code ValidationProviderResolver}の問題が原因です。
	 */
	Configuration<?> configure();
}
