package com.crs.datajpa.model.dto;

import java.util.List;

public record ProductResponseDTO(
        Long id,
        String title,
        String description,
        Double price,
        String cod,
        String photoUrl,
        List<CategoryDTO> categories,
        List<String> tags
) {
}
