package com.drawing.controller.registry.validation;

import com.drawing.error.InvalidArgException;

/**
 * Vérifie que le nombre d'arguments est exactement celui attendu.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class ArityValidator extends ArgsValidator {

    private int expected;

    /**
     * @param next validateur suivant
     * @param expected nombre d'arguments requis
     */
    public ArityValidator(Validator next, int expected) {
        super(next);
        this.expected = expected;
    }

    /**
     * @param expected nombre d'arguments requis
     */
    public ArityValidator(int expected) {
        super(null);
        this.expected = expected;
    }

    /**
     * @param args arguments à valider
     * @throws com.drawing.error.InvalidArgException si {@code args.length != expected}
     */
    @Override
    public void check(String[] args) {
        if (args.length != expected) {
            throw new InvalidArgException("Pas le bon nombre d'arguments !");
        }
    }

}
