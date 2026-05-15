package com.drawing.controller.validation;

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

    /** {@inheritDoc} */
    @Override
    public boolean check(String[] args) {
        if (args.length < min) {
           return false;
        }
        return true;
    }
}
