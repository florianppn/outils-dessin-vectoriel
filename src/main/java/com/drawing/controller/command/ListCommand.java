package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.model.DrawingModel;

/**
 * Retourne la liste numérotée des formes du modèle (texte pour la console).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class ListCommand implements EditorCommand {

    private String[] args;

    /**
     * @param args aucun argument attendu
     */
    public ListCommand(String[] args) {
        this.args = args;
    }

    /**
     * @param ctx contexte contenant le modèle à lister
     * @return résultat de {@link DrawingModel#toString()}
     */
    @Override
    public String execute(EditorContext ctx) {
        DrawingModel d = ctx.getDrawingModel();
        return d.toString();
    }

}
