package com.winicios.dev.din.bank;

import com.winicios.dev.din.bank.dto.BankResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public ResponseEntity<List<BankResponseDTO>> findAll() {
        return ResponseEntity.ok(this.bankService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankResponseDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.bankService.findById(id));
    }
}