package pet.proect.finflow.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pet.proect.finflow.entity.User;
import pet.proect.finflow.repository.UserRepository;

import java.net.http.HttpResponse;
import java.time.OffsetDateTime;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public User register(String email, String rawRegister){
        if(userRepository.findByEmail(email).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Emailis already registered");
        }
        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(rawRegister));
        user.setRole("USER");
        user.setCreatedAt(OffsetDateTime.now());
        return userRepository.save(user);
    }


}
