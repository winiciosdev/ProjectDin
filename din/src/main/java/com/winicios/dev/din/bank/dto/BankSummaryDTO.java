package com.winicios.dev.din.bank.dto;

import java.math.BigDecimal;

public record BankSummaryDTO(String bankName, String hexadecimalColor, BigDecimal totalAmount) {
}
