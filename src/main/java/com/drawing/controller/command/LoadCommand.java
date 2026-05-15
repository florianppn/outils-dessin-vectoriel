package com.drawing.controller.command;

import com.drawing.model.xml.XmlLoader;
import com.drawing.model.DrawingModel;
import com.drawing.model.builder.DrawingBuilder;
import com.drawing.controller.validation.Validator;

/**
 * Réinitialise le modèle puis charge un dessin depuis un fichier XML.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class LoadCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private DrawingBuilder builder;
    private XmlLoader loader;
    private Validator validator;

    public LoadCommand(DrawingModel drawingModel, DrawingBuilder builder, XmlLoader loader, Validator validator) {
        this.drawingModel = drawingModel;
        this.builder = builder;
        this.loader = loader;
        this.validator = validator;
    }

    /** {@inheritDoc} */
    @Override
    public String execute(String[] params) {
        if (!validator.validate(params)) return "Les paramètres ne peuvent pas être traité.";
        try {
            loader.load(params[0]);
            drawingModel.setDrawables(builder.getResult());
            return "Le dessin a bien été chargé.";
        } catch (Exception e) {
            return "Erreur chargement : " + e.getMessage();
        }
    }

}
