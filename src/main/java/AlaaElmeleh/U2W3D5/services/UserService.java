package AlaaElmeleh.U2W3D5.services;

import AlaaElmeleh.U2W3D5.entities.User;
import AlaaElmeleh.U2W3D5.repositories.UserRepository;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Page<User> getUsers (int page,int size, String orderBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return userRepository.findAll(pageable);
    }
public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()-> new ChangeSetPersister.NotFoundException("utente con email" + email + "non trovato"));
}


}
