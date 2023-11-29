package com.eclipse.order.jms.reciver;

import com.eclipse.order.services.OrderService;
import com.eclipse.order.dtos.OrderDto;
import com.eclipse.order.exceptions.MappingException;
import com.eclipse.order.jms.sender.MessageSender;
import com.eclipse.order.mapper.OrderDtoMapper;


import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderReciver {
    private final OrderService orderService;
    private final MessageSender messageSender;

    @Value("${spring.queue.order.dead}")
    private String destinationDead;


    @JmsListener(destination = "CommandeQueue", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message)  {
        System.out.println("Received :)");
        try {
            OrderDto orderDto = OrderDtoMapper.tOrderDto(message);
            orderDto.isValidObject();
            orderService.createOrder(orderDto);
        } catch (MappingException e) {
            messageSender.sendMessage("Error while processing JSON in JMS message " + e.getMessage(),destinationDead);
        } catch (IllegalArgumentException e) {
            messageSender.sendMessage("Error while validating JSON in JMS message " + e.getMessage(),destinationDead);
        } catch (Exception e) {
            messageSender.sendMessage("Error while Store Data " + e.getMessage(),destinationDead);
        }
    }
}
