package it.epicode.U5W4BW.controllers;

import it.epicode.U5W4BW.entities.InvoiceStatus;
import it.epicode.U5W4BW.exceptions.BadRequestException;
import it.epicode.U5W4BW.payloads.InvoiceStatusDTO;
import it.epicode.U5W4BW.services.InvoiceStatusSRV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/invoiceStatus")
public class InvoiceStatusCTRL {
    @Autowired
    private InvoiceStatusSRV invoiceStatusSRV;

    @GetMapping
    public Page<InvoiceStatus> getInvoiceStatus(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "id") String orderBy) {
        return this.invoiceStatusSRV.getInvoiceStatus(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public InvoiceStatus getInvoiceStatusById(@PathVariable UUID id) {
        return this.invoiceStatusSRV.getInvoiceStatusById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceStatus saveInvoiceStatus(@RequestBody @Validated InvoiceStatusDTO newInvoiceStatus, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.invoiceStatusSRV.saveInvoiceStatus(newInvoiceStatus);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceStatusById(@PathVariable UUID id) {
        this.invoiceStatusSRV.deleteInvoiceStatus(id);
    }

}
