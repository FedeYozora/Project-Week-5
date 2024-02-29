package it.epicode.U5W4BW.services;

import it.epicode.U5W4BW.entities.Address;
import it.epicode.U5W4BW.entities.Municipality;
import it.epicode.U5W4BW.exceptions.NotFoundException;
import it.epicode.U5W4BW.exceptions.UUIDNotFoundException;
import it.epicode.U5W4BW.payloads.AddressDTO;
import it.epicode.U5W4BW.repositories.AddressDAO;
import it.epicode.U5W4BW.repositories.MunicipalityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressSRV {
    @Autowired
    private AddressDAO addressDAO;
    @Autowired
    private MunicipalityDAO municipalityDAO;

    public Page<Address> getAddresses(int pageNum, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNum, size, Sort.by(orderBy));
        return addressDAO.findAll(pageable);

    }

    public Address saveAddress(AddressDTO body) {
        Municipality found = municipalityDAO.findByName(body.city()).orElseThrow(() -> new NotFoundException(body.city()));

        return addressDAO.save(new Address(body.street(), body.streetNumber(), body.city(), body.zipCode(), found));

    }


    public Address getAddressById(UUID id) {
        return addressDAO.findById(id).orElseThrow(() -> new UUIDNotFoundException(id));
    }


    public void deleteAddress(UUID id) {
        Address found = getAddressById(id);
        addressDAO.delete(found);
    }


}
