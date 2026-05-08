package com.drawing.controller.commandset;

import com.drawing.controller.command.GrpCommand;
import com.drawing.controller.command.UgrpCommand;
import com.drawing.controller.Editor;
import com.drawing.controller.registry.validation.ArityValidator;
import com.drawing.controller.registry.validation.MinArityValidator;

/**
 * Commandes de groupement : {@code grp}, {@code ugrp}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class GroupCommandSet implements CommandSet {

    /** {@inheritDoc} */
    @Override
    public void register(Editor editor) {
        editor.register("grp",  new MinArityValidator(2), GrpCommand::new);
        editor.register("ugrp", new ArityValidator(1), UgrpCommand::new);
    }

}
