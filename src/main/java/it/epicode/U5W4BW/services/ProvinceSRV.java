package it.epicode.U5W4BW.services;

import it.epicode.U5W4BW.entities.Province;
import it.epicode.U5W4BW.exceptions.NotFoundException;
import it.epicode.U5W4BW.repositories.ProvinceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProvinceSRV {
    @Autowired
    private ProvinceDAO provinceDAO;


    public Province save(Province body) {
        return provinceDAO.save(body);
    }

    public Page<Province> getProvinces(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return provinceDAO.findAll(pageable);
    }

    public Province findById(UUID id) {
        return provinceDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        Province found = provinceDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        provinceDAO.delete(found);
    }

    public Province getByNome(String name) {
        return provinceDAO.findByName(name);

    }
}
