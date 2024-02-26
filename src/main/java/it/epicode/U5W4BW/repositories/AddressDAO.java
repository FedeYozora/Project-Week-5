package it.epicode.U5W4BW.repositories;

import it.epicode.U5W4BW.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressDAO extends JpaRepository<Address, UUID> {

}
