package com.example.catalogo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "catalogo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Catalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fk_categoria")
    private Categorias categorias;
    @Column(nullable = false)
    private String nombre;
    private String pathImg;
}
