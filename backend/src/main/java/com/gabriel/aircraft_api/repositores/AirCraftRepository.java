package com.gabriel.aircraft_api.repositores;

import com.gabriel.aircraft_api.models.dto.MarcaResponse;
import com.gabriel.aircraft_api.models.entities.AirCraft;
import com.gabriel.aircraft_api.projection.DecadeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AirCraftRepository extends JpaRepository<AirCraft, Long> {

    @Query("""
            SELECT a
            FROM AirCraft a
            WHERE a.createdAt >= :date
            ORDER BY a.createdAt DESC
            """)
    List<AirCraft> findAllByCreatedAtAfter(LocalDateTime date);

    @Query("""
                SELECT a
                FROM AirCraft a
                WHERE
                    a.createdAt >= :date
                    AND
                    (LOWER(a.marca) LIKE LOWER(CONCAT('%', :term, '%'))
                    OR
                    LOWER(a.nome) LIKE LOWER(CONCAT('%', :term, '%')))
                ORDER BY a.createdAt DESC
            """)
    List<AirCraft> findByTerm(String term, LocalDateTime date);

    @Query("""
                SELECT COUNT(a)
                FROM AirCraft a
                WHERE a.vendido = false
            """)
    Long countUnVendido();

    @Query("""
                SELECT COUNT(a)
                FROM AirCraft a
                WHERE a.createdAt >= :date
            """)
    Long countRegisteredLastWeek(LocalDateTime date);

    @Query("""
                SELECT new com.gabriel.aircraft_api.models.dto.MarcaResponse(
                    a.marca,
                    COUNT(a)
                )
                FROM AirCraft a
                GROUP BY a.marca
                ORDER BY COUNT(a) DESC
            """)
    List<MarcaResponse> countByMarca();

    @Query(value = """
            SELECT
                FLOOR(ano / 10) * 10 AS decade,
                MIN(ano) AS year,
                COUNT(*) AS quantity
            FROM aircraft
            GROUP BY decade
            ORDER BY decade
            """, nativeQuery = true)
    List<DecadeProjection> countByDecade();
}
