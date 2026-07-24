package com.gabriel.aircraft_api.models.enums;
import lombok.Getter;

@Getter
public enum Marca {
    BOEING("Boeing"),
    AIRBUS("Airbus"),
    EMBRAER("Embraer"),
    CESSNA("Cessna"),
    BOMBARDIER("Bombardier");

    private final String displayName;

    Marca(String displayName) {
        this.displayName = displayName;
    }
}
