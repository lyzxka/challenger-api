package io.renren.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: template-api
 * @description:
 * @author: ZhangLei
 * @create: 2019-01-24 23:53
 **/
@Component
public class MyqueueListener {

    /**
     * 测试消费者
     * @param mail
     * @throws Exception
     */
    @RabbitListener(queues = "myqueue")
    public void displayMail(String mail) throws Exception {
        System.out.println("队列监听器1号收到消息"+mail);
    }

}
