package io.renren.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消费者队列配置
 */
@Configuration
public class RabbitQueue {

    @Autowired
    RabbitConfig rabbitconfig;

    /**
     * 测试队列,后续仿造此方法复制即可
     * @return
     */
    @Bean
    public Queue myQueue() {
        Queue queue = new Queue("myqueue");
        return queue;
    }

}
