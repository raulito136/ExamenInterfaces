package org.example.exameninterfaces.exceptions;

/**
 * Excepción lanzada cuando el item ya existe en la BD.
 */
public class ItemExistException extends RuntimeException {
    public ItemExistException(String message) {
        super(message);
    }
}
