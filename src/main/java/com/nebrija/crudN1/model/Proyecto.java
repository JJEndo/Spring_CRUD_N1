package com.nebrija.crudN1.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoProyecto estado;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarea> tareas;

    public Proyecto() {}

    public Proyecto(int id, String nombre, String descripcion, LocalDate fechaInicio, EstadoProyecto estado, List<Tarea> tareas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        this.tareas = tareas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public EstadoProyecto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProyecto estado) {
        this.estado = estado;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }


    @Override
    public String toString() {
        return "Proyecto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", estado=" + estado +
                ", tareas=" + tareas +
                '}';
    }
}//cierra clase
