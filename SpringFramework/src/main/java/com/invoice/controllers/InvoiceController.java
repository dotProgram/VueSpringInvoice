package com.invoice.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.entities.InvoiceDto;
import com.invoice.entities.Item;
import com.invoice.repositories.InvoiceRepo;

/**
 * REST Controller for managing invoices.
 * Handles CRUD operations between frontend and database.
 */
@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*") // Allow frontend (Vue) access from different port
public class InvoiceController {

    private final InvoiceRepo invoiceRepository;

    public InvoiceController(InvoiceRepo invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    /**
     * Fetch all invoices.
     * Example: GET /api/invoices
     */
	
	  @GetMapping public List<InvoiceDto> getAllInvoices() { return
	  invoiceRepository.findAll(); }
	 

    /**
     * Fetch a single invoice by ID.
     * Example: GET /api/invoices/{id}
     */
    @GetMapping("/{id}")
    public Optional<InvoiceDto> getInvoiceById(@PathVariable Long id) {
        return invoiceRepository.findById(id);
    }
	/*
	 * @GetMapping public List<InvoiceDto> getAllInvoices() { List<InvoiceDto>
	 * invoices = invoiceRepository.findAll(); invoices.forEach(inv -> { if
	 * (inv.getItems() == null) inv.setItems(List.of()); // ensure not null });
	 * return invoices; }
	 */

    /**
     * Create a new invoice.
     * Example: POST /api/invoices
     */
    @PostMapping
    public InvoiceDto createInvoice(@RequestBody InvoiceDto invoiceDto) {
    	InvoiceDto invoice = new InvoiceDto();
        invoice.setCustomer(invoiceDto.getCustomer());
        invoice.setSubtotal(invoiceDto.getSubtotal());

        // Map items from DTO to Entity
        List<Item> items = invoiceDto.getItems().stream().map(itemDto -> {
            Item item = new Item();
            item.setDescription(itemDto.getDescription());
            item.setQty(itemDto.getQty());
            item.setUnitPrice(itemDto.getUnitPrice());
            return item;
        }).collect(Collectors.toList());

        invoice.setItems(items);

        return invoiceRepository.save(invoice);
    }

    /**
     * Delete an invoice by ID.
     * Example: DELETE /api/invoices/{id}
     */
    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        invoiceRepository.deleteById(id);
    }
}