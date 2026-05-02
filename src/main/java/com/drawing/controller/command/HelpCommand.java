package com.drawing.controller.command;

import com.drawing.controller.EditorContext;

public class HelpCommand implements EditorCommand {
    
    private String[] args;

    public HelpCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute(EditorContext ctx) {
        return "Commandes disponibles :\n"
            + "  rect x0 y0 x1 y1 color\n"
            + "  circ cx cy rad color\n"
            + "  line x0 y0 x1 y1 color\n"
            + "  elli x y rx ry color\n"
            + "  grp label r1,r2...\n"
            + "  ugrp rank\n"
            + "  save filename.vec\n"
            + "  load filename.vec\n"
            + "  new\n"
            + "  list\n"
            + "  quit\n";
    }

}
