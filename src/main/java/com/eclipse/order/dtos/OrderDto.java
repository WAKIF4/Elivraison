package com.eclipse.order.dtos;

import com.eclipse.order.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    @NotEmpty(message = "The order must have an id")
    private Long id;

    @Min(value = 0, message = "The value must be positive")
    @NotEmpty(message = "The order must have a total price")
    private Double totalPaye;

    @NotEmpty(message = "The order must have a client")
    private String client;

    @NotEmpty(message = "The order must have at least one item")
    private String commandItems;

    private DeliveryPersonDto deliveryPerson;

    private OrderStatus status = OrderStatus.PENDING;


    public void isValidObject() {
        String error = "";
        if (this.id == null ) {
            error += "The order must have an id";
        }

        if (this.totalPaye == null || this.totalPaye < 0) {
            error += "The order must have a total price";
        }

        if (this.client == null) {
            error += "The order must have a client";
        }

        if (this.commandItems == null) {
            error += "The order must have at least one item";
        }
        
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

    }
}
