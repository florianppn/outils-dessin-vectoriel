package com.drawing;

import com.drawing.controller.Editor;
import com.drawing.controller.command.V2bmpCommand;
import com.drawing.controller.registry.validation.ArityValidator;

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
        Editor editor = new Editor("Bienvenue sur l'editeur de conversion !");
        editor.register("v2bmp",
                new ArityValidator(2),
                V2bmpCommand::new
        );
        editor.run();
    }

}
