package com.winicios.dev.din.users.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.winicios.dev.din.users.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponseDTO(
        String id,
        String name,
        String email,
        BigDecimal fixedIncome,
        LocalDateTime creationDate) {

    public UserResponseDTO(User user) {

        this(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getFixedIncome(),
        user.getCreationDate()
        );
    }
}
