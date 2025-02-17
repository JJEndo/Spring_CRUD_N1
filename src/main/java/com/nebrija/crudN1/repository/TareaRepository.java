package com.nebrija.crudN1.repository;

import com.nebrija.crudN1.model.EstadoTarea;
import com.nebrija.crudN1.model.Proyecto;
import com.nebrija.crudN1.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    // Buscar tareas asociadas a un proyecto (utilizando la entidad Proyecto)
    List<Tarea> findByProyecto(Proyecto proyecto);

    // Buscar tareas asociadas a un proyecto usando el id del proyecto
    List<Tarea> findByProyectoId(Long proyectoId);

    // Buscar tareas por su estado (por ejemplo, PENDIENTE, EN_CURSO o COMPLETADA)
    List<Tarea> findByEstado(EstadoTarea estado);

    // Buscar tareas cuya fecha límite es anterior a una fecha dada (por ejemplo, para encontrar tareas vencidas)
    List<Tarea> findByFechaLimiteBefore(LocalDate fecha);

}//cierra interface
