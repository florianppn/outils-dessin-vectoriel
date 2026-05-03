package com.drawing.controller.commandset;

import com.drawing.controller.command.LoadCommand;
import com.drawing.controller.command.SaveCommand;
import com.drawing.controller.registry.CommandRegistry;
import com.drawing.controller.registry.validation.ArityValidator;

/**
 * Persistance : {@code save}, {@code load}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class FileCommandSet implements CommandSet {

    /** {@inheritDoc} */
    @Override
    public void register(CommandRegistry registry) {
        registry.register("save", new ArityValidator(1), SaveCommand::new);
        registry.register("load", new ArityValidator(1), LoadCommand::new);
    }

}