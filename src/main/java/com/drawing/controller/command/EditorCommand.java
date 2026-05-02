package com.drawing.controller.command;

import com.drawing.controller.EditorContext;

public interface EditorCommand {

    String execute(EditorContext ctx);

}
