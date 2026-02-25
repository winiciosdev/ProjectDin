package com.winicios.dev.din.bank;

import com.winicios.dev.din.bank.dto.BankResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankService {

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<BankResponseDTO> findAll() {
        return this.bankRepository.findAll()
                .stream()
                .map(BankResponseDTO::new)
                .collect(Collectors.toList());
    }

    public BankResponseDTO findById(String id) {
        Bank bank = this.bankRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Banco não encontrado"));
        return new BankResponseDTO(bank);
    }
}