package com.eclipse.order.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.eclipse.order.dtos.DeliveryPersonDto;
import com.eclipse.order.dtos.OrderDto;
import com.eclipse.order.services.DeliveryPersonService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/delivery-persons")
@RequiredArgsConstructor
public class DeliveryPersonController {
    private final DeliveryPersonService deliveryPersonService;
    
    @GetMapping(value = {"", "/", "/all"})
    public ResponseEntity<List<DeliveryPersonDto>> getAllDeliveryPersons() {
        return ResponseEntity.ok(deliveryPersonService.getAllDeliveryPersons());
    }

    @GetMapping(value = {"/{id}", "/delivery-person/{id}"})
    public ResponseEntity<DeliveryPersonDto> getDeliveryPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryPersonService.getDeliveryPerson(id));
    }

    @PostMapping(value = {"/{id}", "/delivery-person/{id}"})
    public ResponseEntity<DeliveryPersonDto> updateDeliveryPerson(@PathVariable Long id, @RequestBody @Valid DeliveryPersonDto deliveryPersonDto) {
        return ResponseEntity.ok(deliveryPersonService.updateDeliveryPerson(id, deliveryPersonDto));
    }

    @DeleteMapping(value = {"/{id}", "/delivery-person/delete/{id}", "/delete/{id}"})
    public ResponseEntity<String> deleteDeliveryPerson(@PathVariable Long id) {
        deliveryPersonService.deleteDeliveryPerson(id);
        return ResponseEntity.ok("Delivery Person deleted successfully");
    }

    @PostMapping(value = {"", "/", "/delivery-person"})
    public ResponseEntity<DeliveryPersonDto> createDeliveryPerson(@RequestBody @Valid DeliveryPersonDto deliveryPersonDto) {
        return ResponseEntity.ok(deliveryPersonService.createDeliveryPerson(deliveryPersonDto));
    }

    @GetMapping(value = {"/{id}/orders"})
    public ResponseEntity<List<OrderDto>> getDeliveryPersonOrders(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryPersonService.getDeliveryPersonOrders(id));
    }
}
