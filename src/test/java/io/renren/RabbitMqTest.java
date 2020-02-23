package io.renren;

import io.renren.rabbitmq.service.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: template-api
 * @description:
 * @author: ZhangLei
 * @create: 2019-01-25 16:12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

    @Autowired
    Producer producer;

    @Test
    public void test(){
        producer.sendMessage("myqueue","hello world");
    }

}
