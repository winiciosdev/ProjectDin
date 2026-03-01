package com.winicios.dev.din.expenses.dto;

import java.math.BigDecimal;

public record MonthlySummaryDTO(
        Integer month,
        Integer year,
        BigDecimal totalAmount
) {}
