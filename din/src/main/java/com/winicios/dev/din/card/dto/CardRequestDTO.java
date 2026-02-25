package com.winicios.dev.din.card.dto;

import java.math.BigDecimal;

public record CardRequestDTO(
        String bankId,
        String name,
        BigDecimal creditLimit,
        Integer closingDay,
        Integer dueDay
) {
}