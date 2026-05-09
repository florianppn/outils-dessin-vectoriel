package com.drawing.controller;

import com.drawing.controller.command.EditorCommand;

/**
 * Fabrique d'une commande à partir des arguments déjà validés (sans le verbe).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public interface CommandCreator {

    /**
     * @param args arguments spécifiques à la commande (peut être vide)
     * @return commande prête à l'exécution
     */
    EditorCommand create(String[] args);

}
