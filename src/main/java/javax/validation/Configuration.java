/*
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package javax.validation;

import java.io.InputStream;

import javax.validation.spi.ValidationProvider;
import javax.validation.valueextraction.ValueExtractor;
import javax.validation.valueextraction.ValueExtractorDeclarationException;

/**
 * 設定情報を受け取り、適切なBean検証プロバイダの選択と適切な{@link ValidatorFactory}の構築を行います。
 * <p>
 * 使用方法:
 * <pre>
 * //Validationの自動実行メソッドのひとつから提供される
 * Configuration&lt;?&gt; configuration =
 *     ValidatorFactory = configuration
 *         .messageInterpolator( new CustomMessageInterpolator() )
 *         .buildValidatorFactory();
 * </pre>
 * <p>
 * デフォルトでは{@code META-INF/validation.xml}から設定情報が取得されます。
 * {@code Configuration}のいくつかのメソッドを使用することでXMLファイルから受け取った設定を上書きすることが出来ます。
 * <p>
 * {@link ValidationProviderResolver}は設定時に指定されます。({@link ValidationProvider}を参照してください。)
 * 明示的な要求がない場合はデフォルトの{@code ValidationProviderResolver}が使用されます。
 * <p>
 * プロバイダーは次の方法で選択されます。
 * <ul>
 *     <li>{@link Validation#byProvider(Class)}を使用してプログラムで特定のプロバイダーが要求された場合、
 *     要求されたプロバイダーのクラスで最初に見つかった実装クラスを使用します。</li>
 *     <li>{@code META-INF/validation.xml}で特定のプロバイダーが要求された場合、
 *     要求されたプロバイダーのクラスで最初に見つかった実装クラスを使用します。</li>
 *     <li>それ以外の場合、{@code ValidationProviderResolver}で返された最初のプロバイダーを使用します。</li>
 * </ul>
 * <p>
 * 実装がスレッドセーフであるとは限りません。
 *
 * @param <T> この契約に特化したプロバイダ提供の型
 *
 * @author Emmanuel Bernard
 * @author Gunnar Morling
 * @author Hardy Ferentschik
 * @author Guillaume Smet
 */
public interface Configuration<T extends Configuration<T>> {

	/**
         * このメソッドを呼び出すことで{@code META-INF/validation.xml}ファイルからのデータを無視します。
	 * <p>
         * このメソッドは通常、{@code META-INF/validation.xml}自体を解析し、{@code Configuration}のメソッドを介して情報を渡すコンテナで便利です。
	 *
	 * @return メソッドチェーンパターンに従った {@code this}
	 */
	T ignoreXmlConfiguration();

	/**
         * 使用されるメッセージインターポレーターを定義します。
         * 
         * 設定ベースのメッセージインターポレーターよりも優先されます。
	 * <p>
         * {@code null}が渡された場合、(XML内で定義されたものもしくは仕様のデフォルトによる)デフォルトのメッセージインターセプターが使用されます。
	 *
	 * @param interpolator メッセージインターポレーターの実装
	 * @return メソッドチェーンパターンに従った {@code this}
	 */
	T messageInterpolator(MessageInterpolator interpolator);

	/**
         * 使用される横断リゾルバを定義します。
         * 
         * 設定ベースの横断リゾルバよりも優先されます。
	 * <p>
         * {@code null}が渡された場合、(XML内で定義されたものもしくは仕様のデフォルトによる)デフォルトの横断リゾルバが使用されます。
	 *
	 * @param resolver 横断リゾルバの実装
	 * @return メソッドチェーンパターンに従った {@code this}
	 */
	T traversableResolver(TraversableResolver resolver);

