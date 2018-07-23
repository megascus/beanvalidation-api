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
import javax.validation.constraints.Pattern.List;

/**
 * アノテーションの付けられた{@code CharSequence}は指定された正規表現と一致しなければなりません。
 * 正規表現はJavaの正規表現の規則に従います。{@link java.util.regex.Pattern}を参照してください。
 * <p>
 * {@code CharSequence}を受け付けます。
 * {@code null}要素は有効とみなされます。
 *
 * @author Emmanuel Bernard
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(List.class)
@Documented
@Constraint(validatedBy = { })
public @interface Pattern {

	/**
	 * @return 一致する正規表現
	 */
	String regexp();

	/**
	 * @return 正規表現を解決するときに考慮される{@code Flag}の配列
	 */
	Flag[] flags() default { };

	/**
	 * @return エラーメッセージのテンプレート
	 */
	String message() default "{javax.validation.constraints.Pattern.message}";

	/**
	 * @return 制約の所属するグループ
	 */
	Class<?>[] groups() default { };

	/**
	 * @return 制約に関連付けられたペイロード
	 */
	Class<? extends Payload>[] payload() default { };

	/**
         * 可能なRegexpのフラグ。
	 */
	public static enum Flag {

		/**
                 * Unixライン・モードを有効にします。
		 *
		 * @see java.util.regex.Pattern#UNIX_LINES
		 */
		UNIX_LINES( java.util.regex.Pattern.UNIX_LINES ),

		/**
		 * 大文字と小文字を区別しないマッチングを有効にします。
		 *
		 * @see java.util.regex.Pattern#CASE_INSENSITIVE
		 */
		CASE_INSENSITIVE( java.util.regex.Pattern.CASE_INSENSITIVE ),

		/**
		 * パターン内で空白とコメントを使用できるようにします。
		 *
		 * @see java.util.regex.Pattern#COMMENTS
		 */
		COMMENTS( java.util.regex.Pattern.COMMENTS ),

		/**
		 * 複数行モードを有効にします。
		 *
		 * @see java.util.regex.Pattern#MULTILINE
		 */
		MULTILINE( java.util.regex.Pattern.MULTILINE ),

		/**
		 * DOTALLモードを有効にします。
		 *
		 * @see java.util.regex.Pattern#DOTALL
		 */
		DOTALL( java.util.regex.Pattern.DOTALL ),

		/**
		 * Unicodeに準拠した大文字と小文字を区別しないマッチングを有効にします。
		 *
		 * @see java.util.regex.Pattern#UNICODE_CASE
		 */
		UNICODE_CASE( java.util.regex.Pattern.UNICODE_CASE ),

		/**
		 * 正規等価を有効にします。
		 *
		 * @see java.util.regex.Pattern#CANON_EQ
		 */
		CANON_EQ( java.util.regex.Pattern.CANON_EQ );

		//JDK flag value
		private final int value;

		private Flag(int value) {
			this.value = value;
		}

		/**
		 * @return {@link java.util.regex.Pattern}で定義されたフラグの値
		 */
		public int getValue() {
			return value;
		}
	}

	/**
         * 同じ要素に複数の{@link Pattern}アノテーションを定義します。
	 *
	 * @see Pattern
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		Pattern[] value();
	}
}
