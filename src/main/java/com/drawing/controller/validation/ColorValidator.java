package com.drawing.controller.validation;

import com.drawing.util.ColorDecode;

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

    /** {@inheritDoc} */
    @Override
    public boolean check(String[] args) {
        for (int i : positions) {
            boolean isValid = ColorDecode.isValid(args[i].toUpperCase());
            if (!isValid) return false;
        }
        return true;
    }

}
