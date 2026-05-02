package com.drawing.controller.registry;

import com.drawing.controller.command.EditorCommand;

public interface CommandFactory {

    EditorCommand create (String[] args);

}
