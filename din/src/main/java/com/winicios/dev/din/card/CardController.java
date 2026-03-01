package com.winicios.dev.din.card;

import com.winicios.dev.din.card.dto.CardRequestDTO;
import com.winicios.dev.din.card.dto.CardResponseDTO;
import com.winicios.dev.din.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CardResponseDTO> create(
            @RequestBody CardRequestDTO dto,
            @AuthenticationPrincipal User loggedUser) {
        CardResponseDTO response = cardService.create(dto, loggedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CardResponseDTO>> findAll(@AuthenticationPrincipal User loggedUser) {
        return ResponseEntity.ok(cardService.findAllByUser(loggedUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponseDTO> findById(
            @PathVariable String id,
            @AuthenticationPrincipal User loggedUser) {
        return ResponseEntity.ok(cardService.findById(id, loggedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(
            @PathVariable String id,
            @AuthenticationPrincipal User loggedUser) {

        return ResponseEntity.ok(cardService.deleteById(id, loggedUser));

    }
}