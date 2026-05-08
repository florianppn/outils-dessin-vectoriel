package com.drawing.controller.command;

import com.drawing.controller.EditorContext;
import com.drawing.controller.merge.DrawingMerger;
import com.drawing.controller.xml.XmlLoader;
import com.drawing.controller.xml.XmlSaver;
import com.drawing.model.builder.NormalDrawingBuilder;
import com.drawing.model.shape.Drawable;
import com.drawing.model.shape.Group;

import java.util.List;

/**
 * Commande de fusion de deux dessins.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class MergeCommand implements EditorCommand {

    private String[] args;

    public MergeCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute(EditorContext ctx) {
        DrawingMerger drawingMerger = new DrawingMerger();
        try {
            List<Drawable> a = loadDrawables(args[0]);
            List<Drawable> b = loadDrawables(args[1]);
            Group group = drawingMerger.merge(a, b);
            XmlSaver saver = new XmlSaver();
            group.accept(saver);
            saver.save(args[2]);
            return "La fusion est terminée.";
        } catch (Exception e) {
            System.err.println("Erreur lors de la fusion : " + e.getMessage());
            return "";
        }
    }

    private List<Drawable> loadDrawables(String fileName) throws Exception {
        NormalDrawingBuilder builder = new NormalDrawingBuilder();
        XmlLoader loader = new XmlLoader(builder);
        loader.load(fileName);
        return builder.getResult();
    }

}
