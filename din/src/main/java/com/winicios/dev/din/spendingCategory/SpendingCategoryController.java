package com.winicios.dev.din.spendingCategory;

import com.winicios.dev.din.spendingCategory.dto.SpendingCategoryResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class SpendingCategoryController {

    private final SpendingCategoryService service;

    public SpendingCategoryController(SpendingCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SpendingCategoryResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpendingCategoryResponseDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }
}