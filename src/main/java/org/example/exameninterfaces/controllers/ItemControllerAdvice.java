package org.example.exameninterfaces.controllers;

import org.example.exameninterfaces.exceptions.ItemExistException;
import org.example.exameninterfaces.exceptions.ItemNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controlador de excepciones para la entidad Item.
 */
@ControllerAdvice
class ItemControllerAdvice {
    /**
     * Captura la excepcion ItemNotFoundException y la maneja.
     * @param ex
     * @param model
     * @return plantilla error.html
     */
    @ExceptionHandler(ItemNotFoundException.class)
    public String handleNotFound(ItemNotFoundException ex, Model model) {
        model.addAttribute("mensaje", ex.getMessage());
        model.addAttribute("codigo", 404);
        return "error";
    }

    /**
     * Captura la excepcion ItemExistException y la maneja.
     * @param ex
     * @param model
     * @return plantilla error.html
     */
    @ExceptionHandler(ItemExistException.class)
    public String handleExist(ItemExistException ex, Model model) {
        model.addAttribute("mensaje", ex.getMessage());
        model.addAttribute("codigo", 409);
        return "error";
    }

    /**
     * Captura cualquier otra excepcion y la maneja.
     * @param ex
     * @param model
     * @return plantilla error.html
     */
    @ExceptionHandler(Exception.class)
    public String handleGeneric(Exception ex, Model model) {
        model.addAttribute("mensaje", "Ha ocurrido un error inesperado");
        model.addAttribute("codigo", 500);
        return "error";
    }
}
