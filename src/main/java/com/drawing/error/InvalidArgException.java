package com.drawing.error;

/**
 * Argument de commande ou de validation invalide.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class InvalidArgException extends RuntimeException {

    /**
     * @param message détail affiché ou journalisé
     */
    public InvalidArgException(String message) {
        super(message);
    }

}