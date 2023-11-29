package com.eclipse.order.repositories;

import com.eclipse.order.entities.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long>{

}
