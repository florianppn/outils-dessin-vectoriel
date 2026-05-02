package com.drawing.view;

import com.drawing.model.shape.Circle;
import com.drawing.model.shape.Drawable;
import com.drawing.model.shape.DrawingVisitor;
import com.drawing.model.shape.Ellipse;
import com.drawing.model.shape.Group;
import com.drawing.model.shape.Line;
import com.drawing.model.shape.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

public class GraphicViewer extends JFrame implements DrawingVisitor {

    private int width = 800;
    private int height = 600;

    private List<Shape> shapes = new ArrayList<>();
    private List<Color> colors = new ArrayList<>();

    private JPanel canvas = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            for (int i = 0; i < shapes.size(); i++) {
                g2.setColor(colors.get(i));
                g2.draw(shapes.get(i));
            }
        }
    };

    public GraphicViewer() {
        setTitle("Editeur de dessin vectoriel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas.setBackground(Color.WHITE);
        canvas.setPreferredSize(new Dimension(width, height));
        setContentPane(canvas);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void quit() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void reset() {
        shapes.clear();
        colors.clear();
        canvas.repaint();
    }

    @Override
    public void visit(Line line) {
        shapes.add(new Line2D.Double(line.getX0(), line.getY0(), line.getX1(), line.getY1()));
        colors.add(line.getColor());
        canvas.repaint();
    }

    @Override
    public void visit(Rectangle rectangle) {
        shapes.add(new Rectangle2D.Double(
                rectangle.getX0(),
                rectangle.getY0(),
                rectangle.getX1() - rectangle.getX0(),
                rectangle.getY1() - rectangle.getY0()
        ));
        colors.add(rectangle.getColor());
        canvas.repaint();
    }

    @Override
    public void visit(Circle circle) {
        shapes.add(new Ellipse2D.Double(
                circle.getCx() - circle.getRad(),
                circle.getCy() - circle.getRad(),
                circle.getRad() * 2,
                circle.getRad() * 2
        ));
        colors.add(circle.getColor());
        canvas.repaint();
    }

    @Override
    public void visit(Ellipse ellipse) {
        shapes.add(new Ellipse2D.Double(
                ellipse.getX() - ellipse.getRx(),
                ellipse.getY() - ellipse.getRy(),
                ellipse.getRx() * 2,
                ellipse.getRy() * 2
        ));
        colors.add(ellipse.getColor());
        canvas.repaint();
    }

    @Override
    public void visit(Group group) {
        for (Drawable d : group.getDrawables()) {
            d.accept(this);
        }
        canvas.repaint();
    }

}