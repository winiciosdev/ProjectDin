package com.winicios.dev.din.expenses.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequestDTO(
        String cardId,
        String categoryId,
        String description,
        BigDecimal amount,
        Integer installments,
        LocalDate purchaseDate
) {}