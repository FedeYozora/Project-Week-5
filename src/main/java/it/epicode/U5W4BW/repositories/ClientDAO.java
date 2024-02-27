package it.epicode.U5W4BW.repositories;

import it.epicode.U5W4BW.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientDAO extends JpaRepository<Client, UUID> {

    Optional<Client> findByEmail(String email);

    Page<Client> findByCreationDate(Pageable pageable, LocalDate date);

    Page<Client> findByLastContactDate(Pageable pageable, LocalDate date);

    Page<Client> findByYearlyIncome(Pageable pageable, double income);

    Page<Client> findByContactNameStartingWithIgnoreCase(Pageable pageable, String name);
}
