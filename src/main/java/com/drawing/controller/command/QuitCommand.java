package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.view.GraphicViewer;

/**
 * Ferme la fenêtre graphique principale (fin de session utilisateur côté UI).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class QuitCommand implements EditorCommand {

    private String[] args;

    /**
     * @param args aucun argument attendu
     */
    public QuitCommand(String[] args) {
        this.args = args;
    }

    /**
     * @param ctx contexte contenant la {@link GraphicViewer} à fermer
     * @return message d'au revoir affiché en console
     */
    @Override
    public String execute(EditorContext ctx) {
        GraphicViewer graphicViewer = ctx.getGraphicViewer();
        graphicViewer.quit();
        return "Au revoir.";
    }

}
