package com.drawing;

import com.drawing.controller.Editor;
import com.drawing.controller.command.V2bmpCommand;
import com.drawing.model.converter.V2bmpConverter;
import com.drawing.controller.validation.ArityValidator;

/**
 * Point d'entrée.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class V2bmpMain {

    /**
     * @param args arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        V2bmpConverter converter = new V2bmpConverter();
        Editor editor = new Editor("Bienvenue sur l'editeur de conversion !");

        editor.register("v2bmp", new V2bmpCommand(converter, new ArityValidator(2)));

        editor.run();
    }

}
