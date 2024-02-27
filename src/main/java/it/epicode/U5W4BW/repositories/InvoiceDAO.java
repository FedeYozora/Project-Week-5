package it.epicode.U5W4BW.repositories;

import it.epicode.U5W4BW.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface InvoiceDAO extends JpaRepository<Invoice, Long> {
    Page<Invoice> findByEmissionDate(Pageable p, LocalDate date);

    Page<Invoice> findByClientId(Pageable p, UUID id);

    Page<Invoice> findByAmountBetween(Pageable p, double imp1, double imp2);

    @Query("select i from Invoice i where YEAR(i.emissionDate)=:y")
    Page<Invoice> getByYear(Pageable p, int y);
}
