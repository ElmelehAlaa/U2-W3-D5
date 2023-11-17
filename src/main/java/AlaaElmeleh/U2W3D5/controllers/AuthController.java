package AlaaElmeleh.U2W3D5.controllers;


import AlaaElmeleh.U2W3D5.entities.User;
import AlaaElmeleh.U2W3D5.exceptions.BadRequestException;
import AlaaElmeleh.U2W3D5.payload.entity.NewUserDTO;
import AlaaElmeleh.U2W3D5.payload.entity.UserLoginDTO;
import AlaaElmeleh.U2W3D5.payload.entity.UserLoginSuccessDTO;
import AlaaElmeleh.U2W3D5.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public UserLoginSuccessDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginSuccessDTO(authService.autheticateUser(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.save(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
