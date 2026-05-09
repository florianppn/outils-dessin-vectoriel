package com.drawing.controller.validation;

import com.drawing.util.ColorDecode;
import com.drawing.error.InvalidArgException;

/**
 * Vérifie que les arguments aux indices donnés sont des noms de couleur reconnus.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class ColorValidator extends ArgsValidator {

    private int[] positions;

    /**
     * @param next validateur suivant
     * @param positions indices des arguments représentant une couleur
     */
    public ColorValidator(Validator next, int... positions) {
        super(next);
        this.positions = positions;
    }

    /**
     * @param positions indices des arguments représentant une couleur
     */
    public ColorValidator(int... positions) {
        super(null);
        this.positions = positions;
    }

    /**
     * @param args arguments à valider
     * @throws com.drawing.error.InvalidArgException si une couleur attendue est inconnue
     */
    @Override
    public void check(String[] args) {
        for (int i : positions) {
            boolean isValid = ColorDecode.isValid(args[i].toUpperCase());
            if (!isValid) throw new InvalidArgException("Ce n'est pas une couleur !");
        }
    }

}
