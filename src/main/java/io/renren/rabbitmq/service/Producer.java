package io.renren.rabbitmq.service;

/**
 * @program: template-api
 * @description:
 * @author: ZhangLei
 * @create: 2019-01-25 00:01
 **/
public interface Producer {

    public void sendMessage(String queue,Object message);

}
