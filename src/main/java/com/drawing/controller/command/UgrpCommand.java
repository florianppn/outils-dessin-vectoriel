package com.drawing.controller.command;

import com.drawing.model.DrawingModel;

/**
 * Dissout un groupe : replace le groupe par ses enfants dans l'historique si l'élément ciblé est un groupe.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class UgrpCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private String[] params;

    public UgrpCommand(DrawingModel drawingModel, String[] params) {
        this.drawingModel = drawingModel;
        this.params = params;
    }

    /** {@inheritDoc} */
    @Override
    public String execute() {
        boolean res = drawingModel.deleteGroup(
            Integer.parseInt(params[0])-1
        );
        return res ? "Le groupe a bien été supprimé." : "Ce n'est pas un groupe.";
    }

}
