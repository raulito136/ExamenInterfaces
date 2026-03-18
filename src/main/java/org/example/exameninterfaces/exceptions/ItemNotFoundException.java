package org.example.exameninterfaces.exceptions;

/**
 * Excepción lanzada cuando no se encuentra un ítem en la base de datos.
 */
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
