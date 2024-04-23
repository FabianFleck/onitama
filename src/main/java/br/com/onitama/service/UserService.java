package br.com.onitama.service;

import br.com.onitama.error.exception.UnprocessableEntityException;
import br.com.onitama.model.entity.UserEntity;
import br.com.onitama.model.request.UserRequest;
import br.com.onitama.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserEntity registerNewUser(UserRequest user) {
        if (existsByUsername(user.getUsername())) {
            throw new UnprocessableEntityException("Username já existe!");
        }
        UserEntity newUser = new UserEntity();
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword())); // Criptografar a senha antes de salvar
        return userRepository.save(newUser);
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UnprocessableEntityException("Username não existe."));
    }
}