package it.epicode.U5W4BW.controllers;

import it.epicode.U5W4BW.entities.Client;
import it.epicode.U5W4BW.exceptions.BadRequestException;
import it.epicode.U5W4BW.payloads.NewClientDTO;
import it.epicode.U5W4BW.services.ClientSRV;
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
@RequestMapping("/clients")
public class ClientCTRL {

    @Autowired
    private ClientSRV clientSRV;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER' , 'ADMIN')")
    public Page<Client> getInvoices(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy) {
        return this.clientSRV.getClients(page, size, orderBy);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER' , 'ADMIN')")
    public Client getInvoiceById(@PathVariable UUID id) {
        return this.clientSRV.getClientById(id);
    }

    @GetMapping("/date")
    public Page<Client> getByCreationDate(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String order, @RequestParam LocalDate data) {
        return clientSRV.findByCreationDate(page, size, order, data);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client saveClient(@RequestBody @Validated NewClientDTO newClient, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.clientSRV.saveClient(newClient);

    }

    @GetMapping("/filter")
    public Page<Client> getByFilter(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size,
                                    @RequestParam(defaultValue = "id") String order,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) LocalDate creationDate,
                                    @RequestParam(required = false) Double yearlyIncome,
                                    @RequestParam(required = false) LocalDate lastContactDate) {
        if (name != null) {
            try {
                return clientSRV.findByContactNameStartingWithIgnoreCase(page, size, order, name);
            } catch (BadRequestException e) {
                throw new BadRequestException("Insert a valid Name");
            }

        } else if (creationDate != null) {
            try {
                return clientSRV.findByCreationDate(page, size, order, creationDate);
            } catch (BadRequestException e) {
                throw new BadRequestException("Insert a valid Date");
            }
        } else if (yearlyIncome != null) {
            try {
                return clientSRV.findByYearlyIncome(page, size, order, yearlyIncome);
            } catch (NumberFormatException e) {
                throw new BadRequestException("Insert a valid number");
            }
        } else if (lastContactDate != null) {
            try {
                return clientSRV.findByLastContactDate(page, size, order, lastContactDate);
            } catch (NumberFormatException e) {
                throw new BadRequestException("Insert a valid Date");
            }
        }
        throw new BadRequestException("Please check your request parameters...");
    }

}
