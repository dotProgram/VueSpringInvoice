package com.invoice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.entities.InvoiceDto;

public interface InvoiceRepo extends JpaRepository<InvoiceDto, Long> {
	
	
	//public List<District> findByStateIdAndIsActiveAndLgdDistrictIdIsNotNull(Long stateId,String isActive);
	//public List<District> findByIsActive(String isActive);

}
