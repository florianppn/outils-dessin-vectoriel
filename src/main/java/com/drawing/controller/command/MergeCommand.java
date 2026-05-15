package com.drawing.controller.command;

import com.drawing.model.xml.XmlLoader;
import com.drawing.model.xml.XmlSaver;
import com.drawing.model.builder.DrawingBuilder;
import com.drawing.model.shape.Drawable;
import com.drawing.model.shape.Group;
import com.drawing.controller.validation.Validator;

import java.util.List;

/**
 * Commande de fusion de deux dessins.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class MergeCommand implements EditorCommand {

    private DrawingBuilder builder;
    private XmlLoader loader;
    private Validator validator;

    public MergeCommand(DrawingBuilder builder, XmlLoader loader, Validator validator) {
        this.builder = builder;
        this.loader = loader;
        this.validator = validator;
    }

    /** {@inheritDoc} */
    @Override
    public String execute(String[] params) {
        if (!validator.validate(params)) return "Les paramètres ne peuvent pas être traité.";
        try {
            XmlSaver saver = new XmlSaver();
            saver.reset();
            Group a = new Group(loadDrawables(params[0]), "a");
            Group b = new Group(loadDrawables(params[1]), "b");
            saver.visit(a);
            saver.visit(b);
            saver.save(params[2]);
            return "La fusion est terminée.";
        } catch (Exception e) {
            return "Erreur lors de la fusion : " + e.getMessage();
        }
    }

    /**
     * Charge les dessins à partir d'un fichier XML.
     * @param fileName Le nom du fichier XML à charger.
     * @return Une liste de Drawable représentant les formes chargées.
     * @throws Exception Si une erreur survient lors du chargement du fichier XML.
     */
    private List<Drawable> loadDrawables(String fileName) throws Exception {
        loader.load(fileName);
        return builder.getResult();
    }

}
