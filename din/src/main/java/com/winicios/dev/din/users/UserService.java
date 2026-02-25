package com.winicios.dev.din.users;

import com.winicios.dev.din.security.TokenService;
import com.winicios.dev.din.users.dto.UserRequestDTO;
import com.winicios.dev.din.users.dto.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

private UserRepository userRepository;
private AuthenticationManager authenticationManager;
private TokenService tokenService;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;

    }

    public UserResponseDTO register(UserRequestDTO dto) {

    if (this.userRepository.existsByEmail(dto.email())){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já exite um registro com este e-mail");
    }


    String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
    User newUser = new User(dto.email(), dto.name(), encryptedPassword, dto.role());

    this.userRepository.save(newUser);

    return new UserResponseDTO(newUser);
}

    public String login(UserRequestDTO dto){

        try {

        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
            var auth = this.authenticationManager.authenticate(userNamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());

        return token;

        } catch (AuthenticationException e) {

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login ou senha incorretos");

        }
    }

}
