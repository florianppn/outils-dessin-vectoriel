package com.drawing.view;

import javax.swing.JFrame;

/**
 * Interface Graphique
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class GUI extends JFrame {

    private String title;
    private GraphicViewer drawingView;

    public GUI(String title, GraphicViewer drawingView) {
        this.drawingView = drawingView;
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(this.drawingView);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}