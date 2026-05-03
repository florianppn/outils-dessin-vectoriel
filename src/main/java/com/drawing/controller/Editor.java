package com.drawing.controller;

import com.drawing.controller.registry.CommandRegistry;

import java.util.Scanner;

/**
 * Boucle interactive : lit une ligne sur l'entrée standard, dispatche le verbe
 * vers le {@link CommandRegistry}, exécute la commande et affiche le résultat.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class Editor {

    private CommandRegistry commandRegistry;
    private EditorContext ctx;

    /**
     * @param commandRegistry table des commandes enregistrées
     * @param ctx contexte passé à chaque {@link com.drawing.controller.command.EditorCommand#execute(EditorContext)}
     */
    public Editor(CommandRegistry commandRegistry, EditorContext ctx) {
        this.commandRegistry = commandRegistry;
        this.ctx = ctx;
    }

    /** Lance la boucle de lecture jusqu'à fermeture du flux d'entrée. */
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
        return commandRegistry.dispatch(args[0]).create(argsCmd).execute(ctx);
    }

}
