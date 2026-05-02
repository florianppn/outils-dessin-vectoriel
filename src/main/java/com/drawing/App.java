package com.drawing;

import com.drawing.controller.Editor;
import com.drawing.controller.EditorContext;
import com.drawing.controller.command.*;
import com.drawing.controller.commandset.*;
import com.drawing.controller.registry.CommandRegistry;
import com.drawing.model.DrawingModel;
import com.drawing.view.GraphicViewer;

import javax.swing.*;
import java.util.List;

public class App {
    public static void main(String[] args) {
        DrawingModel model = new DrawingModel();
        GraphicViewer viewer = new GraphicViewer();
        CommandRegistry registry = new CommandRegistry();
        Editor editor = new Editor(registry, new EditorContext(model, viewer));

        List.of(
                new DrawingCommandSet(),
                new GroupCommandSet(),
                new FileCommandSet(),
                new SystemCommandSet()
        ).forEach(s -> s.register(registry));

        editor.run();
    }
}