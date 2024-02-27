package it.epicode.U5W4BW.controllers;

import it.epicode.U5W4BW.entities.Invoice;
import it.epicode.U5W4BW.exceptions.BadRequestException;
import it.epicode.U5W4BW.payloads.InvoiceDTO;
import it.epicode.U5W4BW.services.InvoiceSRV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/invoices")
public class InvoiceCTRL {

    @Autowired
    private InvoiceSRV invoiceSRV;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER' , 'ADMIN')")
    public Page<Invoice> getInvoices(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy) {
        return this.invoiceSRV.getInvoices(page, size, orderBy);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER' , 'ADMIN')")
    public Invoice getInvoiceById(@PathVariable Long id) {
        return this.invoiceSRV.getInvoiceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Invoice saveInvoice(@RequestBody @Validated InvoiceDTO newInvoice, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.invoiceSRV.saveInvoice(newInvoice);

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //  Status Code 204
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteInvoiceById(@PathVariable Long id) {
        this.invoiceSRV.deleteInvoice(id);
    }

    @GetMapping("/filter/{request}")
    public Page<Invoice> getRequests(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String orderBy,
                                     @RequestParam(required = false) String client_id,
                                     @RequestParam(required = false) LocalDate date,
                                     @RequestParam(required = false) String year,
                                     @RequestParam(required = false) String amount1,
                                     @RequestParam(required = false) String amount2) {

        if (client_id != null) {
            try {
                return invoiceSRV.findByIdClient(page, size > 20 ? 5 : size, orderBy, UUID.fromString((client_id)));
            } catch (NumberFormatException e) {
                throw new BadRequestException("Insert a valid client UUID");
            }
        }

        if (date != null) {
            try {
                return invoiceSRV.findByEmissionDate(page, size > 20 ? 5 : size, orderBy, date);
            } catch (NumberFormatException e) {
                throw new BadRequestException("Insert a valid year");
            }
        }

        if (year != null) {
            try {
                return invoiceSRV.findByYear(page, size > 20 ? 5 : size, orderBy, Integer.parseInt(year));
            } catch (NumberFormatException e) {
                throw new BadRequestException("Insert a valid date");
            }

        }

        if (amount1 != null && amount2 != null) {
            try {
                return invoiceSRV.findByAmount(page, size > 20 ? 5 : size, orderBy, Double.parseDouble(amount1), Double.parseDouble(amount2));
            } catch (NumberFormatException e) {
                throw new BadRequestException("Insert a valid amount");
            }
        }


        throw new BadRequestException("Please check your request parameters...");
    }
}
