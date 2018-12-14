package com.desk.otter.pdfprocessor.queueconfig;

import com.desk.otter.pdfprocessor.receiver.PDFReceiver;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

@Configuration
@PropertySource("classpath:queue.properties")
public class QueueConfiguration {

    Logger logger = LoggerFactory.getLogger(QueueConfiguration.class);
    private static final String LISTENER_METHOD = "processMessage";

    @Value("${direct.exchange}")
    private String directExchange;

    @Value("${queue.name}")
    private String queueName;

    @Value("${routing.key}")
    private String routingKey;

    @Value("${cloud.rabbit.url}")
    private String rabbitUrl;

    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    public Queue ackqueue() {
        return new Queue("ackQueue", true);
    }

    @Bean
    public DirectExchange ackexchange() {
        return new DirectExchange("ackExchange");
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(directExchange);
    }

    @Bean
    MessageListenerAdapter listenerAdapter(PDFReceiver consumer) {
        return new MessageListenerAdapter(consumer, LISTENER_METHOD);
    }

    @Bean
    public Channel binding(Queue queue, DirectExchange exchange) {
        /*CachingConnectionFactory factory = connectionFactory();
        Channel channel = null;
        Connection connection = factory.createConnection();
        channel = connection.createChannel(true);*/

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        Channel channel = null;
        Channel ackchannel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            channel.exchangeDeclare(directExchange, "direct", true);
            channel.queueBind(queueName, directExchange, routingKey);

            ackchannel.exchangeDeclare("ackExchange", "direct", true);
            ackchannel.queueBind("ackQueue", "ackExchange", "acknowledgement");

        } catch (Exception e) {
            System.out.println("Exception in Queue Binding");
        }
        return channel;
    }

   /* @Bean
    public CachingConnectionFactory connectionFactory() {
       *//* URI rabbitMQURI = null;
        try {
            rabbitMQURI = new URI("localhost");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUri(rabbitMQURI);
        return factory;*//*
    }*/

    @Bean
    public SimpleMessageListenerContainer listenerContainer(CachingConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue().getName());
        container.setMessageListener(messageListenerAdapter);
        return container;

    }

    @Bean
    public SimpleMessageListenerContainer ackListenerContainer(CachingConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("ackQueue");
        container.setMessageListener(messageListenerAdapter);
        return container;

    }


}
