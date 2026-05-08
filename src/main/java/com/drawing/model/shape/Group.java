package com.drawing.model.shape;

import java.util.*;

/**
 * Composite : regroupe plusieurs {@link Drawable} sous un nom.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class Group implements Drawable {

    private List<Drawable> drawables;
    private String name;

    /**
     * @param name libellé du groupe (affichage / XML)
     */
    public Group(String name) {
        this.drawables = new ArrayList<>();
        this.name = name;
    }

    /**
     * @return liste modifiable des enfants (à manipuler avec prudence depuis l'extérieur)
     */
    public List<Drawable> getDrawables() {
        return drawables;
    }

    /**
     * @param drawables liste de dessin.
     */
    public void setDrawables(List<Drawable> drawables) {
        this.drawables = drawables;
    }

    /**
     * @param id index 0-based dans la liste des enfants
     * @return enfant à cet index
     * @throws IndexOutOfBoundsException si l'index est hors limites
     */
    public Drawable getDrawable(int id) {
        return drawables.get(id);
    }

    /** @return nom du groupe */
    public String getName() {
        return name;
    }

    /** @param newName nouveau nom du groupe */
    public void setName(String newName) {
        name = newName;
    }

    /** @param d forme à ajouter au groupe */
    public void add(Drawable d) {
        drawables.add(d);
    }

    /** @param d forme à retirer du groupe (égalité {@link List#remove(Object)}) */
    public void remove(Drawable d) {
        drawables.remove(d);
    }

    /** {@inheritDoc} */
    @Override
    public void accept(DrawingVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * @return libellé du groupe pour la console
     */
    @Override
    public String toString() {
        return
            "group ["+
            name+
            "]"
        ;
    }

}