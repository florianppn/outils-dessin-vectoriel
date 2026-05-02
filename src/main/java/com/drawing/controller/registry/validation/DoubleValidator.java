package com.drawing.controller.registry.validation;

import com.drawing.error.InvalidArgException;

public class DoubleValidator extends ArgsValidator {

    private int[] positions;

    public DoubleValidator(int... positions) {
        this.positions = positions;
    }

    @Override
    public void check(String[] args) {
        try {
            for (int position : positions) {
                Double.parseDouble(args[position]);
            }
        } catch (NumberFormatException e) {
            throw new InvalidArgException("Double attendu");
        }
    }

}
