package com.eclipse.order.dtos;

import com.eclipse.order.enums.DeliveryPersonStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeliveryPersonDto {
    private Long id;

    @NotBlank(message = "The delivery person must have a fullname")
    private String fullname;

    @NotBlank(message = "The delivery person must have an email")
    private String email;

    @NotBlank(message = "The delivery person must have a phone")
    private String phone;

    @NotBlank(message = "The delivery person must have a city")
    private String city;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private DeliveryPersonStatus status = DeliveryPersonStatus.AVAILABLE; 
}
