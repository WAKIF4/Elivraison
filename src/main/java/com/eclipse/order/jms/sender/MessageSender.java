package com.eclipse.order.jms.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.TextMessage;

@Service
public class MessageSender {
    
    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String messageText, String destination) {


        jmsTemplate.send(destination, session -> {
            TextMessage message = session.createTextMessage(messageText);
            return message;
        });
    }
}
