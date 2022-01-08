package kr.or.ddit.annotation.stereotype;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(TYPE)
@Retention(RUNTIME)
public @interface MultiValueAnnotation {
	String value() default "text"; // 필수 속성
	int number() default 12; // 옵션 속성
	float floatNumber() default 2.0f;
}
