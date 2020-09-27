package com.lmaye.ms.starter.rabbitmq;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

/**
 * -- Rabbit Mq Auto Configuration
 *
 * @author lmay.Zhou
 * @date 2020/9/27 14:02 星期日
 * @since Email: lmay_zlm@meten.com
 */
@Configuration
@ConditionalOnBean(RabbitProperties.class)
@AutoConfigureAfter(org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration.class)
public class RabbitMqAutoConfiguration {
}
