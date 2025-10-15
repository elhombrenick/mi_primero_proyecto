package org.aerolineas.controller.publico;

import java.util.List;
import org.aerolineas.entity.bean.Vuelo;
import org.aerolineas.entity.dto.BusquedaVueloDto;
import org.aerolineas.usecase.RutaUseCase;
import org.aerolineas.usecase.VueloUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/publico/vuelos")
public class BusquedaVuelosController {

    @Autowired
    private VueloUseCase vueloUseCase;

    @Autowired
    private RutaUseCase rutaUseCase;

    @GetMapping("/buscar")
    public String buscarForm(Model model) {
        //mostrar formulario de busqueda
        model.addAttribute("busquedaVueloDto", new org.aerolineas.entity.dto.BusquedaVueloDto());
        return "publico/vuelos-buscar";
    }

 // En resultados(...) agregar lectura de sesion
    @GetMapping("/resultados")
    public String resultados(@ModelAttribute org.aerolineas.entity.dto.BusquedaVueloDto dto,
                             jakarta.servlet.http.HttpSession session,
                             org.springframework.ui.Model model) {
        var lista = vueloUseCase.BuscarPorRuta(dto.getOrigen(), dto.getDestino());
        model.addAttribute("lista", lista);
        model.addAttribute("filtro", dto);

        Integer idCliente = (Integer) session.getAttribute("idCliente");
        if (idCliente != null) model.addAttribute("idClienteActual", idCliente);

        return "publico/vuelos-resultados";
    }


    @GetMapping("/destinos")
    public String destinos(Model model) {
        //listar rutas para mostrar destinos disponibles
        model.addAttribute("rutas", rutaUseCase.Listar());
        return "publico/destinos";
    }
}
