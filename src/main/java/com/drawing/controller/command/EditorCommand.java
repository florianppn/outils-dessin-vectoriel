package com.drawing.controller.command;

/**
 * Action invocable par l'éditeur (ligne de commande). Chaque implémentation
 * exécute une opération via le contexte et renvoie un message pour la console.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public interface EditorCommand {

    /**
     * Exécute l'action de la commande en s'appuyant sur le contexte (modèle, vue, etc.)
     * et renvoie le message à afficher dans la console.
     *
     * @return texte de retour affiché à l'utilisateur après la commande
     */
    String execute();

}
