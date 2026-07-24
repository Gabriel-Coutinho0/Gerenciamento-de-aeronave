package com.gabriel.aircraft_api.models.dto;

import com.gabriel.aircraft_api.models.enums.Marca;
import jakarta.validation.constraints.Min;


public record UpdateAirCraftDto(
        String nome,
        Marca marca,
        @Min(value = 1903, message = "O ano deve ser maior ou igual a 1903")
        Integer ano,
        String descricao,
        boolean vendido
){}
