package com.eclipse.order.entities;

import com.eclipse.order.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    private Long id;

    @Min(value = 0, message = "The value must be positive")
    private Double totalPrice;

    @NotEmpty(message = "The order must have at least one product")
    @Column(columnDefinition = "TEXT")
    private String products;

    @NotEmpty(message = "The order must have a customer")
    private String customer;

    @ManyToOne
    @JoinColumn(name = "delivery_person_id")
    private DeliveryPerson deliveryPerson;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;
}
