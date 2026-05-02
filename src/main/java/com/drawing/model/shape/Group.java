package com.drawing.model.shape;

import java.util.*;

public class Group implements Drawable {

    private List<Drawable> drawables;
    private String name;

    public Group(String name) {
        this.drawables = new ArrayList<>();
        this.name = name;
    }

    public List<Drawable> getDrawables() {
        return drawables;
    }

    public Drawable getDrawable(int id) {
        return drawables.get(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void add(Drawable d) {
        drawables.add(d);
    }

    public void remove(Drawable d) {
        drawables.remove(d);
    }

    @Override
    public void accept(DrawingVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return
            "group ["+
            name+
            "]"
        ;
    }

}