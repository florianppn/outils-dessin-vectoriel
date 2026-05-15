package com.drawing.controller.command;

import com.drawing.controller.converter.V2bmpConverter;
import com.drawing.controller.validation.Validator;

/**
 * Commande de conversion d'un dessin vectoriel (.vec) en image matricielle (.png).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class V2bmpCommand implements EditorCommand {

    private Validator validator;

    public V2bmpCommand(Validator validator) {
        this.validator = validator;
    }

    /** {@inheritDoc} */
    @Override
    public String execute(String[] params) {
        if (!validator.validate(params)) return "Les paramètres ne peuvent pas être traité.";
        try {
            new V2bmpConverter().convert(params[0], params[1]);
            return "Conversion réussie.";
        } catch (Exception e) {
            return "Erreur conversion : " + e.getMessage();
        }
    }

}
