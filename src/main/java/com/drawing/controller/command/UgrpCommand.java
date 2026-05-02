package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.model.DrawingModel;

public class UgrpCommand implements EditorCommand {

    private String[] args;

    public UgrpCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute(EditorContext ctx) {
        DrawingModel d = ctx.getDrawingModel();
        d.deleteGroup(
            Integer.parseInt(args[0])-1
        );
        return "Le groupe a bien été supprimé.";
    }

}
