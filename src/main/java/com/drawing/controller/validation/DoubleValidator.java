package com.drawing.controller.validation;

/**
 * Vérifie que les arguments aux indices donnés sont des nombres décimaux valides.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class DoubleValidator extends ArgsValidator {

    private int[] positions;

    /**
     * @param next validateur suivant
     * @param positions indices des arguments à interpréter comme {@code double}
     */
    public DoubleValidator(Validator next, int... positions) {
        super(next);
        this.positions = positions;
    }

    /**
     * @param positions indices des arguments à interpréter comme {@code double}
     */
    public DoubleValidator(int... positions) {
        super(null);
        this.positions = positions;
    }

    /** {@inheritDoc} */
    @Override
    public boolean check(String[] args) {
        try {
            for (int position : positions) {
                Double.parseDouble(args[position]);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
