package com.drawing.controller.registry.validation;

public abstract class ArgsValidator implements Validator {

    private Validator successor;

    public ArgsValidator then (ArgsValidator next) {
        this.successor = next;
        return next;
    }

    @Override
    public void validate(String[] args) {
        check(args);
        if (successor != null) {
            successor.validate(args);
        }
    }

    public void add(Validator successor) {
        this.successor = successor;
    }

    public abstract void check(String[] args);

}
