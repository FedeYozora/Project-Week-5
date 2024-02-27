package it.epicode.U5W4BW.services;

import it.epicode.U5W4BW.entities.Client;
import it.epicode.U5W4BW.entities.Invoice;
import it.epicode.U5W4BW.entities.User;
import it.epicode.U5W4BW.entities.UserRole;
import it.epicode.U5W4BW.exceptions.NotFoundException;
import it.epicode.U5W4BW.exceptions.UUIDNotFoundException;
import it.epicode.U5W4BW.payloads.InvoiceDTO;
import it.epicode.U5W4BW.payloads.UserRoleDTO;
import it.epicode.U5W4BW.repositories.UserDAO;
import it.epicode.U5W4BW.repositories.UserRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserRoleSRV {

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private UserDAO userDAO;

    public Page<UserRole> getUserRoles(int pageNum, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNum, size, Sort.by(orderBy));
        return userRoleDAO.findAll(pageable);
    }

    public UserRole saveRole(UserRoleDTO newRole) {
        UserRole userRole = new UserRole(newRole.role().toUpperCase());
        return userRoleDAO.save(userRole);
    }

    public UserRole getUserRoleById(UUID id) {
        return userRoleDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void deleteRole(UUID id) {
        UserRole found = getUserRoleById(id);
        userRoleDAO.delete(found);
    }
}
