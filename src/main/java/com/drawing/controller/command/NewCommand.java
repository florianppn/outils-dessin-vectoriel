package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.model.DrawingModel;
import com.drawing.view.GraphicViewer;

public class NewCommand implements EditorCommand {

    private String[] args;

    public NewCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute(EditorContext ctx) {
        DrawingModel d = ctx.getDrawingModel();
        GraphicViewer graphicViewer = ctx.getGraphicViewer();
        d.reset();
        graphicViewer.reset();
        return "Nouveau dessin.";
    }

}
