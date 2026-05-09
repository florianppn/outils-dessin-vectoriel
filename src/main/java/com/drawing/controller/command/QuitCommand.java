package com.drawing.controller.command;

/**
 * Ferme la fenêtre graphique principale (fin de session utilisateur côté UI).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class QuitCommand implements EditorCommand {

    /** {@inheritDoc} */
    @Override
    public String execute() {
        System.exit(0);
        return "Au revoir.";
    }

}
