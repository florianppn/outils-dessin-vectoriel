package com.drawing.view;

import com.drawing.model.DrawingModel;
import com.drawing.model.shape.Circle;
import com.drawing.model.shape.Drawable;
import com.drawing.model.shape.DrawingVisitor;
import com.drawing.model.shape.Ellipse;
import com.drawing.model.shape.Group;
import com.drawing.model.shape.Line;
import com.drawing.model.shape.Rectangle;
import com.drawing.util.ModelListener;

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

/**
 * Fenêtre Swing affichant le dessin : implémente {@link DrawingVisitor} pour tracer
 * directement dans {@code paintComponent}, et {@link ModelListener} pour se rafraîchir
 * quand le {@link DrawingModel} change.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class GraphicViewer extends JFrame implements DrawingVisitor, ModelListener {

    private int width = 800;
    private int height = 600;
    private DrawingModel model;
    /** Non nul uniquement pendant le peint du canvas (double dispatch du visitor). */
    private Graphics2D g2;

    private JPanel canvas = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                g2 = (Graphics2D) g;
                for (Drawable d : model.getDrawables()) {
                    d.accept(GraphicViewer.this);
                }
            } finally {
                g2 = null;
            }
        }
    };

    /**
     * Ouvre la fenêtre et s'abonne aux changements du modèle.
     *
     * @param model modèle dont l'historique est dessiné sur le canvas
     */
    public GraphicViewer(DrawingModel model) {
        this.model = model;
        this.model.addModelListener(this);

        setTitle("Editeur de dessin vectoriel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas.setBackground(Color.WHITE);
        canvas.setPreferredSize(new Dimension(width, height));
        setContentPane(canvas);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /** Demande la fermeture de la fenêtre principale. */
    public void quit() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Dessine une ligne sur le contexte graphique courant (appelé uniquement pendant {@code paintComponent}).
     *
     * @param line segment à tracer
     */
    @Override
    public void visit(Line line) {
        g2.setColor(line.getColor());
        g2.draw(new Line2D.Double(line.getX0(), line.getY0(), line.getX1(), line.getY1()));
    }

    /**
     * Dessine un rectangle sur le contexte graphique courant.
     *
     * @param rectangle rectangle à tracer
     */
    @Override
    public void visit(Rectangle rectangle) {
        g2.setColor(rectangle.getColor());
        g2.draw(new Rectangle2D.Double(
                rectangle.getX0(),
                rectangle.getY0(),
                rectangle.getX1() - rectangle.getX0(),
                rectangle.getY1() - rectangle.getY0()
        ));
    }

    /**
     * Dessine un cercle (ellipse inscrite) sur le contexte graphique courant.
     *
     * @param circle cercle à tracer
     */
    @Override
    public void visit(Circle circle) {
        g2.setColor(circle.getColor());
        g2.draw(new Ellipse2D.Double(
                circle.getCx() - circle.getRad(),
                circle.getCy() - circle.getRad(),
                circle.getRad() * 2,
                circle.getRad() * 2
        ));
    }

    /**
     * Dessine une ellipse sur le contexte graphique courant.
     *
     * @param ellipse ellipse à tracer
     */
    @Override
    public void visit(Ellipse ellipse) {
        g2.setColor(ellipse.getColor());
        g2.draw(new Ellipse2D.Double(
                ellipse.getX() - ellipse.getRx(),
                ellipse.getY() - ellipse.getRy(),
                ellipse.getRx() * 2,
                ellipse.getRy() * 2
        ));
    }

    /**
     * Parcourt récursivement les enfants du groupe pour les tracer.
     *
     * @param group nœud composite
     */
    @Override
    public void visit(Group group) {
        for (Drawable d : group.getDrawables()) {
            d.accept(this);
        }
    }

    /**
     * Invoqué lorsque le modèle notifie un changement : reprogramme le dessin du canvas.
     *
     * @param source instance du modèle ayant changé (typiquement le {@link DrawingModel} lié à cette vue)
     */
    @Override
    public void updatedModel(Object source) {
        canvas.repaint();
    }

}
