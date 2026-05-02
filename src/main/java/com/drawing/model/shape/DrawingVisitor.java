package com.drawing.model.shape;

public interface DrawingVisitor {

    void visit(Line line);

    void visit(Rectangle rectangle);

    void visit(Circle circle);

    void visit(Ellipse ellipse);

    void visit(Group group);

}