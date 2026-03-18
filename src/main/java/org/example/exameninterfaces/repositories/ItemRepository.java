package org.example.exameninterfaces.repositories;

import org.example.exameninterfaces.entities.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio de MongoDB para la entidad Item.
 * Proporciona acceso a las operaciones sobre MongoDB
 */
@Repository
public interface ItemRepository extends MongoRepository<Item,String> {

    Optional<Item> findItemById(Integer id);

    List<Item> getItemsByCountLessThan(Long countIsLessThan);
}
