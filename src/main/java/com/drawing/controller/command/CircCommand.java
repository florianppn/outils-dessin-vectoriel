package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.util.ColorDecode;
import com.drawing.model.DrawingModel;

/**
 * Ajoute un cercle au modèle (centre, rayon, couleur).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class CircCommand implements EditorCommand {

    private String[] args;

    /**
     * @param args {@code [0]} abscisse centre ; {@code [1]} ordonnée ; {@code [2]} rayon ;
     *             {@code [3]} nom de couleur
     */
    public CircCommand(String[] args) {
        this.args = args;
    }

    /**
     * @param ctx contexte contenant le modèle à modifier
     * @return message de confirmation affiché en console
     */
    @Override
    public String execute(EditorContext ctx) {
        DrawingModel d = ctx.getDrawingModel();
        d.createCircle(
            Double.parseDouble(args[0]),
            Double.parseDouble(args[1]),
            Double.parseDouble(args[2]),
            ColorDecode.decode(args[3].toUpperCase())
        );
        return "Le cercle a bien été crée.";
    }

}
