package com.drawing;

import com.drawing.controller.Editor;
import com.drawing.controller.command.*;
import com.drawing.controller.validation.ArityValidator;
import com.drawing.controller.validation.ColorValidator;
import com.drawing.controller.validation.DoubleValidator;
import com.drawing.controller.validation.MinArityValidator;
import com.drawing.model.DrawingModel;
import com.drawing.view.GraphicViewer;

/**
 * Point d'entrée.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class EditMain {

    /**
     * @param args arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        DrawingModel model = new DrawingModel();
        new GraphicViewer(model);
        Editor editor = new Editor("Bienvenue sur l'editeur de dessin !\n");
        
        editor.register("rect", new RectCommand(model, new ArityValidator(new DoubleValidator(new ColorValidator(4), 0, 1, 2, 3),5)));
        editor.register("circ", new CircCommand(model, new ArityValidator(new DoubleValidator(new ColorValidator(3), 0, 1, 2), 4)));
        editor.register("line", new LineCommand(model, new ArityValidator(new DoubleValidator(new ColorValidator(4), 0, 1, 2, 3), 5)));
        editor.register("elli", new ElliCommand(model, new ArityValidator(new DoubleValidator(new ColorValidator(4), 0, 1, 2, 3), 5)));

        editor.register("grp", new GrpCommand(model, new MinArityValidator(2)));
        editor.register("ugrp", new UgrpCommand(model, new ArityValidator(1)));

        editor.register("load", new LoadCommand(model, new ArityValidator(1)));
        editor.register("save", new SaveCommand(model, new ArityValidator(1)));

        editor.register("new", new NewCommand(model, new ArityValidator(0)));
        editor.register("list", new ListCommand(model, new ArityValidator(0)));
        editor.register("quit", new QuitCommand(new ArityValidator(0)));

        editor.run();
    }
    
}