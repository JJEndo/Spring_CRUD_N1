package com.nebrija.crudN1.controller;

import com.nebrija.crudN1.model.EstadoTarea;
import com.nebrija.crudN1.model.Proyecto;
import com.nebrija.crudN1.model.Tarea;
import com.nebrija.crudN1.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.nebrija.crudN1.repository.TareaRepository;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    // Mostrar formulario de creación de nueva tarea
    @GetMapping({"", "/{proyectoId}"})
    public String mostrarFormularioCreacion(
            @PathVariable Long proyectoId,
            @NonNull Model model){
        Tarea tarea = new Tarea();
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado"));
        tarea.setProyecto(proyecto);

        model.addAttribute("tarea", tarea);
        model.addAttribute("estados", com.nebrija.crudN1.model.EstadoTarea.values());
        model.addAttribute("proyectos", proyectoRepository.findAll());
        return "tareas/formulario"; // Vista para el formulario de creación de tarea
    }

    // Guardar nueva tarea
    @PostMapping("/guardar")
    public String guardarTarea(@ModelAttribute Tarea tarea) {
        tareaRepository.save(tarea);
        // Redirige a los detalles del proyecto al que pertenece la tarea
        return "redirect:/proyectos/" + tarea.getProyecto().getId();
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Tarea tarea = tareaRepository.findById(id.longValue())
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada"));
        model.addAttribute("tarea", tarea);
        model.addAttribute("estados", EstadoTarea.values());
        model.addAttribute("proyectos", proyectoRepository.findAll());
        return "tareas/formulario"; // Reutilizas la misma plantilla de creación/edición
    }

    @PostMapping("/actualizarEstado")
    @ResponseBody
    public String actualizarEstado(@RequestParam("id") Integer id,
                                   @RequestParam("estado") EstadoTarea estado) {
        // Buscamos la tarea a actualizar
        Tarea tarea = tareaRepository.findById(id.longValue())
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada"));
        // Actualizamos el estado
        tarea.setEstado(estado);
        tareaRepository.save(tarea);
        // Retornamos un mensaje de éxito (podrías retornar JSON si lo prefieres)
        return "OK";
    }

    // eliminar nueva tarea
    @GetMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id){
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Tarea no encontrada"));
        int proyectoId = tarea.getProyecto().getId();
        tareaRepository.deleteById(id);
        return "redirect:/proyectos/" + proyectoId;
    }

}//cierra clase
