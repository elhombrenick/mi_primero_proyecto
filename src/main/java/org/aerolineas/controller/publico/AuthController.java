package org.aerolineas.controller.publico;

import jakarta.servlet.http.HttpSession;
import org.aerolineas.entity.dto.ClienteRegistroDto;
import org.aerolineas.entity.bean.Cliente;
import org.aerolineas.usecase.ClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; //import necesario
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/publico")
public class AuthController {

    @Autowired
    private ClienteUseCase clienteUseCase;

    @GetMapping("/login")
    public String loginForm(Model model) {
        //mostrar formulario de ingreso simple
        model.addAttribute("clienteRegistroDto", new ClienteRegistroDto());
        return "publico/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute ClienteRegistroDto dto, HttpSession session) {
        //registrar o usar datos para crear cliente y guardar id en sesion
        var c = new Cliente();
        c.setNombre(dto.getNombre());
        c.setDireccion(dto.getDireccion());
        clienteUseCase.Registrar(c);
        //importante: tras registrar, el repositorio le asigna id
        session.setAttribute("idCliente", c.getIdCliente());
        session.setAttribute("nombreCliente", c.getNombre());
        return "redirect:/home?ingreso";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        //cerrar sesion
        session.invalidate();
        return "redirect:/home?salida";
    }

    @GetMapping("/registro")
    public String registroForm(Model model) {
        //redirigir a login por simplicidad
        model.addAttribute("clienteRegistroDto", new ClienteRegistroDto());
        return "publico/login";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute ClienteRegistroDto dto, HttpSession session) {
        //registrar y loguear
        var c = new Cliente();
        c.setNombre(dto.getNombre());
        c.setDireccion(dto.getDireccion());
        clienteUseCase.Registrar(c);
        session.setAttribute("idCliente", c.getIdCliente());
        session.setAttribute("nombreCliente", c.getNombre());
        return "redirect:/home?registrado";
    }
}
