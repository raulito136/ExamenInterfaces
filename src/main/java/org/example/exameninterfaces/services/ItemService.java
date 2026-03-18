package org.example.exameninterfaces.services;

import org.example.exameninterfaces.entities.EstadisticasTienda;
import org.example.exameninterfaces.entities.Item;
import org.example.exameninterfaces.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Servicio encargado de gestionar la lógica de negocio para la entidad Item.
 * Se comunica con el ItemRepository para realizar las operaciones.
 */
@Service
public class ItemService {
    private ItemRepository itemRepository;
    private LLMService llmService;

    public ItemService(ItemRepository itemRepository, LLMService llmService) {
        this.itemRepository = itemRepository;
        this.llmService = llmService;
    }

    /**
     * Devuelve un item por su id
     * @param id
     * @return Item
     */
    public Optional<Item> buscarPorId(Integer id){
        Optional<Item> item = itemRepository.findItemById(id);
        return item;
    }

    public Optional<Item> buscarPorIdMongo(String id){
        Optional<Item> item =itemRepository.findById(id);
        if (item.isPresent()) {
            return item;
        }
        return Optional.empty();
    }

    public List<String> todasCategorias(){
        List<Item> items = itemRepository.findAll();
        Set<String> categorias = new HashSet<>();
        for (Item item : items) {
            categorias.add(item.getCategory());
        }
        return new ArrayList<>(categorias);
    }


    /**
     * Devuelve todos los items
     * @return Lista de items
     */
    public List<Item> getAll(){
        return itemRepository.findAll();
    }

    /**
     * Editar Item
     * @param item
     * @return Optional<Item>
     */

    public Optional<Item> editar(Item item){
        Optional<Item> itemOriginal=itemRepository.findById(item.get_id());
        if(itemOriginal.isPresent()){
            return Optional.of(itemRepository.save(item));
        }else{
            return Optional.empty();

        }
    }

    /**
     * Metodo para obtener estadisticas de la tienda
     * @return EstadisticasTienda
     */
    public EstadisticasTienda estadisticasTienda() {
        Integer stockInferiorA = 100;
        long contadorTotal = itemRepository.count();
        List<Item> itemsConStockInferior = itemRepository.getItemsByCountLessThan(Long.valueOf(stockInferiorA));
        List<Item> items = itemRepository.findAll();

        Set<String> fabricantesSet = new HashSet<>();

        for (Item item : items) {
            fabricantesSet.add(item.getManufacturer());
        }

        List<String> listaFabricantes = new ArrayList<>(fabricantesSet);
        EstadisticasTienda estadisticasTienda = new EstadisticasTienda(contadorTotal, itemsConStockInferior, listaFabricantes);
        estadisticasTienda.setFabricantes(listaFabricantes);

        return estadisticasTienda;
    }


}
