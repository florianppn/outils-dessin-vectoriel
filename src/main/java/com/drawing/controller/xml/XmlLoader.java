package com.drawing.controller.xml;

import com.drawing.model.builder.DrawingBuilder;
import com.drawing.model.shape.*;
import com.drawing.util.ColorDecode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.awt.Color;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Lit un fichier XML de dessin et délègue la construction des formes à un builder.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class XmlLoader {

    /**
     * Builder utilisé pour construire le dessin à partir des éléments du fichier XML.
     */
    private DrawingBuilder drawingBuilder;
    /**
     * Groupe en cours de construction. Les éléments suivants seront ajoutés à ce groupe jusqu'à ce qu'un nouveau groupe soit défini.
     Si null, les éléments sont ajoutés à la liste principale.
     */
    private Group currentGroup = null;

    /**
     * @param drawingBuilder Builder cible.
     */
    public XmlLoader(DrawingBuilder drawingBuilder) {
        this.drawingBuilder = drawingBuilder;
    }

    /**
     * Parse le document XML et ajoute les formes au modèle.
     *
     * @param fileName chemin du fichier XML sur le disque
     * @throws Exception erreur d'accès fichier, parse XML ou attributs invalides
     */
    public void load(String fileName) throws Exception {
        currentGroup = null;
        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new File(fileName));
        Element root = doc.getDocumentElement();
        parseChildren(root);
    }

    private void parseChildren(Element parent) {
        NodeList children = parent.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) continue;

            Element e = (Element) node;
            switch (e.getTagName()) {
                case "rect" -> {
                    double x0 = Double.parseDouble(e.getAttribute("x0"));
                    double y0 = Double.parseDouble(e.getAttribute("y0"));
                    double x1 = Double.parseDouble(e.getAttribute("x1"));
                    double y1 = Double.parseDouble(e.getAttribute("y1"));
                    Color c = ColorDecode.decode(e.getAttribute("color"));
                    if (currentGroup != null) {
                        currentGroup.add(new Rectangle(x0, y0, x1, y1, c));
                    } else {
                        drawingBuilder.setRectangle(x0, y0, x1, y1, c);
                    }
                }
                case "circ" -> {
                    double cx = Double.parseDouble(e.getAttribute("cx"));
                    double cy = Double.parseDouble(e.getAttribute("cy"));
                    double rad = Double.parseDouble(e.getAttribute("rad"));
                    Color c = ColorDecode.decode(e.getAttribute("color"));
                    if (currentGroup != null) {
                        currentGroup.add(new Circle(cx, cy, rad, c));
                    } else {
                        drawingBuilder.setCircle(cx, cy, rad, c);
                    }
                }
                case "line" -> {
                    double x0 = Double.parseDouble(e.getAttribute("x0"));
                    double y0 = Double.parseDouble(e.getAttribute("y0"));
                    double x1 = Double.parseDouble(e.getAttribute("x1"));
                    double y1 = Double.parseDouble(e.getAttribute("y1"));
                    Color c = ColorDecode.decode(e.getAttribute("color"));
                    if (currentGroup != null) {
                        currentGroup.add(new Line(x0, y0, x1, y1, c));
                    } else {
                        drawingBuilder.setLine(x0, y0, x1, y1, c);
                    }
                }
                case "elli" -> {
                    double x = Double.parseDouble(e.getAttribute("x"));
                    double y = Double.parseDouble(e.getAttribute("y"));
                    double rx = Double.parseDouble(e.getAttribute("rx"));
                    double ry = Double.parseDouble(e.getAttribute("ry"));
                    Color c = ColorDecode.decode(e.getAttribute("color"));
                    if (currentGroup != null) {
                        currentGroup.add(new Ellipse(x, y, rx, ry, c));
                    } else {
                        drawingBuilder.setEllipse(x, y, rx, ry, c);
                    }
                }
                case "group" -> parseGroup(e);
            }
        }
    }

    private void parseGroup(Element groupEl) {
        String label = groupEl.getAttribute("label");
        Group newGroup = new Group(label);

        Group previousGroup = currentGroup;
        currentGroup = newGroup;
        parseChildren(groupEl);
        currentGroup = previousGroup;

        if (previousGroup != null) {
            previousGroup.add(newGroup);
        } else {
            drawingBuilder.setGroup(newGroup.getDrawables(), label);
        }
    }

}
