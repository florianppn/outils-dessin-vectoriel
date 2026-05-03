package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.controller.xml.XmlVisitor;
import com.drawing.model.shape.Drawable;

/**
 * Sérialise l'historique du modèle en XML et l'écrit dans un fichier.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class SaveCommand implements EditorCommand {

    private String[] args;

    /**
     * @param args {@code [0]} chemin du fichier de sortie
     */
    public SaveCommand(String[] args) {
        this.args = args;
    }

    /**
     * @param ctx contexte contenant le modèle à exporter
     * @return message de succès, ou chaîne vide si une erreur a été journalisée sur la sortie erreur
     */
    @Override
    public String execute(EditorContext ctx) {
        try {
            XmlVisitor visitor = new XmlVisitor();
            for (Drawable d : ctx.getDrawingModel().getHistory()) {
                d.accept(visitor);
            }
            visitor.save(args[0]);
            return "Le dessin a bien été sauvegardé.";
        } catch (Exception e) {
            System.err.println("Erreur sauvegarde : " + e.getMessage());
            return "";
        }
    }

}
