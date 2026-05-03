package com.drawing.controller.registry;

import com.drawing.controller.command.EditorCommand;

/**
 * Fabrique d'une commande à partir des arguments déjà validés (sans le verbe).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public interface CommandFactory {

    /**
     * @param args arguments spécifiques à la commande (peut être vide)
     * @return commande prête à l'exécution
     */
    EditorCommand create(String[] args);

}
