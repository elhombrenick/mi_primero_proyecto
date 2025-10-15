package org.aerolineas.controller.publico;

import org.aerolineas.entity.dto.CambioVueloDto;
import org.aerolineas.entity.dto.ReservaRequestDto;
import org.aerolineas.usecase.ReservaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/publico/reservas")
public class ReservaController {

    @Autowired
    private ReservaUseCase reservaUseCase;
    
    @GetMapping("/listar")
    public String listar(jakarta.servlet.http.HttpSession session, Model model) {
        //validar sesion
        Integer idCliente = (Integer) session.getAttribute("idCliente");
        if (idCliente == null) return "redirect:/publico/login?requiereSesion";
        //listar reservas por cliente
        model.addAttribute("lista", reservaUseCase.ListarPorCliente(idCliente));
        model.addAttribute("cambioVueloDto", new org.aerolineas.entity.dto.CambioVueloDto());
        model.addAttribute("idClienteActual", idCliente);
        return "publico/reservas-listar";
    }

    @PostMapping
    public String crear(@ModelAttribute ReservaRequestDto dto) {
        
        var r = new org.aerolineas.entity.bean.Reserva();
        var c = new org.aerolineas.entity.bean.Cliente(); c.setIdCliente(dto.getIdCliente());
        var v = new org.aerolineas.entity.bean.Vuelo();   v.setIdVuelo(dto.getIdVuelo());
        r.setCliente(c); r.setVuelo(v);
        reservaUseCase.Crear(r);
        return "redirect:/publico/reservas/listar?idCliente=" + dto.getIdCliente() + "&ok";
    }

    @PostMapping("/{id}/confirmar")
    public String confirmar(@PathVariable Integer id) {
       
        reservaUseCase.Confirmar(id);
        var r = reservaUseCase.BuscarPorId(id);
        Integer idCliente = (r != null && r.getCliente() != null) ? r.getCliente().getIdCliente() : null;
        String url = "/publico/reservas/listar" + (idCliente != null ? "?idCliente=" + idCliente + "&confirmada" : "?confirmada");
        return "redirect:" + url;
    }

    @PostMapping("/{id}/cancelar")
    public String cancelar(@PathVariable Integer id) {
        
        reservaUseCase.Cancelar(id);
        var r = reservaUseCase.BuscarPorId(id);
        Integer idCliente = (r != null && r.getCliente() != null) ? r.getCliente().getIdCliente() : null;
        String url = "/publico/reservas/listar" + (idCliente != null ? "?idCliente=" + idCliente + "&cancelada" : "?cancelada");
        return "redirect:" + url;
    }

    @PostMapping("/cambiar")
    public String cambiar(@ModelAttribute CambioVueloDto dto) {
     
        reservaUseCase.CambiarVuelo(dto.getIdReserva(), dto.getIdVueloNuevo());
        var r = reservaUseCase.BuscarPorId(dto.getIdReserva());
        Integer idCliente = (r != null && r.getCliente() != null) ? r.getCliente().getIdCliente() : null;
        String url = "/publico/reservas/listar" + (idCliente != null ? "?idCliente=" + idCliente + "&cambiada" : "?cambiada");
        return "redirect:" + url;
    }
}
