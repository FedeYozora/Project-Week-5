package it.epicode.U5W4BW.services;

import it.epicode.U5W4BW.entities.Municipality;
import it.epicode.U5W4BW.exceptions.NotFoundException;
import it.epicode.U5W4BW.repositories.MunicipalityDAO;
import it.epicode.U5W4BW.repositories.ProvinceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MunicipalitySRV {
    @Autowired
    private MunicipalityDAO municipalityDAO;
    @Autowired
    private ProvinceDAO provinceDAO;

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
