package com.invoice.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Data Transfer Object for Invoice.
 * Used to receive invoice data from the frontend and send responses.
 */
@Entity
@Table(name="invoice")
public class InvoiceDto {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private Long id;
	@Column(name="customer")
    private String customer;
	@Column(name="subtotal")
    private double subtotal;
	//@Transient
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "invoice_id")
    private List<Item> items;

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    // --- Inner DTO class for Item ---
	/*
	 * public static class ItemDto { private String description; private int qty;
	 * private double unitPrice;
	 * 
	 * public String getDescription() { return description; }
	 * 
	 * public void setDescription(String description) { this.description =
	 * description; }
	 * 
	 * public int getQty() { return qty; }
	 * 
	 * public void setQty(int qty) { this.qty = qty; }
	 * 
	 * public double getUnitPrice() { // TODO Auto-generated method stub return
	 * unitPrice; } }
	 */
}