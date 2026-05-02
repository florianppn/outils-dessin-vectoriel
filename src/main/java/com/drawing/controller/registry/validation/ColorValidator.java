package com.drawing.controller.registry.validation;

import com.drawing.util.ColorDecode;
import com.drawing.error.InvalidArgException;

public class ColorValidator extends ArgsValidator {

    private int[] positions;

    public ColorValidator(int... positions) {
        this.positions = positions;
    }

    @Override
    public void check(String[] args) {
        for (int i : positions) {
            boolean isValid = ColorDecode.isValid(args[i].toUpperCase());
            if (!isValid) throw new InvalidArgException("Ce n'est pas une couleur !");
        }
    }

}
