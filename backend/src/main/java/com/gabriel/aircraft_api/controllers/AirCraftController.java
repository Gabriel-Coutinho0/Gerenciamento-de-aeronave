package com.gabriel.aircraft_api.controllers;

import com.gabriel.aircraft_api.models.dto.AirCraftDto;
import com.gabriel.aircraft_api.models.dto.CreateAirCraftDto;
import com.gabriel.aircraft_api.models.dto.DashboardResponse;
import com.gabriel.aircraft_api.models.dto.UpdateAirCraftDto;
import com.gabriel.aircraft_api.services.AirCraftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Aeronaves", description = "Endpoints relacionados a aeronaves")
@RestController
@CrossOrigin
@RequestMapping("/aeronaves")
public class AirCraftController {
    private final AirCraftService airCraftService;

    public AirCraftController(AirCraftService airCraftService) {
        this.airCraftService = airCraftService;
    }

    @Operation(summary = "Criar nova aeronave", description = "Endpoint para criar uma nova aeronave com os dados fornecidos.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AirCraftDto createAirCraft(@Valid @RequestBody CreateAirCraftDto createAirCraftDto) {
        return airCraftService.createAirCraft(createAirCraftDto);
    }

    @Operation(summary = "Atualizar aeronave cadastrada", description = "Endpoint para atualizar as informações da aeronave cadastrada.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AirCraftDto updateAirCraft(@PathVariable Long id, @Valid @RequestBody UpdateAirCraftDto updateAirCraftDto) {
        return airCraftService.updateAirCraft(id, updateAirCraftDto);
    }

    @Operation(summary = "Deletar aeronave", description = "Endpoint para deletar uma aeronave pelo seu ID.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAirCraft(@PathVariable Long id) {
        airCraftService.deleteAirCraft(id);
    }

    @Operation(summary = "Obter aeronave por ID", description = "Endpoint para obter os detalhes de uma aeronave pelo seu ID.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AirCraftDto getAirCraftById(@PathVariable Long id) {
        return airCraftService.getAirCraftById(id);
    }

    @Operation(summary = "Obter todas as aeronaves", description = "Endpoint para obter todas as aeronaves.")
    @GetMapping
    public List<AirCraftDto> findAll() {
        return airCraftService.getAirCrafts();
    }

    @Operation(summary = "Obter aeronaves por nome ou marca", description = "Endpoint para obter as aeronaves por nome ou marca.")
    @GetMapping("/find")
    public List<AirCraftDto> find(@RequestParam String term) {
        return airCraftService.getAirCraft(term);
    }

    @Operation(summary = "Obter as estatística das aeronaves", description = "Endpoint para obter as estatística das aeronaves.")
    @GetMapping("/dashboard")
    public DashboardResponse dashboard() {
        return airCraftService.dashboard();
    }
}
