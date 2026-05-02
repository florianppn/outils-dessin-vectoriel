package com.drawing.controller.command;

import java.util.List;

import com.drawing.controller.EditorContext;
import com.drawing.model.DrawingModel;
import com.drawing.model.shape.Drawable;

public class GrpCommand implements EditorCommand {

    private String[] args;

    public GrpCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute(EditorContext ctx) {
        DrawingModel d = ctx.getDrawingModel();
        int[] ranks = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            ranks[i - 1] = Integer.parseInt(args[i]);
        }
        d.createGroup(args[0], ranks);
        List<Drawable> history = d.getHistory();
        for (Drawable drawable : history) {
            drawable.accept(ctx.getGraphicViewer());
        }
        return "Le groupe a bien été crée.";
    }

}
