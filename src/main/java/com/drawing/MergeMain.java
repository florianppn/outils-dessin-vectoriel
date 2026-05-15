package com.drawing;

import com.drawing.controller.Editor;
import com.drawing.controller.command.MergeCommand;
import com.drawing.controller.validation.ArityValidator;

/**
 * Point d'entrée.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class MergeMain {

    /**
     * @param args arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        Editor editor = new Editor("Bienvenue sur l'éditeur de fusion !");

        editor.register("merge", new MergeCommand(new ArityValidator(3)));
        
        editor.run();
    }

}
