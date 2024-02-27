package it.epicode.U5W4BW.repositories;

import it.epicode.U5W4BW.entities.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InvoiceStatusDAO extends JpaRepository<InvoiceStatus, UUID> {
    Optional<InvoiceStatus> findByStatus(String status);
}
