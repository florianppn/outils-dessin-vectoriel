package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.controller.xml.XmlLoader;
import com.drawing.model.shape.Drawable;
import com.drawing.view.GraphicViewer;

public class LoadCommand implements EditorCommand {
    
    private String[] args;

    public LoadCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute(EditorContext ctx) {
        try {
            ctx.getDrawingModel().reset();
            GraphicViewer graphicViewer = ctx.getGraphicViewer();
            new XmlLoader(ctx.getDrawingModel()).load(args[0]);
            for (Drawable d : ctx.getDrawingModel().getHistory()) {
                d.accept(ctx.getGraphicViewer());
            }
            return "Le dessin a bien été chargé.";
        } catch (Exception e) {
            System.err.println("Erreur chargement : " + e.getMessage());
            return "Le dessin n'a pas pu être chargé.";
        }
    }

}
