package com.drawing.controller.command;

import com.drawing.model.DrawingModel;
import com.drawing.controller.validation.Validator;

/**
 * Regroupe plusieurs formes de l'historique courant dans un {@link com.drawing.model.shape.Group}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class GrpCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private Validator validator;

    public GrpCommand(DrawingModel drawingModel, Validator validator) {
        this.drawingModel = drawingModel;
        this.validator = validator;
    }

    /** {@inheritDoc} */
    @Override
    public String execute(String[] params) {
        if (!validator.validate(params)) return "Les paramètres ne peuvent pas être traité.";
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
