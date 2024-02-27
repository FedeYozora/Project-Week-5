package it.epicode.U5W4BW.repositories;

import it.epicode.U5W4BW.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProvinceDAO extends JpaRepository<Province, UUID> {
    Province findByName(String name);
}
