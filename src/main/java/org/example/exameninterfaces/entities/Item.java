package org.example.exameninterfaces.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa la entidad Ítem.
 * Esta clase está mapeada a la colección item en la base de datos MongoDB.
 */
@Document(collection = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    private String _id;
    private Integer id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private Double rate;
    private Long count;
    private String color;
    private String manufacturer;
    private String EAN;
    private String image;
}
