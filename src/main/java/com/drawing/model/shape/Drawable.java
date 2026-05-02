package com.drawing.model.shape;

public interface Drawable {

    void accept(DrawingVisitor visitor);

}