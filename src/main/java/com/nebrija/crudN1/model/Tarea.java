package com.nebrija.crudN1.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Asegura la generación automática de ID
    private Integer id;

    private String titulo;

    private String descripcion;

    @Column(name = "fecha_limite")
    private LocalDate fechaLimite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private  EstadoTarea estado;

    // Relación de muchos a uno con Proyectos
    @ManyToOne
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    public Tarea() {}

    public Tarea(int id, String titulo, String descripcion, LocalDate fechaLimite, EstadoTarea estado, Proyecto proyecto) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
        this.proyecto = proyecto;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", fechaLimite=" + fechaLimite +
                ", estado=" + estado +
                ", proyecto=" + proyecto +
                '}';
    }
}//cierra clase
