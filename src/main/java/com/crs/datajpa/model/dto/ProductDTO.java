package com.crs.datajpa.model.dto;

import java.util.List;

public record ProductDTO(
        Long id,
        String title,
        String description,
        int price,
        String cod,
        String photoUrl,
        List<Long> categoryIds,
        List<String> tags
) {
}
