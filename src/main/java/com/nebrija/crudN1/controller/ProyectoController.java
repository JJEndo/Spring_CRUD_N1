package com.nebrija.crudN1.controller;


import com.nebrija.crudN1.model.Proyecto;
import com.nebrija.crudN1.repository.ProyectoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    private ProyectoRepository proyectoRepository;

    // Constructor para inyectar el repositorio (Evita NullPointerException)
    public ProyectoController(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    // Listar todos los proyectos
    @GetMapping({"", "/"})
    public String listarProyectos(Model model) {
        model.addAttribute("proyectos", proyectoRepository.findAll());
        return "proyectos/lista"; // Vista Thymeleaf: src/main/resources/templates/proyectos/lista.html
    }

    // Mostrar formulario de creación de proyecto
    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("estados", com.nebrija.crudN1.model.EstadoProyecto.values());
        return "proyectos/formulario"; //Vista con formulario para crear proyecto
    }

    // Guardar nuevo proyecto
    @PostMapping("/guardar")
    public String guardarProyecto(@ModelAttribute Proyecto proyecto, Model model) {
        proyectoRepository.save(proyecto);
        return "redirect:/proyectos";
    }

    // Ver detalles de un proyecto y sus tareas
    @GetMapping("/{id}")
    public String verProyecto(@PathVariable Long id, Model model) {
        Proyecto proyecto = proyectoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado"));
        model.addAttribute("proyecto", proyecto);
        return "proyectos/detalle";
    }

    //Eliminar un proyecto
    @GetMapping("/eliminar/{id}")
    public String eliminarProyecto(@PathVariable Long id, Model model) {
        proyectoRepository.deleteById(id);
        return "redirect:/proyectos";
    }

}//cierra clase
