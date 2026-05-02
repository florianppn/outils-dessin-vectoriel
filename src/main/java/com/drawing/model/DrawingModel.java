package com.drawing.model;

import com.drawing.model.shape.*;
import com.drawing.model.shape.Rectangle;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;

public class DrawingModel {

    private List<Drawable> history = new ArrayList<>();

    public List<Drawable> getHistory() {
        return history;
    }

    public void createCircle(double cx, double cy, double rad, Color c) {
        Circle circle = new Circle(cx, cy, rad, c);
        history.add(circle);
    }

    public void createRectangle(double x0, double y0, double x1, double y1, Color c) {
        Rectangle rectangle = new Rectangle(x0, y0, x1, y1, c);
        history.add(rectangle);
    }

    public void createLine(double x0, double y0, double x1, double y1, Color c) {
        Line line = new Line(x0, y0, x1, y1, c);
        history.add(line);
    }

    public void createEllipse(double x, double y, double rx, double ry, Color c) {
        Ellipse ellipse = new Ellipse(x, y, rx, ry, c);
        history.add(ellipse);
    }

    public void createGroup(String label, int... ranks) {
        Group group = new Group(label);
        List<Drawable> toRemove = new ArrayList<>();
        for (int i : ranks) {
            group.add(history.get(i));
            toRemove.add(history.get(i));
        }
        history.removeAll(toRemove);
        history.add(group);
    }

    public boolean deleteGroup(int rank) {
        Drawable group = history.get(rank);
        if (group instanceof Group) {
            history.addAll(((Group) group).getDrawables());
            history.remove(rank);
            return true;
        }
        return false;
    }

    public void reset() {
        history.clear();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < history.size(); i++) {
            res.append(i+1).append(" ").append(history.get(i).toString()).append("\n");
        }
        return res.toString();
    }

}