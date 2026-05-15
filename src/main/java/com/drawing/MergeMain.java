package com.drawing;

import com.drawing.controller.Editor;
import com.drawing.controller.command.MergeCommand;
import com.drawing.controller.validation.ArityValidator;
import com.drawing.controller.xml.XmlLoader;
import com.drawing.model.builder.DrawingBuilder;
import com.drawing.model.builder.NormalDrawingBuilder;

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
        DrawingBuilder builder = new NormalDrawingBuilder();
        XmlLoader loader = new XmlLoader(builder);

        editor.register("merge", new MergeCommand(builder, loader, new ArityValidator(3)));
        
        editor.run();
    }

}
