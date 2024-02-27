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

import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientCTRL {
    @Autowired
    private ClientSRV clientSRV;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER' , 'ADMIN')")
    public Page<Client> getClients(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String orderBy) {
        return this.clientSRV.getClients(page, size, orderBy);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER' , 'ADMIN')")
    public Client getClientById(@PathVariable UUID id) {
        return this.clientSRV.getClientById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client saveClient(@RequestBody @Validated NewClientDTO newClient, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.clientSRV.saveClient(newClient);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client updateUserById(@PathVariable UUID id, @RequestBody @Validated NewClientDTO newClient, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.clientSRV.updateClientById(newClient, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //  Status Code 204
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteClientById(@PathVariable UUID id) {
        this.clientSRV.deleteClient(id);
    }

}
