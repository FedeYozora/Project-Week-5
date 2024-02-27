package it.epicode.U5W4BW.controllers;

import it.epicode.U5W4BW.entities.Client;
import it.epicode.U5W4BW.exceptions.BadRequestException;
import it.epicode.U5W4BW.payloads.NewClientDTO;
import it.epicode.U5W4BW.services.ClientSRV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientCTRL {

    @Autowired
    private ClientSRV clientSRV;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client saveClient(@RequestBody @Validated NewClientDTO newClient, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.clientSRV.saveClient(newClient);

    }
}
