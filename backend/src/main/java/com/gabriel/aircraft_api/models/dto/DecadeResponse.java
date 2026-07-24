package com.gabriel.aircraft_api.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record DecadeResponse(
        @JsonIgnore
        Integer decade,
        @JsonIgnore
        Integer year,
        Long quantity
) {
    public String getLabel() {
        return String.format("%02d (%d)", decade % 100, year);
    }
}
