package AlaaElmeleh.U2W3D5.services;

import AlaaElmeleh.U2W3D5.Enum.Role;
import AlaaElmeleh.U2W3D5.entities.User;
import AlaaElmeleh.U2W3D5.exceptions.BadRequestException;
import AlaaElmeleh.U2W3D5.exceptions.UnauthorizedException;
import AlaaElmeleh.U2W3D5.payload.entity.NewUserDTO;
import AlaaElmeleh.U2W3D5.payload.entity.UserLoginDTO;
import AlaaElmeleh.U2W3D5.repositories.UserRepository;
import AlaaElmeleh.U2W3D5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    public String autheticateUser(UserLoginDTO body) {
        User user = userService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("credenziali non valide!");
        }
    }


    public User save(NewUserDTO body) throws IOException {
        userRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email" + user.getEmail() + "è già utilizzata!");
        });
        User newUser = new User();
        newUser.setNome(body.name());
        newUser.setEmail(body.email());
        newUser.setCognome(body.surname());
        newUser.setUsername(body.username());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setRole(Role.USER);
        return userRepository.save(newUser);
    }

}
