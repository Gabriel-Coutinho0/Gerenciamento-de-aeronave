package com.gabriel.aircraft_api.models.dto;

import com.gabriel.aircraft_api.models.enums.Marca;

public record MarcaResponse(
        Marca marca,
        Long quantity) {}
