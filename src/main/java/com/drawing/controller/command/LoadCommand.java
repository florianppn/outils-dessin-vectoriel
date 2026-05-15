package com.drawing.controller.command;

import com.drawing.controller.xml.XmlLoader;
import com.drawing.model.DrawingModel;
import com.drawing.model.builder.NormalDrawingBuilder;
import com.drawing.controller.validation.Validator;

/**
 * Réinitialise le modèle puis charge un dessin depuis un fichier XML.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class LoadCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private Validator validator;

    public LoadCommand(DrawingModel drawingModel, Validator validator) {
        this.drawingModel = drawingModel;
        this.validator = validator;
    }

    /** {@inheritDoc} */
    @Override
    public String execute(String[] params) {
        if (!validator.validate(params)) return "Les paramètres ne peuvent pas être traité.";
        try {
            NormalDrawingBuilder builder = new NormalDrawingBuilder();
            new XmlLoader(builder).load(params[0]);
            drawingModel.setDrawables(builder.getResult());
            return "Le dessin a bien été chargé.";
        } catch (Exception e) {
            return "Erreur chargement : " + e.getMessage();
        }
    }

}
