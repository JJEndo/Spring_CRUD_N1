package com.nebrija.crudN1.repository;

import com.nebrija.crudN1.model.EstadoProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nebrija.crudN1.model.Proyecto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    // Buscar un proyecto por su nombre (suponiendo que el nombre es único)
    Optional<Proyecto> findByNombre(String nombre);

    // Buscar proyectos que tengan un estado específico (por ejemplo, ACTIVO, EN_PROGRESO o FINALIZADO)
    List<Proyecto> findByEstado(EstadoProyecto estado);

    // Buscar proyectos cuya fecha de inicio se encuentre entre dos fechas
    List<Proyecto> findByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin);

}//cierra interface
