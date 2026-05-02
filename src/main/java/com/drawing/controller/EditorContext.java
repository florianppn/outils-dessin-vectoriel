package com.drawing.controller;

import com.drawing.model.DrawingModel;
import com.drawing.view.GraphicViewer;

public class EditorContext {

    private DrawingModel drawingModel;
    private GraphicViewer graphicViewer;

    public EditorContext(DrawingModel drawingModel, GraphicViewer graphicViewer) {
        this.drawingModel = drawingModel;
        this.graphicViewer = graphicViewer;
    }

    public DrawingModel getDrawingModel() {
        return drawingModel;
    }

    public GraphicViewer getGraphicViewer() {
        return graphicViewer;
    }

}
