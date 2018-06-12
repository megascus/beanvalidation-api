/*
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package javax.validation.bootstrap;

import javax.validation.Configuration;
import javax.validation.ValidationException;
import javax.validation.ValidationProviderResolver;
import javax.validation.spi.ValidationProvider;

/**
 * Bean Validationの自動起動に使用される状態を定義し、プロバイダ固有の{@code T}型の{@link Configuration}を作ります。
 * 
 * <p>
 * 個別の{@code Configuration}は{@link ValidationProvider}の実装の汎用パラメーターを介してプロバイダにリンクされています。
 * <p>
 * 要求されたプロバイダは({@link ProviderSpecificBootstrap}の構築時に知られる)要求されたプロバイダの型に割り当てることが出来る最初のプロバイダです。
 * 評価されたプロバイダのリストは{@link ValidationProviderResolver}によって返されます。
 * {@code ValidationProviderResolver}が定義されていない場合、デフォルトの{@code ValidationProviderResolver}戦略が使用されます。
 *
 * @param <T> プロバイダ固有の{@link Configuration}型
 *
 * @author Emmanuel Bernard
 */
public interface ProviderSpecificBootstrap<T extends Configuration<T>> {

	/**
         * オプションで使用されるプロバイダのリゾルバー実装を定義します。 
         * 定義されていない場合はデフォルトの{@link ValidationProviderResolver}を使用します。
	 *
	 * @param resolver 使用される{@code ValidationProviderResolver}の実装
	 *
	 * @return 連鎖するメソッドのパターンに従った {@code this}
	 */
	public ProviderSpecificBootstrap<T> providerResolver(
			ValidationProviderResolver resolver);

	/**
         * {@code T}に適したプロバイダの実装を決定し、この個別の{@link Configuration}のサブクラスの構築をプロバイダに委譲します。
	 *
	 * @return {@code Configuration}のサブインターフェースの実装
	 *
	 * @throws ValidationException {@code Configuration}オブジェクトが構築できない場合。
         * これは一般に {@code ValidationProviderResolver}の問題が原因です。
	 */
	public T configure();
}
