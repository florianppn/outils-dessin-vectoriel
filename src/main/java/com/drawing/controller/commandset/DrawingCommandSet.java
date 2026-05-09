package com.drawing.controller.commandset;

import com.drawing.controller.command.CircCommand;
import com.drawing.controller.command.ElliCommand;
import com.drawing.controller.command.LineCommand;
import com.drawing.controller.command.RectCommand;
import com.drawing.controller.Editor;
import com.drawing.controller.validation.ArityValidator;
import com.drawing.controller.validation.ColorValidator;
import com.drawing.controller.validation.DoubleValidator;
import com.drawing.model.DrawingModel;

/**
 * Commandes de création de primitives : {@code rect}, {@code circ}, {@code line}, {@code elli}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class DrawingCommandSet implements CommandSet {

    /** {@inheritDoc} */
    @Override
    public void register(Editor editor, DrawingModel drawingModel) {
        editor.register("rect",
                new ArityValidator(new DoubleValidator(new ColorValidator(4), 0, 1, 2, 3),5),
                args -> new RectCommand(drawingModel, args)
        );
        editor.register("circ",
                new ArityValidator(new DoubleValidator(new ColorValidator(3), 0, 1, 2), 4),
                args -> new CircCommand(drawingModel, args)
        );
        editor.register("line",
                new ArityValidator(new DoubleValidator(new ColorValidator(4), 0, 1, 2, 3), 5),
                args -> new LineCommand(drawingModel, args)
        );
        editor.register("elli",
                new ArityValidator(new DoubleValidator(new ColorValidator(4), 0, 1, 2, 3), 5),
                args -> new ElliCommand(drawingModel, args)
        );
    }

}
