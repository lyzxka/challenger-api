package io.renren.rabbitmq.service.impl;

import io.renren.rabbitmq.service.Producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: template-api
 * @description:
 * @author: ZhangLei
 * @create: 2019-01-25 00:02
 **/
@Service
public class ProducerImpl implements Producer{

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(String queue, Object message) {
        rabbitTemplate.setQueue(queue);
        rabbitTemplate.convertAndSend(queue,message);
    }
}
