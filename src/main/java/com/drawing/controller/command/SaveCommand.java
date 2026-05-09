package com.drawing.controller.command;

import com.drawing.controller.xml.XmlSaver;
import com.drawing.model.DrawingModel;
import com.drawing.model.shape.Drawable;

/**
 * Sérialise l'historique du modèle en XML et l'écrit dans un fichier.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class SaveCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private String[] params;

    public SaveCommand(DrawingModel drawingModel, String[] params) {
        this.drawingModel = drawingModel;
        this.params = params;
    }

    /** {@inheritDoc} */
    @Override
    public String execute() {
        try {
            XmlSaver saver = new XmlSaver();
            for (Drawable d : drawingModel.getDrawables()) {
                d.accept(saver);
            }
            saver.save(params[0]);
            return "Le dessin a bien été sauvegardé.";
        } catch (Exception e) {
            System.err.println("Erreur sauvegarde : " + e.getMessage());
            return "";
        }
    }

}
