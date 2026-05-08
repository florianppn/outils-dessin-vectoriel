package com.drawing.controller.commandset;

import com.drawing.controller.Editor;

/**
 * Interface représentant un ensemble de commandes à enregistrer dans le contrôleur.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public interface CommandSet {

    /**
     * Enregistre toutes les commandes de cet ensemble dans le registre donné.
     *
     * @param editor le contrôleur dans lequel les commandes seront exécutées
     */
    void register(Editor editor);

}
