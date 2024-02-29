package it.epicode.U5W4BW.controllers;

import it.epicode.U5W4BW.entities.Address;
import it.epicode.U5W4BW.services.AddressSRV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/addresses")
public class AddressCTRL {
    @Autowired
    private AddressSRV addressSRV;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER' , 'ADMIN')")
    public Page<Address> getAddresses(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String orderBy) {
        return this.addressSRV.getAddresses(page, size, orderBy);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER' , 'ADMIN')")
    public Address getClientById(@PathVariable UUID id) {
        return this.addressSRV.getAddressById(id);
    }


}
