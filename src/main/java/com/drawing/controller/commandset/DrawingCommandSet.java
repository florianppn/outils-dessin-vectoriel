package com.drawing.controller.commandset;

import com.drawing.controller.command.CircCommand;
import com.drawing.controller.command.ElliCommand;
import com.drawing.controller.command.LineCommand;
import com.drawing.controller.command.RectCommand;
import com.drawing.controller.registry.CommandRegistry;
import com.drawing.controller.registry.validation.ArityValidator;
import com.drawing.controller.registry.validation.ColorValidator;
import com.drawing.controller.registry.validation.DoubleValidator;

public class DrawingCommandSet implements CommandSet {

    @Override
    public void register(CommandRegistry registry) {
        registry.register("rect",
                new ArityValidator(5)
                  .then(new DoubleValidator(0, 1, 2, 3))
                  .then(new ColorValidator(4)),
                RectCommand::new
        );
        registry.register("circ",
                new ArityValidator(5)
                  .then(new DoubleValidator(0, 1, 2, 3))
                  .then(new ColorValidator(4)),
                CircCommand::new
        );
        registry.register("line",
                new ArityValidator(5)
                  .then(new DoubleValidator(0, 1, 2, 3))
                   .then(new ColorValidator(4)),
                LineCommand::new
        );
        registry.register("elli",
                new ArityValidator(5)
                  .then(new DoubleValidator(0, 1, 2, 3))
                  .then(new ColorValidator(4)),
                ElliCommand::new
        );
    }

}
