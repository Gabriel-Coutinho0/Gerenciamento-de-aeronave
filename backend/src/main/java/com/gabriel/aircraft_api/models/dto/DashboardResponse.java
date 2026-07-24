package com.gabriel.aircraft_api.models.dto;

import java.util.List;

public record DashboardResponse(
        Long unsold,
        Long registeredLastWeek,
        List<MarcaResponse> aircraftByMarca,
        List<DecadeResponse> aircraftByDecade

) {
}