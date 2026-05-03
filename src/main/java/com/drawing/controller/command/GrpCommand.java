package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.model.DrawingModel;

/**
 * Regroupe plusieurs formes de l'historique courant dans un {@link com.drawing.model.shape.Group}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class GrpCommand implements EditorCommand {

    private String[] args;

    /**
     * @param args {@code [0]} libellé du groupe ; {@code [1..]} indices entiers dans l'historique
     *             du modèle (0-based, comme les positions internes du modèle)
     */
    public GrpCommand(String[] args) {
        this.args = args;
    }

    /**
     * @param ctx contexte contenant le modèle à modifier
     * @return message de confirmation affiché en console
     */
    @Override
    public String execute(EditorContext ctx) {
        DrawingModel d = ctx.getDrawingModel();
        int[] ranks = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            ranks[i - 1] = Integer.parseInt(args[i]);
        }
        d.createGroup(args[0], ranks);
        return "Le groupe a bien été crée.";
    }

}
