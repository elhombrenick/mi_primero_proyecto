package org.aerolineas.controller.admin;

import org.aerolineas.entity.bean.Ruta;
import org.aerolineas.usecase.RutaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/rutas")
public class AdminRutaController {

    @Autowired
    private RutaUseCase rutaUseCase;

    @GetMapping
    public String listar(Model model) {
        //listar rutas
        model.addAttribute("lista", rutaUseCase.Listar());
        return "admin/rutas/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        //registrar ruta
        model.addAttribute("ruta", new Ruta());
        return "admin/rutas/registrar";
    }

    @PostMapping
    public String crear(@ModelAttribute Ruta ruta) {
        //crear ruta
        rutaUseCase.Registrar(ruta);
        return "redirect:/admin/rutas";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        //form editar ruta
        model.addAttribute("ruta", rutaUseCase.BuscarPorId(id));
        return "admin/rutas/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute Ruta ruta) {
        //actualizar ruta
        ruta.setIdRuta(id);
        rutaUseCase.Actualizar(ruta);
        return "redirect:/admin/rutas";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id) {
        //eliminar ruta
        rutaUseCase.Eliminar(id);
        return "redirect:/admin/rutas";
    }
}
