package it.epicode.U5W4BW.repositories;

import it.epicode.U5W4BW.entities.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MunicipalityDAO extends JpaRepository<Municipality, UUID> {
    Optional<Municipality> findByName(String name);
}
