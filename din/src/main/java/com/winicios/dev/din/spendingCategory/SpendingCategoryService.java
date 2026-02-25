package com.winicios.dev.din.spendingCategory;

import com.winicios.dev.din.spendingCategory.dto.SpendingCategoryResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpendingCategoryService {

    private final SpendingCategoryRepository repository;

    public SpendingCategoryService(SpendingCategoryRepository repository) {
        this.repository = repository;
    }

    public List<SpendingCategoryResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(SpendingCategoryResponseDTO::new)
                .collect(Collectors.toList());
    }

    public SpendingCategoryResponseDTO findById(String id) {
        SpendingCategory category = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
        return new SpendingCategoryResponseDTO(category);
    }
}