package com.drawing.controller;

import com.drawing.controller.registry.CommandFactory;
import com.drawing.controller.registry.CommandRegistry;
import com.drawing.controller.registry.validation.*;

import java.util.Scanner;

public class Editor {

    private CommandRegistry commandRegistry;
    private EditorContext ctx;

    public Editor(CommandRegistry commandRegistry, EditorContext ctx) {
        this.commandRegistry = commandRegistry;
        this.ctx = ctx;
    }

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

    public String handleLine(String[] args) {
        String[] args_cmd = new String[args.length - 1];
        System.arraycopy(args, 1, args_cmd, 0, args.length - 1);
        return commandRegistry.dispatch(args[0]).create(args_cmd).execute(ctx);
    }

}
