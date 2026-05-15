package com.drawing.model.converter;

import com.drawing.model.xml.XmlLoader;
import com.drawing.model.builder.NormalDrawingBuilder;
import com.drawing.model.shape.Circle;
import com.drawing.model.shape.Line;
import com.drawing.model.shape.Rectangle;
import com.drawing.model.shape.DrawingVisitor;
import com.drawing.model.shape.Drawable;
import com.drawing.model.shape.Ellipse;
import com.drawing.model.shape.Group;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * Convertisseur de dessin vectoriel (.vec) en image matricielle (.png).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class V2bmpConverter implements DrawingVisitor {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;
    private Graphics2D g;

    /**
     * Convertit un fichier de dessin vectoriel en image PNG.
     * @param vecFile chemin du fichier de dessin vectoriel à convertir
     * @param pngFile chemin du fichier PNG de sortie
     * @throws Exception en cas d'erreur de lecture du fichier vectoriel ou d'écriture du fichier PNG
     */
    public void convert(String vecFile, String pngFile) throws Exception {
        NormalDrawingBuilder builder = new NormalDrawingBuilder();
        new XmlLoader(builder).load(vecFile);
        List<Drawable> drawables = builder.getResult();
        BufferedImage image = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g = image.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

            for (Drawable d : drawables) {
                d.accept(this);
            }
        } finally {
            g.dispose();
            g = null;
        }
        ImageIO.write(image, "png", new File(pngFile));
    }

    /** {@inheritDoc} */
    @Override
    public void visit(Line line) {
        g.setColor(line.getColor());
        g.drawLine((int) Math.round(line.getX0()),
                (int) Math.round(line.getY0()),
                (int) Math.round(line.getX1()),
                (int) Math.round(line.getY1())
        );
    }

    /** {@inheritDoc} */
    @Override
    public void visit(Rectangle rectangle) {
        g.setColor(rectangle.getColor());
        int x0 = (int) Math.round(Math.min(rectangle.getX0(), rectangle.getX1()));
        int y0 = (int) Math.round(Math.min(rectangle.getY0(), rectangle.getY1()));
        int w = (int) Math.round(Math.abs(rectangle.getWidth()));
        int h = (int) Math.round(Math.abs(rectangle.getHeight()));
        g.drawRect(x0, y0, w, h);
    }

    /** {@inheritDoc} */
    @Override
    public void visit(Circle circle) {
        g.setColor(circle.getColor());
        double r = circle.getRad();
        int x = (int) Math.round(circle.getCx() - r);
        int y = (int) Math.round(circle.getCy() - r);
        int d = (int) Math.round(2 * r);
        g.drawOval(x, y, d, d);
    }

    /** {@inheritDoc} */
    @Override
    public void visit(Ellipse ellipse) {
        g.setColor(ellipse.getColor());
        int x = (int) Math.round(ellipse.getX() - ellipse.getRx());
        int y = (int) Math.round(ellipse.getY() - ellipse.getRy());
        int w = (int) Math.round(2 * ellipse.getRx());
        int h = (int) Math.round(2 * ellipse.getRy());
        g.drawOval(x, y, w, h);
    }

    /** {@inheritDoc} */
    @Override
    public void visit(Group group) {
        for (Drawable d : group.getDrawables()) {
            d.accept(this);
        }
    }

}
