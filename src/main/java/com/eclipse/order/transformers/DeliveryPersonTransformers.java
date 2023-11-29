package com.eclipse.order.transformers;

import java.util.List;
import java.util.stream.Collectors;

import com.eclipse.order.dtos.DeliveryPersonDto;
import com.eclipse.order.entities.DeliveryPerson;

public class DeliveryPersonTransformers {
        public static DeliveryPerson toEntity(DeliveryPersonDto dto) {
            DeliveryPerson entity = DeliveryPerson.builder()
                    .id(dto.getId())
                    .fullname(dto.getFullname())
                    .status(dto.getStatus())
                    .city(dto.getCity())
                    .email(dto.getEmail())
                    .phone(dto.getPhone())
                    .build();
            return entity;
        }

        public static DeliveryPersonDto toDto(DeliveryPerson entity) {
            DeliveryPersonDto dto = DeliveryPersonDto.builder()
                    .id(entity.getId())
                    .fullname(entity.getFullname())
                    .status(entity.getStatus())
                    .city(entity.getCity())
                    .email(entity.getEmail())
                    .phone(entity.getPhone())
                    .build();
            return dto;
        }


        public static List<DeliveryPersonDto> toDtoList(List<DeliveryPerson> entities) {
            return entities.stream().map(entity -> toDto(entity)).collect(Collectors.toList());
        }


    
}
