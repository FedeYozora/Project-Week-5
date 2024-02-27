package it.epicode.U5W4BW.services;

import it.epicode.U5W4BW.entities.User;
import it.epicode.U5W4BW.entities.UserRole;
import it.epicode.U5W4BW.exceptions.BadRequestException;
import it.epicode.U5W4BW.exceptions.NotFoundException;
import it.epicode.U5W4BW.payloads.NewUserDTO;
import it.epicode.U5W4BW.payloads.UserLoginDTO;
import it.epicode.U5W4BW.repositories.UserDAO;
import it.epicode.U5W4BW.repositories.UserRoleDAO;
import it.epicode.U5W4BW.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthSRV {

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserSRV userSRV;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;


    public String authUserAndGenerateToken(UserLoginDTO payload) {
        User user = userSRV.findUserByEmail(payload.email());
        if (bcrypt.matches(payload.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new RuntimeException("Error logging the user in: incorrect credentials");
        }

    }

    public User saveUser(NewUserDTO payload) {
        userDAO.findByEmail(payload.email()).ifPresent(user -> {
            throw new BadRequestException("Email " + user.getEmail() + " already in use!");
        });
        //Setto il ruolo di base come user
        UserRole userRole = userRoleDAO.findByRole("USER").orElseThrow(() -> new NotFoundException("Role not found"));

        User newUser = new User(
                payload.email(),
                payload.username(),
                bcrypt.encode(payload.password()),
                payload.name(),
                payload.surname()
        );

        newUser.getRoles().add(userRole);

        return userDAO.save(newUser);
    }
}
