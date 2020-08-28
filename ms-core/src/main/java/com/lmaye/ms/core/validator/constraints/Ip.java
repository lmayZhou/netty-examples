package com.lmaye.ms.core.validator.constraints;

import com.lmaye.ms.core.validator.IpValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * -- IP 校验注解
 *
 * @author lmay.Zhou
 * @date 2018/12/5 15:25 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = IpValidator.class)
public @interface Ip {

    String message() default "{Ip.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Ip[] value();
    }
}
