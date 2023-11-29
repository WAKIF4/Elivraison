package com.eclipse.order.transformers;

import java.util.List;
import java.util.stream.Collectors;

import com.eclipse.order.dtos.OrderDto;
import com.eclipse.order.entities.Order;

public class OrderTransformer {

    public static Order toEntity(OrderDto dto) {
        Order entity = Order.builder()
            .id(dto.getId())
            .products(dto.getCommandItems())
            .status(dto.getStatus())
            .customer(dto.getClient())
            .totalPrice(dto.getTotalPaye())
            .build();

            return entity;
    }

    public static OrderDto toDto(Order entity) {
        OrderDto dto = OrderDto.builder()
            .id(entity.getId())
            .commandItems(entity.getProducts())
            .client(entity.getCustomer())
            .deliveryPerson(entity.getDeliveryPerson() != null ? DeliveryPersonTransformers.toDto(entity.getDeliveryPerson()) : null)
            .status(entity.getStatus())
            .totalPaye(entity.getTotalPrice())
            .build();

        return dto;
    }

    public static List<OrderDto> toDtoList(List<Order> entities) {
        return entities.stream().map(entity -> toDto(entity)).collect(Collectors.toList());
    }
    
}
