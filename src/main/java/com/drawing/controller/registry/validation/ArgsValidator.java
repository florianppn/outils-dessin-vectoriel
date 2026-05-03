package com.drawing.controller.registry.validation;

/**
 * Chaîne de validation : exécute {@link #check(String[])} puis délègue au validateur suivant.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public abstract class ArgsValidator implements Validator {

    private Validator next;

    /**
     * @param next validateur suivant, ou {@code null} si fin de chaîne
     */
    public ArgsValidator(Validator next) {
        this.next = next;
    }

    @Override
    public void setNext(Validator next) {
        this.next = next;
    }

    /**
     * @param args arguments de la commande (sans le verbe), passés à tout le maillon de la chaîne
     */
    @Override
    public void validate(String[] args) {
        check(args);
        if (next != null) {
            next.validate(args);
        }
    }

    /**
     * Contrôle local sur les arguments (sans appeler explicitement le suivant).
     *
     * @param args arguments de la commande
     */
    public abstract void check(String[] args);

}
