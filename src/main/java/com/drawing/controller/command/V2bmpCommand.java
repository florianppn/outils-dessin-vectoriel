package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.controller.converter.V2bmpConverter;

/**
 * Commande de conversion d'un dessin vectoriel (.vec) en image matricielle (.png).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class V2bmpCommand implements EditorCommand {

    private final String[] args;

    public V2bmpCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute(EditorContext ctx) {
        try {
            new V2bmpConverter().convert(args[0], args[1]);
            return "Conversion réussie.";
        } catch (Exception e) {
            System.err.println("Erreur conversion : " + e.getMessage());
            return "Echec de la conversion.";
        }
    }

}
