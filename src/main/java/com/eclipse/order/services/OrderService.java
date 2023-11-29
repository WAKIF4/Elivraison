package com.eclipse.order.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eclipse.order.dtos.OrderDto;
import com.eclipse.order.entities.DeliveryPerson;
import com.eclipse.order.entities.Order;
import com.eclipse.order.enums.OrderStatus;
import com.eclipse.order.exceptions.NotFoundExcption;
import com.eclipse.order.jms.sender.MessageSender;
import com.eclipse.order.repositories.DeliveryPersonRepository;
import com.eclipse.order.repositories.OrderRepository;
import com.eclipse.order.transformers.DeliveryPersonTransformers;
import com.eclipse.order.transformers.OrderTransformer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final DeliveryPersonRepository deliveryPersonRepository;

    @Value("${spring.queue.order.dead}")
    private String destination;

    public void createOrder(OrderDto orderDto) {
        orderDto.setStatus(OrderStatus.PENDING);;
        orderRepository.save(OrderTransformer.toEntity(orderDto));
    }

    public List<OrderDto> getAllOrders() {
        return OrderTransformer.toDtoList(orderRepository.findAll());
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundExcption("Order not found"));
        return OrderTransformer.toDto(order);
    }

    private final MessageSender messageSender;
    public OrderDto updateStatus(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundExcption("Order not found"));
        order.setStatus(order.getStatus() == OrderStatus.PENDING ? OrderStatus.DELIVERED : OrderStatus.PENDING);
        orderRepository.save(order);
        messageSender.sendMessage("{\"id\":" + order.getId() + ",\"status\":\"" + order.getStatus() + "\"}",destination);
        return OrderTransformer.toDto(order);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundExcption("Order not found"));
        orderRepository.delete(order);
    }

    public OrderDto updateOrder(OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getId()).orElseThrow(() -> new NotFoundExcption("Order not found"));
        order.setCustomer(orderDto.getClient());
        order.setProducts(orderDto.getCommandItems());
        order.setTotalPrice(orderDto.getTotalPaye());
        orderRepository.save(order);
        return OrderTransformer.toDto(order);
    }

    public OrderDto addDeliveryPerson(Long id, Long deliveryPersonId) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundExcption("Order not found"));
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(deliveryPersonId)
                .orElseThrow(() -> new NotFoundExcption("Delivery Person not found"));
        order.setDeliveryPerson(DeliveryPersonTransformers.toEntity(DeliveryPersonTransformers.toDto(deliveryPerson)));
        orderRepository.save(order);
        return OrderTransformer.toDto(order);
    }
}
