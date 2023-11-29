package com.eclipse.order.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.eclipse.order.dtos.OrderDto;
import com.eclipse.order.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping(value = {"", "/","/all"})
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping(value = {"/{id}", "/order/{id}"})
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    
    @PostMapping(value = {"/{id}", "/order/status/{id}", "/status/{id}"})
    public ResponseEntity<OrderDto> updateStatus(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.updateStatus(id));
    }

    @DeleteMapping(value = {"/{id}", "/order/delete/{id}", "/delete/{id}"})
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }

    @PutMapping(value = {"/{deliveryPersonId}/{id}", "/order/{deliveryPersonId}/{id}", })
    public ResponseEntity<OrderDto> addDeliveryPerson(@PathVariable Long id,@PathVariable    Long deliveryPersonId){
        return ResponseEntity.ok(orderService.addDeliveryPerson(id, deliveryPersonId));
    }

}
