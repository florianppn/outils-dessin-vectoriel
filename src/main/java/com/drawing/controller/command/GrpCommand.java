package com.drawing.controller.command;

import com.drawing.model.DrawingModel;

/**
 * Regroupe plusieurs formes de l'historique courant dans un {@link com.drawing.model.shape.Group}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class GrpCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private String[] params;

    public GrpCommand(DrawingModel drawingModel, String[] params) {
        this.drawingModel = drawingModel;
        this.params = params;
    }

    /** {@inheritDoc} */
    @Override
    public String execute() {
        if(params[0].length() > 20) {
            return "Le nom du groupe ne doit pas faire plus de 20 caractères.";
        }
        int[] ranks = new int[params.length - 1];
        for (int i = 1; i < params.length; i++) {
            ranks[i - 1] = Integer.parseInt(params[i]) - 1;
        }
        drawingModel.createGroup(params[0], ranks);
        return "Le groupe a bien été crée.";
    }

}
