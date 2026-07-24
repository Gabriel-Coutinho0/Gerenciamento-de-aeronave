package com.gabriel.aircraft_api.models.dto;

import com.gabriel.aircraft_api.models.enums.Marca;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CreateAirCraftDto(
        @NotBlank(message = "O nome não pode ser nulo ou vazio")
        String nome,
        @NotNull(message = "A marca não pode ser nula")
        Marca marca,
        @NotNull(message = "O ano não pode ser nulo")
        @Min(value = 1903, message = "O ano deve ser maior ou igual a 1903")
        Integer ano,
        String descricao,
        boolean vendido
){}
