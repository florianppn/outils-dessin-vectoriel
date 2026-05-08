package com.drawing;

import com.drawing.controller.Editor;
import com.drawing.controller.EditorContext;
import com.drawing.controller.commandset.*;
import com.drawing.model.DrawingModel;
import com.drawing.view.GraphicViewer;

import java.util.List;

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
        GraphicViewer viewer = new GraphicViewer(model);
        Editor editor = new Editor(new EditorContext(model, viewer));

        List.of(
                new DrawingCommandSet(),
                new GroupCommandSet(),
                new FileCommandSet(),
                new SystemCommandSet()
        ).forEach(s -> s.register(editor));
        editor.run();
    }
    
}