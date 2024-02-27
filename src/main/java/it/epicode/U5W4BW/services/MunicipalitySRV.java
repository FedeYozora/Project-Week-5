package it.epicode.U5W4BW.services;

import it.epicode.U5W4BW.entities.Municipality;
import it.epicode.U5W4BW.exceptions.NotFoundException;
import it.epicode.U5W4BW.repositories.MunicipalityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public class MunicipalitySRV {
    @Autowired
    private MunicipalityDAO municipalityDAO;

    public Municipality save(Municipality body) {

        return municipalityDAO.save(body);
    }

    public Page<Municipality> getMunicipalities(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return municipalityDAO.findAll(pageable);
    }

    public Municipality findById(UUID id) {
        return municipalityDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        Municipality found = municipalityDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        municipalityDAO.delete(found);
    }
}