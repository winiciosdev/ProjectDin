package com.winicios.dev.din.bank.dto;

import com.winicios.dev.din.bank.Bank;

public record BankResponseDTO(
        String id,
        String name,
        String hexadecimalColor
) {

    public BankResponseDTO(Bank bank) {
        this(bank.getId(), bank.getName(), bank.getHexadecimalColor());
    }
}