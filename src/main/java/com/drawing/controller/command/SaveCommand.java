package com.drawing.controller.command;

import com.drawing.model.xml.XmlSaver;
import com.drawing.model.DrawingModel;
import com.drawing.model.shape.Drawable;
import com.drawing.controller.validation.Validator;

/**
 * Sérialise l'historique du modèle en XML et l'écrit dans un fichier.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class SaveCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private XmlSaver saver;
    private Validator validator;

    public SaveCommand(DrawingModel drawingModel, XmlSaver saver, Validator validator) {
        this.drawingModel = drawingModel;
        this.saver = saver;
        this.validator = validator;
    }

    /** {@inheritDoc} */
    @Override
    public String execute(String[] params) {
        if (!validator.validate(params)) return "Les paramètres ne peuvent pas être traité.";
        try {
            saver.reset();
            for (Drawable d : drawingModel.getDrawables()) {
                d.accept(saver);
            }
            saver.save(params[0]);
            return "Le dessin a bien été sauvegardé.";
        } catch (Exception e) {
            return "Erreur sauvegarde : " + e.getMessage();
        }
    }

}
