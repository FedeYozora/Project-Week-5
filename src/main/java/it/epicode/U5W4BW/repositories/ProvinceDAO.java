package it.epicode.U5W4BW.repositories;

import it.epicode.U5W4BW.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProvinceDAO extends JpaRepository<Province, UUID> {
    @Query(value = "select p from Province p where p.name=:name")
    Province findByName(String name);

    @Query(value = "select p from Province p where p.acronym=:acronym")
    Province findByAcronym(String acronym);
}
