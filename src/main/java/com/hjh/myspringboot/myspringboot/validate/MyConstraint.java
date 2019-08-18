package com.hjh.myspringboot.myspringboot.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-16 23:48
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidate.class)
public @interface MyConstraint {

    String message() default "{javax.validation.constraints.Past.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
