package com.lmaye.ms.core.validator;

import com.lmaye.ms.core.validator.constraints.Mobile;
import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * -- 手机号校验
 *
 * @author lmay.Zhou
 * @date 2018/12/5 15:25 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    private static final Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");

    /**
     * 校验手机号码
     *
     * @param mobile 手机号码
     * @return boolean
     */
    public static boolean isMobile(String mobile) {
        return MOBILE_PATTERN.matcher(mobile).matches();
    }

    @Override
    public void initialize(Mobile constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Strings.isBlank(value) || isMobile(value);
    }
}
