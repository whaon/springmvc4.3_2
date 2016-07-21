package com.mileweb.glb.apiserver.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

//实际校验实现
@Constraint(validatedBy = { DogNameValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DogNameDuplicate {
	//该属性为message的key值，参见国际化相关内容
	String message() default "{javax.validation.constraints.Max.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	String[] init() default {};

}