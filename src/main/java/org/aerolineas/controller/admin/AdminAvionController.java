package org.aerolineas.controller.admin;

import org.aerolineas.entity.bean.Avion;
import org.aerolineas.usecase.AvionUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/aviones")
public class AdminAvionController {

    @Autowired
    private AvionUseCase avionUseCase;

    @GetMapping
    public String listar(Model model) {
        //listar aviones
        model.addAttribute("lista", avionUseCase.Listar());
        return "admin/aviones/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        //form registrar avion
        model.addAttribute("avion", new Avion());
        return "admin/aviones/registrar";
    }

    @PostMapping
    public String crear(@ModelAttribute Avion avion) {
        //crear avion
        avionUseCase.Registrar(avion);
        return "redirect:/admin/aviones";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        //form editar avion
        model.addAttribute("avion", avionUseCase.BuscarPorId(id));
        return "admin/aviones/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute Avion avion) {
        //actualizar avion
        avion.setIdAvion(id);
        avionUseCase.Actualizar(avion);
        return "redirect:/admin/aviones";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id) {
        //eliminar avion
        avionUseCase.Eliminar(id);
        return "redirect:/admin/aviones";
    }
}
