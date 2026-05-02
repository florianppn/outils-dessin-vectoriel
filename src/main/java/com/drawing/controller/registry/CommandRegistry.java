package com.drawing.controller.registry;

import com.drawing.controller.registry.validation.ArgsValidator;
import com.drawing.controller.registry.validation.ArityValidator;
import com.drawing.controller.registry.validation.ColorValidator;
import com.drawing.controller.registry.validation.DoubleValidator;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    private Map<String, CommandFactory> commands;

    public CommandRegistry() {
        this.commands = new HashMap<>();
    }

    public CommandFactory dispatch(String line) {
        CommandFactory commandFactory = commands.get(line);
        return (commandFactory != null) ? commandFactory : commands.get("help");
    }

    public void register(String verb, ArgsValidator validator, CommandFactory factory) {
        commands.put(verb, args -> {
            validator.validate(args);
            return factory.create(args);
        });
    }

}
