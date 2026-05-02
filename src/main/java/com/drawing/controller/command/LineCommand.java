package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.util.ColorDecode;
import com.drawing.model.DrawingModel;
import com.drawing.model.shape.Drawable;

import java.util.List;

public class LineCommand implements EditorCommand {

    private String[] args;

    public LineCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute(EditorContext ctx) {
        DrawingModel d = ctx.getDrawingModel();
        d.createLine(
            Double.parseDouble(args[0]),
            Double.parseDouble(args[1]),
            Double.parseDouble(args[2]),
            Double.parseDouble(args[3]),
            ColorDecode.decode(args[4].toUpperCase())
        );
        List<Drawable> history = d.getHistory();
        for (Drawable drawable : history) {
            drawable.accept(ctx.getGraphicViewer());
        }
        return "La ligne a bien été crée.";
    }

}
