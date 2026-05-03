package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.controller.xml.XmlLoader;

/**
 * Réinitialise le modèle puis charge un dessin depuis un fichier XML.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class LoadCommand implements EditorCommand {

    private String[] args;

    /**
     * @param args {@code [0]} chemin du fichier XML à lire
     */
    public LoadCommand(String[] args) {
        this.args = args;
    }

    /**
     * @param ctx contexte contenant le modèle à remplacer par le contenu du fichier
     * @return message de succès, ou chaîne vide si le chargement a échoué (erreur sur stderr)
     */
    @Override
    public String execute(EditorContext ctx) {
        try {
            ctx.getDrawingModel().reset();
            new XmlLoader(ctx.getDrawingModel()).load(args[0]);
            return "Le dessin a bien été chargé.";
        } catch (Exception e) {
            System.err.println("Erreur chargement : " + e.getMessage());
            return "";
        }
    }

}
