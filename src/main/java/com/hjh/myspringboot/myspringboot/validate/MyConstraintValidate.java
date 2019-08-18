package com.hjh.myspringboot.myspringboot.validate;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-16 23:50
 */
@Slf4j
public class MyConstraintValidate implements ConstraintValidator<MyConstraint, Object> {
    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        //初始化
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        log.info("MyConstraintValidate: ",value);
        return false;
    }
}
