package com.drawing.controller.validation;


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

    /** {@inheritDoc} */
    @Override
    public boolean check(String[] args) {
        if (args.length != expected) {
            return false;
        }
        return true;
    }

}
