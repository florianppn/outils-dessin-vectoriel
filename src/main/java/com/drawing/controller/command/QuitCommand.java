package com.drawing.controller.command;

import com.drawing.controller.validation.Validator;

/**
 * Ferme la fenêtre graphique principale (fin de session utilisateur côté UI).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class QuitCommand implements EditorCommand {

    private Validator validator;

    public QuitCommand(Validator validator) {
        this.validator = validator;
    }

    /** {@inheritDoc} */
    @Override
    public String execute(String[] params) {
        if (!validator.validate(params)) return "Les paramètres ne peuvent pas être traité.";
        System.exit(0);
        return "Au revoir.";
    }

}
