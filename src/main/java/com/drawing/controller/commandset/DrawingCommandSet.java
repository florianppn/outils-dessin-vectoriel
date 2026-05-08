package com.drawing.controller.commandset;

import com.drawing.controller.command.CircCommand;
import com.drawing.controller.command.ElliCommand;
import com.drawing.controller.command.LineCommand;
import com.drawing.controller.command.RectCommand;
import com.drawing.controller.Editor;
import com.drawing.controller.registry.validation.ArityValidator;
import com.drawing.controller.registry.validation.ColorValidator;
import com.drawing.controller.registry.validation.DoubleValidator;

/**
 * Commandes de création de primitives : {@code rect}, {@code circ}, {@code line}, {@code elli}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class DrawingCommandSet implements CommandSet {

    /** {@inheritDoc} */
    @Override
    public void register(Editor editor) {
        editor.register("rect",
                new ArityValidator(new DoubleValidator(new ColorValidator(4), 0, 1, 2, 3),5),
                RectCommand::new
        );
        editor.register("circ",
                new ArityValidator(new DoubleValidator(new ColorValidator(3), 0, 1, 2), 4),
                CircCommand::new
        );
        editor.register("line",
                new ArityValidator(new DoubleValidator(new ColorValidator(4), 0, 1, 2, 3), 5),
                LineCommand::new
        );
        editor.register("elli",
                new ArityValidator(new DoubleValidator(new ColorValidator(4), 0, 1, 2, 3), 5),
                ElliCommand::new
        );
    }

}
