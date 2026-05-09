package com.drawing.controller.commandset;

import com.drawing.controller.command.LoadCommand;
import com.drawing.controller.command.SaveCommand;
import com.drawing.controller.Editor;
import com.drawing.controller.validation.ArityValidator;
import com.drawing.model.DrawingModel;

/**
 * Persistance : {@code save}, {@code load}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class FileCommandSet implements CommandSet {

    /** {@inheritDoc} */
    @Override
    public void register(Editor editor, DrawingModel drawingModel) {
        editor.register("save", new ArityValidator(1), args -> new SaveCommand(drawingModel, args));
        editor.register("load", new ArityValidator(1), args -> new LoadCommand(drawingModel, args));
    }

}