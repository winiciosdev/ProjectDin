package com.winicios.dev.din.card.dto;

import com.winicios.dev.din.bank.dto.BankResponseDTO;
import com.winicios.dev.din.card.Card;

import java.math.BigDecimal;

public record CardResponseDTO(
        String id,
        String name,
        BankResponseDTO bank,
        BigDecimal creditLimit,
        Integer closingDay,
        Integer dueDay
) {
    public CardResponseDTO(Card card) {
        this(
                card.getId(),
                card.getName(),
                new BankResponseDTO(card.getBank()),
                card.getCreditLimit(),
                card.getClosingDay(),
                card.getDueDay()
        );
    }
}