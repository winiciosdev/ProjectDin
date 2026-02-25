package com.winicios.dev.din.spendingCategory.dto;

import com.winicios.dev.din.spendingCategory.SpendingCategory;

public record SpendingCategoryResponseDTO (
        String id,
        String name,
        String description) {

    public SpendingCategoryResponseDTO(SpendingCategory sc) {

        this(
                sc.getId(),
                sc.getName(),
                sc.getDescription()
        );
    }

}
