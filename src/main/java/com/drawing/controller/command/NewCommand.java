package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.model.DrawingModel;

/**
 * Vide le modèle (nouveau dessin vierge).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class NewCommand implements EditorCommand {

    private String[] args;

    /**
     * @param args aucun argument attendu (tableau éventuellement vide)
     */
    public NewCommand(String[] args) {
        this.args = args;
    }

    /**
     * @param ctx contexte contenant le modèle à réinitialiser
     * @return message de confirmation affiché en console
     */
    @Override
    public String execute(EditorContext ctx) {
        DrawingModel d = ctx.getDrawingModel();
        d.reset();
        return "Nouveau dessin.";
    }

}
