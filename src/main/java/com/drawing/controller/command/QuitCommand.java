package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.view.GraphicViewer;

public class QuitCommand implements EditorCommand {

    private String[] args;

    public QuitCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute(EditorContext ctx) {
        GraphicViewer graphicViewer = ctx.getGraphicViewer();
        graphicViewer.quit();
        return "Au revoir.";
    }

}
