/*
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package javax.validation;

import java.util.Map;
import java.util.Set;

import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.validation.spi.ValidationProvider;
import javax.validation.valueextraction.ValueExtractor;

/**
 * {@code META-INF/validation.xml} でユーザーが指定したデフォルトの構成を表します。
 * <p>
 * 返されるオブジェクトへの変更は何も効果のないことに注意してください。
 * 代わりに{@link Configuration}に変更を適用するために{@link Configuration}で提供されているメソッドを使用します。
 *
 * @author Emmanuel Bernard
 * @author Gunnar Morling
 * @author Hardy Ferentschik
 * @author Guillaume Smet
 * @since 1.1
 */
public interface BootstrapConfiguration {

	/**
         * {@link ValidationProvider}の実装のクラス名、指定されていない場合は{@code null}。
	 *
	 * @return 検証プロバイダのクラス名
	 */
	String getDefaultProviderClassName();

	/**
         * {@link ConstraintValidatorFactory}の実装のクラス名、指定されていない場合は{@code null}。
	 *
	 * @return 制約バリデーターファクトリーのクラス名
	 */
	String getConstraintValidatorFactoryClassName();

	/**
         * {@link MessageInterpolator}の実装のクラス名、指定されていない場合は{@code null}。
	 *
	 * @return メッセージインターセプターのクラス名もしくは{@code null}
	 */
	String getMessageInterpolatorClassName();

	/**
         * {@link TraversableResolver}の実装のクラス名、指定されていない場合は{@code null}。
	 *
	 * @return 横断リゾルバのクラス名もしくは {@code null}
	 */
	String getTraversableResolverClassName();

	/**
         * {@link ParameterNameProvider}の実装のクラス名、指定されていない場合は{@code null}。
	 *
	 * @return パラメーター名プロバイダーのクラス名もしくは {@code null}
	 */
	String getParameterNameProviderClassName();

	/**
         * {@link ClockProvider}の実装のクラス名、指定されていない場合は{@code null}。
	 *
	 * @return 時刻プロバイダーのクラス名もしくは {@code null}
	 *
	 * @since 2.0
	 */
	String getClockProviderClassName();

	/**
         * {@link ValueExtractor}のクラス名を返します。
	 *
	 * @return 値エクストラクターのクラス名、指定されていない場合は空のセット
	 * @since 2.0
	 */
	Set<String> getValueExtractorClassNames();

	/**
         * XML制約マッピングファイルを指すリソースパスのセットを返します。
         * 
         * 指定されていない場合、セットは空です。
	 *
	 * @return 制約マッピングリソースへのパスのセット
	 */
	Set<String> getConstraintMappingResourcePaths();

	/**
         * 検証の実行が明示的に有効と指定されている場合、もしくは検証を実行するかどうか未指定のままである場合はtrueを返します。
	 *
	 * @return 検証の実行がグローバルで有効かどうか
	 */
	boolean isExecutableValidationEnabled();

	/**
         * {@link ValidateOnExecution}で明示的にオーバーライドされない限り、考慮する必要がある実行可能な型のセットを返します。
	 * <p>
         * 設定で指定されていない場合は、{@link ExecutableType#CONSTRUCTORS}と{@link ExecutableType#NON_GETTER_METHODS}を含むセットを返します。
	 *
	 * @return 検証された実行可能な型のセット
	 */
	Set<ExecutableType> getDefaultValidatedExecutableTypes();

	/**
         * 文字列ベースのキーバリューペアのマップとしてプロパティを返します。
         * 
         * プロパティが指定されていない場合、マップは空です。
	 *
	 * @return プロパティのマップ
	 */
	Map<String, String> getProperties();
}
