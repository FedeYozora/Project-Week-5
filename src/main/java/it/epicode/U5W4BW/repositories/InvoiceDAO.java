package it.epicode.U5W4BW.repositories;

import it.epicode.U5W4BW.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDAO extends JpaRepository<Invoice, Long> {
}
