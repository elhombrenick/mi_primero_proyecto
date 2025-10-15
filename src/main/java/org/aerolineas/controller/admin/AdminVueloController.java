package org.aerolineas.controller.admin;

import org.aerolineas.entity.bean.Vuelo;
import org.aerolineas.usecase.AvionUseCase;
import org.aerolineas.usecase.RutaUseCase;
import org.aerolineas.usecase.VueloUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/vuelos")
public class AdminVueloController {

    @Autowired
    private VueloUseCase vueloUseCase;

    @Autowired
    private AvionUseCase avionUseCase;

    @Autowired
    private RutaUseCase rutaUseCase;

    @GetMapping
    public String listar(Model model) {
        
        model.addAttribute("lista", vueloUseCase.Listar());
        return "admin/vuelos/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        
        model.addAttribute("vuelo", new Vuelo());
        model.addAttribute("aviones", avionUseCase.Listar());
        model.addAttribute("rutas", rutaUseCase.Listar());
        return "admin/vuelos/registrar";
    }

    @PostMapping
    public String crear(@ModelAttribute Vuelo vuelo) {
        
        vueloUseCase.Registrar(vuelo);
        return "redirect:/admin/vuelos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
       
        model.addAttribute("vuelo", vueloUseCase.BuscarPorId(id));
        model.addAttribute("aviones", avionUseCase.Listar());
        model.addAttribute("rutas", rutaUseCase.Listar());
        return "admin/vuelos/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute Vuelo vuelo) {
        
        vuelo.setIdVuelo(id);
        vueloUseCase.Actualizar(vuelo);
        return "redirect:/admin/vuelos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id) {
        
        vueloUseCase.Eliminar(id);
        return "redirect:/admin/vuelos";
    }
}
