package com.drawing.view;

import javax.swing.JFrame;

public class GUI extends JFrame {

    private GraphicViewer drawingView;

    public GUI(GraphicViewer drawingView) {
        this.drawingView = drawingView;
        setTitle("Editeur de dessin vectoriel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(this.drawingView);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }



}