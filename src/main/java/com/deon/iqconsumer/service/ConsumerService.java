package com.deon.iqconsumer.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to listen for messages on a specified queue on a rabbitMQ node
 */
public class ConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    private ConnectionFactory cf;
private String host;

    /**
     *
     * @param host the host where the rabbitMQ node is running.
     */
    public ConsumerService(String host) {
        if (host.isEmpty()) throw new IllegalArgumentException("Host cannot be empty");
        this.host = host;
        this.cf = new ConnectionFactory();
        this.cf.setHost(host);
    }

    /**
     *
     * @param queue the target queue name for listening
     */
    public void listen(String queue) {
        if (queue.isEmpty()) throw new IllegalArgumentException("queue cannot be empty");
        try {
            Connection conn = this.cf.newConnection();
            Channel channel = conn.createChannel();
            channel.queueDeclare(queue, false, false, false, null);
            System.out.println("Listening for name (Press CTRL+C to exit");

        DeliverCallback dc = (consumerTag, delivery) -> {
            String name = new String(delivery.getBody(), "UTF-8");
            System.out.println("Hello " + name + "' I am your father!");
        };
        channel.basicConsume(queue, true, dc, consumerTag -> { });

        } catch (Exception e) {
           logger.error("Error", e);
        }

    }

    public String getHost() {
        return  this.host;
    }

    }
