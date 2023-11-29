package com.eclipse.order.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eclipse.order.dtos.DeliveryPersonDto;
import com.eclipse.order.dtos.OrderDto;
import com.eclipse.order.entities.DeliveryPerson;
import com.eclipse.order.entities.Order;
import com.eclipse.order.exceptions.NotFoundExcption;
import com.eclipse.order.repositories.DeliveryPersonRepository;
import com.eclipse.order.repositories.OrderRepository;
import com.eclipse.order.transformers.DeliveryPersonTransformers;
import com.eclipse.order.transformers.OrderTransformer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeliveryPersonService {
    private final DeliveryPersonRepository deliveryPersonRepository;
    private final OrderRepository orderRepository;

    public DeliveryPersonDto getDeliveryPerson(Long id) {
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(id)
                .orElseThrow(() -> new NotFoundExcption("Delivery Person not found"));
        return DeliveryPersonTransformers.toDto(deliveryPerson);
    }

    public DeliveryPersonDto updateDeliveryPerson(Long id,DeliveryPersonDto deliveryPersonDto) {
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(id)
                .orElseThrow(() -> new NotFoundExcption("Delivery Person not found"));
        deliveryPerson.setFullname(deliveryPersonDto.getFullname());
        deliveryPerson.setCity(deliveryPersonDto.getCity());
        deliveryPerson.setEmail(deliveryPersonDto.getEmail());
        deliveryPerson.setPhone(deliveryPersonDto.getPhone());
        deliveryPersonRepository.save(deliveryPerson);
        return DeliveryPersonTransformers.toDto(deliveryPerson);
    }

    public void deleteDeliveryPerson(Long id) {
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(id)
                .orElseThrow(() -> new NotFoundExcption("Delivery Person not found"));
        deliveryPersonRepository.delete(deliveryPerson);
    }

    public DeliveryPersonDto createDeliveryPerson(DeliveryPersonDto deliveryPersonDto) {
        DeliveryPerson deliveryPerson =  deliveryPersonRepository.save(DeliveryPersonTransformers.toEntity(deliveryPersonDto));
        return DeliveryPersonTransformers.toDto(deliveryPerson);
    }

    public List<DeliveryPersonDto> getAllDeliveryPersons() {
        return DeliveryPersonTransformers.toDtoList(deliveryPersonRepository.findAll());
    }

    public List<OrderDto> getDeliveryPersonOrders(Long id) {
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(id)
                .orElseThrow(() -> new NotFoundExcption("Delivery Person not found"));
        return OrderTransformer.toDtoList(deliveryPerson.getOrders());
    }
}
