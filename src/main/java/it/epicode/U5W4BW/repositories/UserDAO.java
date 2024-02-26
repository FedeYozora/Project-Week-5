package it.epicode.U5W4BW.repositories;

import it.epicode.U5W4BW.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDAO extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
}
