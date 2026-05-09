package com.drawing.controller.commandset;

import com.drawing.controller.command.GrpCommand;
import com.drawing.controller.command.UgrpCommand;
import com.drawing.controller.Editor;
import com.drawing.controller.validation.ArityValidator;
import com.drawing.controller.validation.MinArityValidator;
import com.drawing.model.DrawingModel;

/**
 * Commandes de groupement : {@code grp}, {@code ugrp}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class ShapeGroupCommandSet implements CommandSet {

    /** {@inheritDoc} */
    @Override
    public void register(Editor editor, DrawingModel drawingModel) {
        editor.register("grp",  new MinArityValidator(2), args -> new GrpCommand(drawingModel, args));
        editor.register("ugrp", new ArityValidator(1), args -> new UgrpCommand(drawingModel, args));
    }

}
