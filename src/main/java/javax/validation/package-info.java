/*
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
/**
 * 
 * Bean Validation APIのトップレベルのパッケージです。
 *
 * 主な実行時アーティファクトは以下の通りです。
 * <ul>
 *     <li>{@link javax.validation.Validation}: Bean Validation自動実行のエントリポイント</li>
 *     <li>{@link javax.validation.ValidatorFactory}: 自動実行されるBean Validationのエンジン</li>
 *     <li>{@link javax.validation.Validator}: Beanを検証してメタデータにアクセスする制約</li>
 *     <li>{@link javax.validation.ConstraintViolation}: 制約違反のレポート</li>
 * </ul>
 *
 * 主な制約定義のアーティファクトは以下の通りです。
 * <ul>
 *     <li>{@link javax.validation.Constraint}: アノテーションを制約として設定するアノテーション</li>
 *     <li>{@link javax.validation.ConstraintValidator}: 指定された制約を検証するコードによって実装されるインタフェース</li>
 *     <li>{@link javax.validation.ReportAsSingleViolation}: 複合制約を単一の制約違反としてレポートするように指定します</li>
 * </ul>
 *
 * 主な制約宣言のアーティファクトは以下の通りです。
 * <ul>
 *     <li>{@link javax.validation.Valid}: 関連に検証中にカスケードされることをマークするアノテーション</li>
 *     <li>{@link javax.validation.GroupSequence}:順番に検証されるべき一連のグループを定義します</li>
 * </ul>
 */
package javax.validation;
