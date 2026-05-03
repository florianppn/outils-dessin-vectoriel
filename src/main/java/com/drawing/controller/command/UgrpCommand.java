package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.model.DrawingModel;

/**
 * Dissout un groupe : replace le groupe par ses enfants dans l'historique si l'élément ciblé est un groupe.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class UgrpCommand implements EditorCommand {

    private String[] args;

    /**
     * @param args {@code [0]} rang affiché par {@code list} (1 pour la première ligne, etc.)
     */
    public UgrpCommand(String[] args) {
        this.args = args;
    }

    /**
     * @param ctx contexte contenant le modèle à modifier
     * @return message indiquant succès ou échec (élément non groupe)
     */
    @Override
    public String execute(EditorContext ctx) {
        DrawingModel d = ctx.getDrawingModel();
        boolean res = d.deleteGroup(
            Integer.parseInt(args[0])-1
        );
        return res ? "Le groupe a bien été supprimé." : "Ce n'est pas un groupe.";
    }

}
