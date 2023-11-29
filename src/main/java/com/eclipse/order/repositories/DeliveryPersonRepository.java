package com.eclipse.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eclipse.order.entities.DeliveryPerson;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long>{

}
