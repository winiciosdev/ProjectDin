package com.winicios.dev.din.expenses.dto;

import com.winicios.dev.din.expenses.Expense;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponseDTO(
        String id,
        String cardName,
        String categoryName,
        String description,
        BigDecimal amount,
        Integer installments,
        Integer currentInstallment,
        String status,
        LocalDate purchaseDate
) {
    public ExpenseResponseDTO(Expense expense) {
        this(
                expense.getId(),
                expense.getCard().getName(),
                expense.getCategory().getName(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getInstallments(),
                expense.getCurrentInstallment(),
                expense.getStatus().name(),
                expense.getPurchaseDate()
        );
    }
}