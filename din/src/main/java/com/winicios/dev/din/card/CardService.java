package com.winicios.dev.din.card;

import com.winicios.dev.din.bank.Bank;
import com.winicios.dev.din.bank.BankRepository;
import com.winicios.dev.din.card.dto.CardRequestDTO;
import com.winicios.dev.din.card.dto.CardResponseDTO;
import com.winicios.dev.din.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final BankRepository bankRepository;

    public CardService(CardRepository cardRepository, BankRepository bankRepository) {
        this.cardRepository = cardRepository;
        this.bankRepository = bankRepository;
    }

    public CardResponseDTO create(CardRequestDTO dto, User loggedUser) {

        Bank bank = bankRepository.findById(dto.bankId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Banco não encontrado"));

        Card newCard = new Card();
        newCard.setUser(loggedUser);
        newCard.setBank(bank);
        newCard.setName(dto.name());
        newCard.setCreditLimit(dto.creditLimit());
        newCard.setClosingDay(dto.closingDay());
        newCard.setDueDay(dto.dueDay());

        cardRepository.save(newCard);

        return new CardResponseDTO(newCard);
    }

    public List<CardResponseDTO> findAllByUser(User loggedUser) {
        return cardRepository.findAllByUser(loggedUser)
                .stream()
                .map(CardResponseDTO::new)
                .collect(Collectors.toList());
    }

    public CardResponseDTO findById(String id, User loggedUser) {
        Card card = cardRepository.findByIdAndUser(id, loggedUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado ou não pertence ao usuário"));
        return new CardResponseDTO(card);
    }

    public String deleteById(String id, User loggedUser) {

        Card card = cardRepository.findByIdAndUser(id, loggedUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado ou não pertence ao usuário"));

        cardRepository.delete(card);

        String message = "Cartão deletado com sucesso";

        return message;
    }
}