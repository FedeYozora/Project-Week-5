package it.epicode.U5W4BW.repositories;

import it.epicode.U5W4BW.entities.UserRole;
import it.epicode.U5W4BW.enums.UserRoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRoleDAO extends JpaRepository<UserRole, UUID> {
    UserRole findByRole(String role);
}
