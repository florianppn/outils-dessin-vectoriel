package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.util.ColorDecode;
import com.drawing.model.DrawingModel;

/**
 * Ajoute un rectangle au modèle à partir de coordonnées et d'une couleur nommée.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class RectCommand implements EditorCommand {

    private String[] args;

    /**
     * @param args {@code [0..3]} coins opposés (x0, y0, x1, y1) ; {@code [4]} nom de couleur
     */
    public RectCommand(String[] args) {
        this.args = args;
    }

    /**
     * @param ctx contexte contenant le modèle à modifier
     * @return message de confirmation affiché en console
     */
    @Override
    public String execute(EditorContext ctx) {
        DrawingModel d = ctx.getDrawingModel();
        d.createRectangle(
            Double.parseDouble(args[0]),
            Double.parseDouble(args[1]),
            Double.parseDouble(args[2]),
            Double.parseDouble(args[3]),
            ColorDecode.decode(args[4].toUpperCase())
        );
        return "Le rectangle a bien été crée.";
    }

}
