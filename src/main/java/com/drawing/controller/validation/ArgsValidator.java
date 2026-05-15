package com.drawing.controller.validation;

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

    /** {@inheritDoc} */
    @Override
    public boolean validate(String[] args) {
        boolean res = check(args);
        if (res) return res;
        if (next != null) next.validate(args);
        return false;
    }

    /**
     * Contrôle local sur les arguments (sans appeler explicitement le suivant).
     *
     * @param args arguments de la commande
     * @return true si valide sinon false
     */
    public abstract boolean check(String[] args);

}
