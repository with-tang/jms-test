package com.imooc.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by tang on 2017/8/5.
 */
public class AppConsumer {
    private static final String url="tcp://127.0.0.1:61616";
    private static final String topicName="topic-test";
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
        Destination destination=session.createTopic(topicName);
        //创建生产者
        MessageConsumer consumer=session.createConsumer(destination);
       //创建监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage=(TextMessage)message;
                try {
                    System.out.println("接收消息：" + textMessage.getText());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        //关闭连接
        //connection.close();
    }
}
