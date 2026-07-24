package com.gabriel.aircraft_api.models.dto;

import com.gabriel.aircraft_api.models.entities.AirCraft;

import java.time.LocalDateTime;

public record AirCraftDto(
        Long id,
        String nome,
        String descricao,
        String marca,
        int ano,
        boolean vendido,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static AirCraftDto fromEntity(AirCraft airCraft) {
        return new AirCraftDto(
                airCraft.getId(),
                airCraft.getNome(),
                airCraft.getDescricao(),
                airCraft.getMarca().getDisplayName(),
                airCraft.getAno(),
                airCraft.getVendido(),
                airCraft.getCreatedAt(),
                airCraft.getUpdatedAt()
        );
    }
}
