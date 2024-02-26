package it.epicode.U5W4BW.repositories;

import it.epicode.U5W4BW.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientDAO extends JpaRepository<Client, UUID> {


}
