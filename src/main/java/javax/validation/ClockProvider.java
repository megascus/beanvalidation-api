/*
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package javax.validation;

import java.time.Clock;

/**
 * {@code @Future} 制約と {@code @Past}制約を検証するときに、 "現在"への参照として使用されている{@link Clock}を取得するための契約です。
 * <p>
 * デフォルト実装では現在のシステム時刻を返します。
 * カスタム実装を差し込むことは、特定の論理日付で実行する必要のあるバッチアプリケーション(たとえば、昨日失敗したバッチジョブを再実行するとき)で有用です。
 * <p>
 * 実装はスレッドセーフである必要があります。
 *
 * @author Gunnar Morling
 * @author Guillaume Smet
 * @since 2.0
 */
public interface ClockProvider {

	/**
         * "現在"への参照として機能する時計を返します。
	 *
	 * @return "現在"への参照として機能する時計、{@code null}であってはなりません
	 */
	Clock getClock();
}
