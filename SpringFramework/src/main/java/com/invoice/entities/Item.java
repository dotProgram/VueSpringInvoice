package com.invoice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Item {
	@Id
	@Column(name="id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

	@Column(name="description")
private String description;
	@Column(name="qty")
private int qty;
	@Column(name="unitPrice")
private double unitPrice;


// getters and setters
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public String getDescription() { return description; }
public void setDescription(String description) { this.description = description; }
public int getQty() { return qty; }
public void setQty(int qty) { this.qty = qty; }
public double getUnitPrice() { return unitPrice; }
public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}
