package br.com.onitama.resource;

import br.com.onitama.model.request.UserAuthenticationRequest;
import br.com.onitama.model.request.UserCreateRequest;
import br.com.onitama.model.response.TokenResponse;
import br.com.onitama.service.TokenService;
import br.com.onitama.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "User")
@RequestMapping("/api/user")
public class UserResource {

    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public UserResource(UserService userService, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> registerUser(@RequestBody UserCreateRequest request) {
        userService.registerNewUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticateUser(@RequestBody UserAuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(tokenService.generateToken(request.getUsername()));
    }
}
