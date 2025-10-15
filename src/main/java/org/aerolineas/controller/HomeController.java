package org.aerolineas.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; //import necesario
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String inicio(HttpSession session, Model model) {
        //mostrar home simple con estado de sesion
        model.addAttribute("idCliente", session.getAttribute("idCliente"));
        model.addAttribute("nombreCliente", session.getAttribute("nombreCliente"));
        return "home";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        //alias de inicio
        model.addAttribute("idCliente", session.getAttribute("idCliente"));
        model.addAttribute("nombreCliente", session.getAttribute("nombreCliente"));
        return "home";
    }
}
