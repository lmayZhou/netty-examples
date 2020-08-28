package com.lmaye.ms.core.validator;

import com.lmaye.ms.core.validator.constraints.No;
import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


/**
 * -- 编号校验
 *
 * @author lmay.Zhou
 * @date 2018/12/5 15:25 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
public class NoValidator implements ConstraintValidator<No, String> {

    private static final Pattern NO_PATTERN = Pattern.compile("(?!_)(?!-)(\\w|-){1,64}");

    /**
     * 校验编号
     *
     * @param code 编号
     * @return 通过返回true，不通过返回false
     */
    public static boolean isNo(String code) {
        return NO_PATTERN.matcher(code).matches();
    }

    @Override
    public void initialize(No constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Strings.isBlank(value) || isNo(value);
    }
}
