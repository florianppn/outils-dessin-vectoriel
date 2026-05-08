package com.drawing.controller.merge;

import com.drawing.model.shape.Drawable;
import com.drawing.model.shape.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitaire pour fusionner deux listes de {@link Drawable} en un seul groupe.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class DrawingMerger {

    /**
     * Fusionne les deux listes de dessins dans un groupe nommé "C" contenant deux sous-groupes "A" et "B".
     *
     * @param a sous-liste a
     * @param b sous-liste b
     * @return un groupe qui représente la fusion des deux sous-listes
     */
    public List<Drawable> merge(List<Drawable> a, List<Drawable> b) {
        Group groupA = new Group("A");
        groupA.setDrawables(new ArrayList<>(a));

        Group groupB = new Group("B");
        groupB.setDrawables(new ArrayList<>(b));

        List<Drawable> drawables = new ArrayList<>();
        drawables.add(groupA);
        drawables.add(groupB);
        return drawables;
    }

}
