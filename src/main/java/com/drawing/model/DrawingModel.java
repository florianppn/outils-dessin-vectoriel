package com.drawing.model;

import com.drawing.model.shape.*;
import com.drawing.model.shape.Rectangle;
import com.drawing.util.AbstractListenableModel;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;

/**
 * Modèle du dessin : historique des {@link Drawable} et opérations de création / groupe / reset.
 * Chaque mutation notifie les {@link com.drawing.util.ModelListener observateurs}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class DrawingModel extends AbstractListenableModel {

    private List<Drawable> drawables = new ArrayList<>();

    /**
     * @return copie de l'historique (ordre d'affichage / d'énumération)
     */
    public List<Drawable> getDrawables() {
        return drawables;
    }

    /**
     * Remplace la liste de dessins par une autre liste.
     *
     * @param drawables est la liste contenant les dessins.
     */
    public void setDrawables(List<Drawable> drawables) {
        this.drawables = drawables;
        this.fireChange();
    }

    /**
     * Ajoute un cercle à l'historique et notifie les observateurs.
     *
     * @param cx abscisse du centre
     * @param cy ordonnée du centre
     * @param rad rayon
     * @param c couleur de tracé
     */
    public void createCircle(double cx, double cy, double rad, Color c) {
        Circle circle = new Circle(cx, cy, rad, c);
        drawables.add(circle);
        fireChange();
    }

    /**
     * Ajoute un rectangle à l'historique et notifie les observateurs.
     *
     * @param x0 abscisse du premier coin
     * @param y0 ordonnée du premier coin
     * @param x1 abscisse du coin opposé
     * @param y1 ordonnée du coin opposé
     * @param c couleur de tracé
     */
    public void createRectangle(double x0, double y0, double x1, double y1, Color c) {
        Rectangle rectangle = new Rectangle(x0, y0, x1, y1, c);
        drawables.add(rectangle);
        fireChange();
    }

    /**
     * Ajoute une ligne à l'historique et notifie les observateurs.
     *
     * @param x0 abscisse de l'extrémité de départ
     * @param y0 ordonnée de l'extrémité de départ
     * @param x1 abscisse de l'extrémité d'arrivée
     * @param y1 ordonnée de l'extrémité d'arrivée
     * @param c couleur de tracé
     */
    public void createLine(double x0, double y0, double x1, double y1, Color c) {
        Line line = new Line(x0, y0, x1, y1, c);
        drawables.add(line);
        fireChange();
    }

    /**
     * Ajoute une ellipse à l'historique et notifie les observateurs.
     *
     * @param x abscisse du centre
     * @param y ordonnée du centre
     * @param rx demi-grand axe horizontal
     * @param ry demi-grand axe vertical
     * @param c couleur de tracé
     */
    public void createEllipse(double x, double y, double rx, double ry, Color c) {
        Ellipse ellipse = new Ellipse(x, y, rx, ry, c);
        drawables.add(ellipse);
        fireChange();
    }

    /**
     * Regroupe les formes aux indices donnés dans un {@link Group} et notifie les observateurs.
     *
     * @param label nom du groupe
     * @param ranks indices 0-based dans l'historique courant des formes à regrouper
     */
    public void createGroup(String label, int... ranks) {
        Group group = new Group(label);
        List<Drawable> toRemove = new ArrayList<>();
        for (int i : ranks) {
            group.add(drawables.get(i));
            toRemove.add(drawables.get(i));
        }
        drawables.removeAll(toRemove);
        drawables.add(group);
        fireChange();
    }

    /**
     * Si l'élément à l'indice donné est un groupe, aplatit ses enfants dans l'historique et supprime le groupe.
     *
     * @param rank index dans l'historique (0-based)
     * @return {@code true} si un groupe a été supprimé
     */
    public boolean deleteGroup(int rank) {
        Drawable group = drawables.get(rank);
        if (group instanceof Group) {
            drawables.addAll(((Group) group).getDrawables());
            drawables.remove(rank);
            fireChange();
            return true;
        }
        fireChange();
        return false;
    }

    /** Vide l'historique et notifie les observateurs. */
    public void reset() {
        drawables.clear();
        fireChange();
    }

    /**
     * Liste numérotée des formes (une ligne par entrée), pour affichage console.
     *
     * @return texte multi-lignes ; ligne vide si l'historique est vide
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < drawables.size(); i++) {
            res.append(i + 1).append(" ").append(drawables.get(i).toString()).append("\n");
        }
        return res.toString();
    }

}