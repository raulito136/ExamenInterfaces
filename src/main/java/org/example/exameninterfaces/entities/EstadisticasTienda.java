package org.example.exameninterfaces.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Clase EstadisticasTienda
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticasTienda {
    private Long numeroTotal;
    private List<Item> itemsConStockInferior;
    private List<String> fabricantes;
}
