package com.yaoch.common.utils;

import com.google.common.collect.Lists;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * @author wbc
 * @date 2018/9/13
 * @desc bean属性检查
 */
@SuppressWarnings("unused")
public class BeanValidators {
    public BeanValidators() {
    }

    public static void validateWithException(Validator validator, Object object, Class... groups) throws ConstraintViolationException {
	    Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
	    if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }


    public static List<ConstraintViolation> getErrorMessage(Set<? extends ConstraintViolation> constraintViolations) {
        List<ConstraintViolation> errorMessages = Lists.newArrayList();
        errorMessages.addAll(constraintViolations);
        return errorMessages;
    }
}
