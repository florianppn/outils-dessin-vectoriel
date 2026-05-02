package com.drawing.controller.commandset;

import com.drawing.controller.command.LoadCommand;
import com.drawing.controller.command.SaveCommand;
import com.drawing.controller.registry.CommandRegistry;
import com.drawing.controller.registry.validation.ArityValidator;

public class FileCommandSet implements CommandSet {

    @Override
    public void register(CommandRegistry registry) {
        registry.register("save", new ArityValidator(1), SaveCommand::new);
        registry.register("load", new ArityValidator(1), LoadCommand::new);
    }

}