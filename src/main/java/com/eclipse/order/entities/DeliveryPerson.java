package com.eclipse.order.entities;

import java.util.List;

import com.eclipse.order.enums.DeliveryPersonStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_delivery_person")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The delivery person must have a fullname")
    private String fullname;

    @NotEmpty(message = "The delivery person must have an email")
    private String email;

    @NotEmpty(message = "The delivery person must have a phone")
    private String phone;

    @NotEmpty(message = "The delivery person must have a city")
    private String city;

    @OneToMany(mappedBy = "deliveryPerson", orphanRemoval = true , cascade = CascadeType.ALL)
    private List<Order> orders;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private DeliveryPersonStatus status = DeliveryPersonStatus.AVAILABLE; 
}
