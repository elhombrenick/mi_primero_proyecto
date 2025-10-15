package org.aerolineas.controller.admin;

import org.aerolineas.entity.bean.Cliente;
import org.aerolineas.usecase.ClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/clientes")
public class AdminClienteController {

    @Autowired
    private ClienteUseCase clienteUseCase;

    @GetMapping
    public String listar(Model model) {
        //listar clientes
        model.addAttribute("lista", clienteUseCase.Listar());
        return "admin/clientes/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        //mostrar formulario registrar cliente
        model.addAttribute("cliente", new Cliente());
        return "admin/clientes/registrar";
    }

    @PostMapping
    public String crear(@ModelAttribute Cliente cliente) {
        //crear cliente
        clienteUseCase.Registrar(cliente);
        return "redirect:/admin/clientes";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        //mostrar formulario editar cliente
        model.addAttribute("cliente", clienteUseCase.BuscarPorId(id));
        return "admin/clientes/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute Cliente cliente) {
        //actualizar cliente
        cliente.setIdCliente(id);
        clienteUseCase.Actualizar(cliente);
        return "redirect:/admin/clientes";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id) {
        //eliminar cliente
        clienteUseCase.Eliminar(id);
        return "redirect:/admin/clientes";
    }
}
