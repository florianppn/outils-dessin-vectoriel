package com.drawing.controller.validation;

/**
 * Validation des arguments d'une commande (souvent enchaînée via {@link ArgsValidator}).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public interface Validator {

    /**
     * Enchaîne un validateur suivant (selon l'implémentation ; peut être sans effet).
     *
     * @param validator maillon suivant dans la chaîne, ou {@code null}
     */
    void setNext(Validator validator);

    /**
     * Vérifie les arguments ; lève une {@link com.drawing.error.InvalidArgException} en cas d'échec.
     *
     * @param args arguments de la commande (sans le verbe)
     * @return true si validé sinon false.
     */
    boolean validate(String[] args);

}
