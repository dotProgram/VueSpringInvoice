package com.invoice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.entities.Item;

public interface ItemRepo extends JpaRepository<Item, Long> {
	
	
	//public List<District> findByStateIdAndIsActiveAndLgdDistrictIdIsNotNull(Long stateId,String isActive);
	//public List<District> findByIsActive(String isActive);

}
