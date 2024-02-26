package it.epicode.U5W4BW.controllers;

import it.epicode.U5W4BW.entities.User;
import it.epicode.U5W4BW.services.UserSRV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserCTRL {
    @Autowired
    private UserSRV userSRV;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER' , 'ADMIN')")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy) {
        return this.userSRV.getUsers(page, size, orderBy);
    }
}
