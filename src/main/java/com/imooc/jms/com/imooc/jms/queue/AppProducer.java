package com.imooc.jms.com.imooc.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by tang on 2017/8/5.
 */
public class AppProducer {
    private static final String url="tcp://127.0.0.1:61616";
    private static final String queueName="queue-test";
    public static  void main(String[] args)throws Exception{
        //创建链接工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);
        //创建链接
        Connection connection=connectionFactory.createConnection();
        //打开链接
        connection.start();
        //创建会话
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建目标
        Destination destination=session.createQueue(queueName);
        //创建生产者
        MessageProducer producer=session.createProducer(destination);
        for(int i=0;i<100;i++){
            TextMessage message=session.createTextMessage("test:"+i);
            producer.send(message);
            System.out.println("text send:"+message.getText());
        }

        //关闭连接
        connection.close();
    }
}
