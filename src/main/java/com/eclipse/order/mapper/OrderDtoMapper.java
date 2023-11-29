package com.eclipse.order.mapper;

import com.eclipse.order.dtos.OrderDto;
import com.eclipse.order.exceptions.MappingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

public class OrderDtoMapper {

    public static OrderDto tOrderDto(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OrderDto orderDto = null;
        try {
            orderDto = objectMapper.readValue(message, OrderDto.class);
        } catch (JsonProcessingException e) {
            throw new MappingException("Error while processing JSON in JMS message", e);
        }
        return orderDto;
    }
}
