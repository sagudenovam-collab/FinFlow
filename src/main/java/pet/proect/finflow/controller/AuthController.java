package pet.proect.finflow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.proect.finflow.dto.RegisterRequest;
import pet.proect.finflow.dto.UserResponse;
import pet.proect.finflow.entity.User;
import pet.proect.finflow.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @RequestBody RegisterRequest request
    ) {

        User user = authService.register(
                request.email(),
                request.password()
        );

        UserResponse response = new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
