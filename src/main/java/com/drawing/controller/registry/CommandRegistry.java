package com.drawing.controller.registry;

import com.drawing.controller.registry.validation.Validator;
import java.util.HashMap;
import java.util.Map;

/**
 * Table des commandes : associe un verbe à une fabrique qui valide les arguments puis crée l'{@link com.drawing.controller.command.EditorCommand}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class CommandRegistry {

    private Map<String, CommandFactory> commands;

    public CommandRegistry() {
        this.commands = new HashMap<>();
    }

    /**
     * @param line verbe de la commande
     * @return fabrique enregistrée, ou celle de {@code help} si le verbe est inconnu
     */
    public CommandFactory dispatch(String line) {
        CommandFactory commandFactory = commands.get(line);
        return (commandFactory != null) ? commandFactory : commands.get("help");
    }

    /**
     * Enregistre une commande : à l'exécution, le validateur est appliqué puis la fabrique crée la commande.
     *
     * @param verb mot-clé saisi en première position sur la ligne
     * @param validator chaîne ou validateur unique pour les arguments
     * @param factory création de la commande après validation
     */
    public void register(String verb, Validator validator, CommandFactory factory) {
        commands.put(verb, args -> {
            validator.validate(args);
            return factory.create(args);
        });
    }

}
