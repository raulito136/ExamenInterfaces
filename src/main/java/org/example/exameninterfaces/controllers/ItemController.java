package org.example.exameninterfaces.controllers;

import org.example.exameninterfaces.entities.EstadisticasTienda;
import org.example.exameninterfaces.entities.Item;
import org.example.exameninterfaces.exceptions.ItemExistException;
import org.example.exameninterfaces.exceptions.ItemNotFoundException;
import org.example.exameninterfaces.services.ItemService;
import org.example.exameninterfaces.services.LLMService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que devuelve las prantillas HTML para la entidad Item.
 */
@Controller
@RequestMapping("/items")
class ItemController {

    private ItemService itemService;
    private LLMService llmService;
    public ItemController(ItemService itemService, LLMService llmService) {
        this.itemService = itemService;
        this.llmService = llmService;
    }


    @GetMapping("/lista")
    public String getAll(Model model){
        List<Item> items = itemService.getAll();
        model.addAttribute("items",items);
        return "lista";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Integer id, Model model){
        Item itemConsultado = itemService.buscarPorId(id).orElseThrow(()-> new ItemNotFoundException("El producto con ID " + id + " no existe."));
        model.addAttribute("item", itemConsultado);
        return "detalle";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormulario(Model model, @PathVariable Integer id) {
        Item itemConsultado = itemService.buscarPorId(id)
                .orElseThrow(() -> new ItemNotFoundException("El producto con ID " + id + " no existe."));

        model.addAttribute("item", itemConsultado);
        model.addAttribute("categorias", itemService.todasCategorias());
        return "editar";
    }

    @PostMapping("/editar")
    public String editarItem(@ModelAttribute Item item) {
        itemService.editar(item).orElseThrow(()->new ItemNotFoundException("El producto con ID " + item.get_id() + " no existe."));
        return "redirect:/items/lista";
    }

    @GetMapping("/estadisticas")
    public String estadisticasTienda(Model model) {
        EstadisticasTienda estadisticasTienda=itemService.estadisticasTienda();
        model.addAttribute("estadisticas", estadisticasTienda);
        model.addAttribute("ia",llmService.generarRespuesta(estadisticasTienda.toString()));
        return "estadisticas";
    }



}
