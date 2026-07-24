package com.gabriel.aircraft_api.models.entities;

import com.gabriel.aircraft_api.models.enums.Marca;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "aircraft")
@NoArgsConstructor
public class AirCraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "marca", nullable = false)
    @Enumerated(EnumType.STRING)
    private Marca marca;

    @Column(name = "ano", nullable = false)
    private int ano;

    @Column(name = "vendido", nullable = false)
    private Boolean vendido = true;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
