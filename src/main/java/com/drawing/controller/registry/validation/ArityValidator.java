package com.drawing.controller.registry.validation;

import com.drawing.error.InvalidArgException;

public class ArityValidator extends ArgsValidator {

    private int expected;

    public ArityValidator(int expected) {
        this.expected = expected;
    }

    @Override
    public void check(String[] args) {
        if (args.length != expected) {
            throw new InvalidArgException("Pas le bon nombre d'arguments !");
        }
    }

}
