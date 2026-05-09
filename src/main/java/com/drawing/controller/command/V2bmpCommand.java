package com.drawing.controller.command;

import com.drawing.controller.converter.V2bmpConverter;

/**
 * Commande de conversion d'un dessin vectoriel (.vec) en image matricielle (.png).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class V2bmpCommand implements EditorCommand {

    private final String[] params;

    public V2bmpCommand(String[] params) {
        this.params = params;
    }

    /** {@inheritDoc} */
    @Override
    public String execute() {
        try {
            new V2bmpConverter().convert(params[0], params[1]);
            return "Conversion réussie.";
        } catch (Exception e) {
            System.err.println("Erreur conversion : " + e.getMessage());
            return "Echec de la conversion.";
        }
    }

}
