package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.util.ColorDecode;
import com.drawing.model.DrawingModel;

/**
 * Ajoute une ellipse au modèle (centre, demi-axes, couleur).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class ElliCommand implements EditorCommand {

    private String[] args;

    /**
     * @param args {@code [0]} centre x ; {@code [1]} centre y ; {@code [2]} rx ; {@code [3]} ry ;
     *             {@code [4]} nom de couleur
     */
    public ElliCommand(String[] args) {
        this.args = args;
    }

    /**
     * @param ctx contexte contenant le modèle à modifier
     * @return message de confirmation affiché en console
     */
    @Override
    public String execute(EditorContext ctx) {
        DrawingModel d = ctx.getDrawingModel();
        d.createEllipse(
            Double.parseDouble(args[0]),
            Double.parseDouble(args[1]),
            Double.parseDouble(args[2]),
            Double.parseDouble(args[3]),
            ColorDecode.decode(args[4].toUpperCase())
        );
        return "L'Ellipse a bien été crée.";
    }

}