	/**
         * 使用される制約バリデーターファクトリーを定義します。
         * 
         * 設定ベースの制約バリデーターファクトリーよりも優先されます。
	 * <p>
         * {@code null}が渡された場合、(XML内で定義されたものもしくは仕様のデフォルトによる)デフォルトの制約バリデーターファクトリーが使用されます。
	 *
	 * @param constraintValidatorFactory 制約バリデーターファクトリーの実装
	 * @return メソッドチェーンパターンに従った {@code this}
	 */
	T constraintValidatorFactory(ConstraintValidatorFactory constraintValidatorFactory);

	/**
         * 使用されるパラメーター名プロバイダーを定義します。
         * 
         * 設定ベースのパラメーター名プロバイダーよりも優先されます。
	 * <p>
         * {@code null}が渡された場合、(XML内で定義されたものもしくは仕様のデフォルトによる)デフォルトのパラメーター名プロバイダーが使用されます。
	 *
	 * @param parameterNameProvider パラメーター名プロバイダーの実装
	 * @return メソッドチェーンパターンに従った {@code this}
	 *
	 * @since 1.1
	 */
	T parameterNameProvider(ParameterNameProvider parameterNameProvider);

	/**
         * 使用される時刻プロバイダーを定義します。
         * 
         * 設定ベースの時刻プロバイダーよりも優先されます。
	 * <p>
         * {@code null}が渡された場合、(XML内で定義されたものもしくは仕様のデフォルトによる)デフォルトの時刻プロバイダーが使用されます。
	 *
	 * @param clockProvider 時刻プロバイダーの実装
	 * @return メソッドチェーンパターンに従った {@code this}
	 *
	 * @since 2.0
	 */
	T clockProvider(ClockProvider clockProvider);

	/**
	 * Adds a value extractor. Has priority over any extractor for the same
	 * type and type parameter detected through the service loader or given in
	 * the XML configuration.
	 *
	 * @param extractor 値エクストラクターの実装
	 * @return メソッドチェーンパターンに従った {@code this}
	 * @throws ValueExtractorDeclarationException if more than one extractor for
	 *         the same type and type parameter is added
	 * @since 2.0
	 */
	T addValueExtractor(ValueExtractor<?> extractor);

	/**
	 * Add a stream describing constraint mapping in the Bean Validation XML
	 * format.
	 * <p>
	 * The stream should be closed by the client API after the
	 * {@link ValidatorFactory} has been built. The Bean Validation provider
	 * must not close the stream.
	 *
	 * @param stream
	 *        XML mapping stream; the given stream should support the
	 *        mark/reset contract (see {@link InputStream#markSupported()});
	 *        if it doesn't, it will be wrapped into a stream supporting the
	 *        mark/reset contract by the Bean Validation provider
	 *
	 * @return メソッドチェーンパターンに従った {@code this}
	 * @throws IllegalArgumentException if {@code stream} is null
	 */
	T addMapping(InputStream stream);

	/**
	 * Adds a provider specific property. This property is equivalent to
	 * XML configuration properties.
	 * If the underlying provider does not know how to handle the property,
	 * it must silently ignore it.
	 * <p>
	 * Note: Using this non type-safe method is generally not recommended.
	 * <p>
	 * It is more appropriate to use, if available, the type-safe equivalent provided
	 * by a specific provider via its {@link Configuration} subclass.
	 * <pre>
	 * ValidatorFactory factory = Validation.byProvider(ACMEProvider.class)
	 *     .configure()
	 *         .providerSpecificProperty(ACMEState.FAST)
	 *     .buildValidatorFactory();
	 * </pre>
	 * This method is typically used by containers parsing {@code META-INF/validation.xml}
	 * themselves and injecting the state to the {@code Configuration} object.
	 * <p>
	 * If a property with a given name is defined both via this method and in the
	 * XML configuration, the value set programmatically has priority.
	 * <p>
	 * If {@code null} is passed as a value, the value defined in XML is used. If no value
	 * is defined in XML, the property is considered unset.
	 *
	 * @param name property name
	 * @param value property value
	 * @return メソッドチェーンパターンに従った {@code this}
	 * @throws IllegalArgumentException if {@code name} is null
	 */
	T addProperty(String name, String value);

