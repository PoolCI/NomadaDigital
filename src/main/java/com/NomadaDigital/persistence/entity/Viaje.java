package com.NomadaDigital.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "viaje")
@Data
@NoArgsConstructor
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "codigo_unico")
    private String codigoUnico;

    @Column(name = "destino", nullable = false, length = 100)
    private String destino;

    @Column(name = "duracion_dias", nullable = false)
    private Integer duracionDias;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "descripcion", length = 1000)
    private String descripcion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "capacidad_maxima", nullable = false)
    private Integer capacidadMaxima;

    @Column(name = "lugares_disponibles")
    private Integer lugaresDisponibles;

    @Column(name = "activo")
    private Boolean activo;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL)
    private List<Reserva> reservas = new ArrayList<>();

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();
}
