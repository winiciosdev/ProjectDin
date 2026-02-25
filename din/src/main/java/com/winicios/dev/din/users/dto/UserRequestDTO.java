package com.winicios.dev.din.users.dto;

import com.winicios.dev.din.users.UserRole;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UserRequestDTO(
        String name,
        UserRole role,
        String email,
        String password,
        BigDecimal fixedIncome,
        LocalDate creationDate
) {
}
