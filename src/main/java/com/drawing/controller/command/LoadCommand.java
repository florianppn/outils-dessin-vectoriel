package com.drawing.controller.command;

import com.drawing.controller.xml.XmlLoader;
import com.drawing.model.DrawingModel;
import com.drawing.model.builder.NormalDrawingBuilder;

/**
 * Réinitialise le modèle puis charge un dessin depuis un fichier XML.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class LoadCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private String[] params;

    public LoadCommand(DrawingModel drawingModel, String[] params) {
        this.drawingModel = drawingModel;
        this.params = params;
    }

    /** {@inheritDoc} */
    @Override
    public String execute() {
        try {
            NormalDrawingBuilder builder = new NormalDrawingBuilder();
            new XmlLoader(builder).load(params[0]);
            drawingModel.setDrawables(builder.getResult());
            return "Le dessin a bien été chargé.";
        } catch (Exception e) {
            System.err.println("Erreur chargement : " + e.getMessage());
            return "";
        }
    }

}
