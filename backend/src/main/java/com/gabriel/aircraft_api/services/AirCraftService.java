package com.gabriel.aircraft_api.services;

import com.gabriel.aircraft_api.models.dto.*;
import com.gabriel.aircraft_api.models.entities.AirCraft;
import com.gabriel.aircraft_api.repositores.AirCraftRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AirCraftService {
    private final AirCraftRepository airCraftRepository;

    public AirCraftService(AirCraftRepository airCraftRepository) {
        this.airCraftRepository = airCraftRepository;
    }

    @Transactional
    public AirCraftDto createAirCraft(CreateAirCraftDto createAirCraftDto) {
        AirCraft airCraft = new AirCraft();
        airCraft.setNome(createAirCraftDto.nome());
        airCraft.setMarca(createAirCraftDto.marca());
        airCraft.setDescricao(createAirCraftDto.descricao());
        airCraft.setAno(createAirCraftDto.ano());
        airCraft.setVendido(createAirCraftDto.vendido());
        var savedAirCraft = airCraftRepository.save(airCraft);

        return AirCraftDto.fromEntity(savedAirCraft);
    }

    @Transactional
    public void deleteAirCraft(Long id) {
        if (!airCraftRepository.existsById(id)) {
            return;
        }

        airCraftRepository.deleteById(id);
    }

    @Transactional
    public AirCraftDto updateAirCraft(Long id, UpdateAirCraftDto updateAirCraft) {
        var airCraft = airCraftRepository.findById(id).orElseThrow(() -> new RuntimeException("Aeronave não encontrada"));

        airCraft.setNome(updateAirCraft.nome() == null || updateAirCraft.nome().isBlank() ? airCraft.getNome() : updateAirCraft.nome());
        airCraft.setMarca(updateAirCraft.marca() == null ? airCraft.getMarca() : updateAirCraft.marca());
        airCraft.setDescricao(updateAirCraft.descricao() == null || updateAirCraft.descricao().isBlank() ? airCraft.getDescricao() : updateAirCraft.descricao());
        airCraft.setAno(updateAirCraft.ano() == null ? airCraft.getAno() : updateAirCraft.ano());
        airCraft.setVendido(updateAirCraft.vendido() == airCraft.getVendido() ? airCraft.getVendido() : updateAirCraft.vendido());
        var updatedAirCraft = airCraftRepository.save(airCraft);

        return AirCraftDto.fromEntity(updatedAirCraft);
    }

    @Transactional(readOnly = true)
    public AirCraftDto getAirCraftById(Long id) {
        var airCraft = airCraftRepository.findById(id).orElseThrow(() -> new RuntimeException("Aeronave não encontrada"));
        return AirCraftDto.fromEntity(airCraft);
    }

    @Transactional(readOnly = true)
    public List<AirCraftDto> getAirCrafts() {
        LocalDateTime lastWeek = LocalDateTime.now().minusDays(7);
        var airCraft = airCraftRepository.findAllByCreatedAtAfter(lastWeek);

        return airCraft.stream().map(AirCraftDto::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public List<AirCraftDto> getAirCraft(String term) {
        LocalDateTime lastWeek = LocalDateTime.now().minusDays(7);
        var airCraft = airCraftRepository.findByTerm(term, lastWeek);

        return airCraft.stream().map(AirCraftDto::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public DashboardResponse dashboard() {
        Long unsold = airCraftRepository.countUnVendido();
        Long registeredLastWeek =
                airCraftRepository.countRegisteredLastWeek(LocalDateTime.now().minusDays(7));
        List<MarcaResponse> manufacturers =
                airCraftRepository.countByMarca();
        List<DecadeResponse> decades = airCraftRepository.countByDecade()
                .stream()
                .map(d -> new DecadeResponse(
                        d.getDecade(),
                        d.getYear(),
                        d.getQuantity()
                ))
                .toList();

        return new DashboardResponse(
                unsold,
                registeredLastWeek,
                manufacturers,
                decades
        );
    }
}
