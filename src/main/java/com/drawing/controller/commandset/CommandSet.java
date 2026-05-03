package com.drawing.controller.commandset;

import com.drawing.controller.registry.CommandRegistry;

/**
 * Groupe cohérent de commandes enregistrées dans un {@link CommandRegistry} (dessin, fichiers, etc.).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public interface CommandSet {

    /**
     * Enregistre toutes les commandes de cet ensemble dans le registre donné.
     *
     * @param registry table des commandes à laquelle ajouter les entrées de cet ensemble
     */
    void register(CommandRegistry registry);

}
