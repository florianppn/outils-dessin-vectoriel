package com.drawing.controller;

import com.drawing.controller.registry.CommandFactory;
import com.drawing.controller.registry.validation.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe principale du contrôleur : elle gère la boucle de lecture et l'exécution des commandes.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class Editor {

    private Map<String, CommandFactory> commands;
    private EditorContext ctx;

    public Editor(EditorContext ctx) {
        this.commands = new HashMap<>();
        this.ctx = ctx;
    }

    /**
     * Lance la boucle de lecture jusqu'à fermeture du flux d'entrée.
     */
    public void run() {
        System.out.println("Bienvenue ! Tapez 'help' pour voir les commandes.\n");
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) continue;
                try {
                    System.out.println(handleLine(input.split("\\s+")));
                } catch (Exception e) {
                    System.err.println("Erreur : " + e.getMessage());
                }
            }
        }
    }

    /**
     * Interprète une ligne déjà découpée en mots : {@code args[0]} est le verbe,
     * le reste constitue les arguments passés à la fabrique de commande.
     *
     * @param args tableau non vide (au moins le nom de la commande)
     * @return message renvoyé par la commande exécutée
     */
    public String handleLine(String[] args) {
        String[] argsCmd = new String[args.length - 1];
        System.arraycopy(args, 1, argsCmd, 0, args.length - 1);
        CommandFactory commandFactory = commands.get(args[0]);
        return (commandFactory != null) ? commandFactory.create(argsCmd).execute(ctx) : commands.get("help").create(argsCmd).execute(ctx);
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
