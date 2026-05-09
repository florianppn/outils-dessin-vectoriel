package com.drawing;

import com.drawing.controller.Editor;
import com.drawing.controller.commandset.*;
import com.drawing.model.DrawingModel;

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
        Editor editor = new Editor();

        GroupCommandSet commands = new GroupCommandSet();
        commands.add(new DrawingCommandSet());
        commands.add(new ShapeGroupCommandSet());
        commands.add(new FileCommandSet());
        commands.add(new SystemCommandSet());
        
        commands.register(editor, model);
        editor.run();
    }
    
}