package com.winicios.dev.din.users;

import com.winicios.dev.din.security.ErrorResponseDTO;
import com.winicios.dev.din.users.dto.UserRequestDTO;
import com.winicios.dev.din.users.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserRequestDTO dto){

        String login = userService.login(dto);

        return ResponseEntity.ok(login);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registrar(@Valid @RequestBody UserRequestDTO dto){

        UserResponseDTO register = userService.register(dto);

        return ResponseEntity.ok(register);

    }

    @ExceptionHandler(org.springframework.web.server.ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDTO> handleResponseStatusException(org.springframework.web.server.ResponseStatusException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(List.of(ex.getReason()));
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }

}