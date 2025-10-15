package org.aerolineas.controller.publico;

import org.aerolineas.entity.dto.VentaRequestDto;
import org.aerolineas.usecase.VentaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; //import opcional para el GET listar
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/publico/ventas")
public class VentaController {

    @Autowired
    private VentaUseCase ventaUseCase;

 // VentaController.java (solo cambia el GET listar)
    @GetMapping("/listar")
    public String listar(jakarta.servlet.http.HttpSession session, org.springframework.ui.Model model) {
        //validar sesion
        Integer idCliente = (Integer) session.getAttribute("idCliente");
        if (idCliente == null) return "redirect:/publico/login?requiereSesion";
        //listar ventas por cliente
        model.addAttribute("lista", ventaUseCase.ListarPorCliente(idCliente));
        return "publico/ventas-listar";
    }


    @PostMapping
    public String generar(@ModelAttribute VentaRequestDto dto) {
        //generar venta desde dto
        var vta = new org.aerolineas.entity.bean.Venta();
        var cli = new org.aerolineas.entity.bean.Cliente(); cli.setIdCliente(dto.getIdCliente());
        var vue = new org.aerolineas.entity.bean.Vuelo();   vue.setIdVuelo(dto.getIdVuelo());
        vta.setCliente(cli); vta.setVuelo(vue); vta.setTotal(dto.getTotal());
        ventaUseCase.Generar(vta);
        return "redirect:/publico/ventas/listar?idCliente=" + dto.getIdCliente() + "&ok";
    }

    @PostMapping("/{id}/anular")
    public String anular(@PathVariable Integer id, @RequestParam Integer idCliente) {
        //anular venta
        ventaUseCase.Anular(id);
        return "redirect:/publico/ventas/listar?idCliente=" + idCliente + "&anulada";
    }
}
