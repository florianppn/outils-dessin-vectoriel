package com.drawing.controller.registry.validation;

import com.drawing.error.InvalidArgException;

/**
 * Vérifie qu'il y a au moins {@code min} arguments.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class MinArityValidator extends ArgsValidator {

    private int min;

    /**
     * @param next validateur suivant
     * @param min nombre minimum d'arguments (inclus)
     */
    public MinArityValidator(Validator next, int min) {
        super(next);
        this.min = min;
    }

    /**
     * @param min nombre minimum d'arguments (inclus)
     */
    public MinArityValidator(int min) {
        super(null);
        this.min = min;
    }

    /**
     * @param args arguments à valider
     * @throws com.drawing.error.InvalidArgException si {@code args.length < min}
     */
    @Override
    public void check(String[] args) {
        if (args.length < min) {
            throw new InvalidArgException("Trop élevé !");
        }
    }
}
