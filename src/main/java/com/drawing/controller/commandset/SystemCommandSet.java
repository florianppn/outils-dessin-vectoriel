package com.drawing.controller.commandset;

import com.drawing.controller.command.ListCommand;
import com.drawing.controller.command.NewCommand;
import com.drawing.controller.command.QuitCommand;
import com.drawing.controller.Editor;
import com.drawing.controller.registry.validation.ArityValidator;
import com.drawing.model.DrawingModel;

/**
 * Commandes générales : {@code new}, {@code list}, {@code quit}, {@code help}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class SystemCommandSet implements CommandSet {

        /** {@inheritDoc} */
        @Override
        public void register(Editor editor, DrawingModel drawingModel) {
            editor.register("new",  new ArityValidator(0), args -> new NewCommand(drawingModel));
            editor.register("list", new ArityValidator(0), args -> new ListCommand(drawingModel));
            editor.register("quit", new ArityValidator(0), args -> new QuitCommand());
        }

}