	/**
	 * Returns an implementation of the {@link MessageInterpolator} interface
	 * following the default {@code MessageInterpolator} defined in the
	 * specification:
	 * <ul>
	 *     <li>use the {@code ValidationMessages} resource bundle to load keys</li>
	 *     <li>use {@code Locale.getDefault()}</li>
	 * </ul>
	 *
	 * @return default {@code MessageInterpolator} implementation compliant with the
	 *         specification
	 */
	MessageInterpolator getDefaultMessageInterpolator();

	/**
	 * Returns an implementation of the {@link TraversableResolver} interface
	 * following the default {@code TraversableResolver} defined in the
	 * specification:
	 * <ul>
	 *     <li>if Java Persistence is available in the runtime environment,
	 *     a property is considered reachable if Java Persistence considers
	 *     the property as loaded</li>
	 *     <li>if Java Persistence is not available in the runtime environment,
	 *     all properties are considered reachable</li>
	 *     <li>all properties are considered cascadable.</li>
	 * </ul>
	 *
	 * @return default {@code TraversableResolver} implementation compliant with the
	 *         specification
	 */
	TraversableResolver getDefaultTraversableResolver();

	/**
	 * Returns an implementation of the {@link ConstraintValidatorFactory} interface
	 * following the default {@code ConstraintValidatorFactory} defined in the
	 * specification:
	 * <ul>
	 *     <li>uses the public no-arg constructor of the {@link ConstraintValidator}</li>
	 * </ul>
	 *
	 * @return default {@code ConstraintValidatorFactory} implementation compliant with the
	 *         specification
	 */
	ConstraintValidatorFactory getDefaultConstraintValidatorFactory();

	/**
	 * Returns an implementation of the {@link ParameterNameProvider}
	 * interface following the default {@code ParameterNameProvider}
	 * defined in the specification:
	 * <ul>
	 *     <li>returns the actual parameter names as provided in the validated
	 *     executable’s definition, if the class file of the executable contains
	 *     parameter name information</li>
	 *     <li>
	 *     otherwise returns names in the form {@code arg&lt;PARAMETER_INDEX&gt;},
	 *     where {@code PARAMETER_INDEX} starts at 0 for the first parameter,
	 *     e.g. {@code arg0}, {@code arg1} etc.</li>
	 * </ul>
	 *
	 * @return default {@code ParameterNameProvider} implementation compliant with
	 *         the specification
	 *
	 * @since 1.1
	 */
	ParameterNameProvider getDefaultParameterNameProvider();

	/**
	 * Returns an implementation of the {@link ClockProvider}
	 * interface following the default {@code ClockProvider}
	 * defined in the specification:
	 * <ul>
	 *     <li>returns a clock representing the current system time and default time
	 *     zone.</li>
	 * </ul>
	 *
	 * @return default {@code ClockProvider} implementation compliant with
	 *         the specification
	 *
	 * @since 2.0
	 */
	ClockProvider getDefaultClockProvider();

	/**
	 * Returns configuration information stored in the {@code META-INF/validation.xml} file.
	 * <p>
	 * <b>Note</b>:
	 * <br>
	 * Implementations are encouraged to lazily build this object to delay parsing.
	 *
	 * @return returns an instance of {@link BootstrapConfiguration}; this method never
	 *         returns {@code null}; if there is no {@code META-INF/validation.xml} the
	 *         different getters of the returned instance will return {@code null}
	 *         respectively an empty set or map
	 *
	 * @since 1.1
	 */
	BootstrapConfiguration getBootstrapConfiguration();

	/**
	 * Build a {@link ValidatorFactory} implementation.
	 *
	 * @return the {@code ValidatorFactory}
	 * @throws ValidationException if the {@code ValidatorFactory} cannot be built
	 */
	ValidatorFactory buildValidatorFactory();
}
