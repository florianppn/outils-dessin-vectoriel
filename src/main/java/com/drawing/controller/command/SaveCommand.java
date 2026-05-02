package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.controller.xml.XmlVisitor;
import com.drawing.model.shape.Drawable;

public class SaveCommand implements EditorCommand {

    private String[] args;

    public SaveCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute(EditorContext ctx) {
        try {
            XmlVisitor visitor = new XmlVisitor();
            for (Drawable d : ctx.getDrawingModel().getHistory()) {
                d.accept(visitor);
            }
            visitor.save(args[0]);
        } catch (Exception e) {
            System.err.println("Erreur sauvegarde : " + e.getMessage());
        }
        return "Le dessin a bien été sauvegardé.";
    }

}
