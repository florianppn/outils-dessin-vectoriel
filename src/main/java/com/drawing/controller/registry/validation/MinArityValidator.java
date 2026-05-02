package com.drawing.controller.registry.validation;

import com.drawing.error.InvalidArgException;

public class MinArityValidator extends ArgsValidator {

    private int min;

    public MinArityValidator(int min) {
        this.min = min;
    }

    @Override
    public void check(String[] args) {
        if (args.length < min) {
            throw new InvalidArgException("Trop élevé !");
        }
    }
}
