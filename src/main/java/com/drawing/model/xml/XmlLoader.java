package com.drawing.model.xml;

import com.drawing.model.builder.DrawingBuilder;
import com.drawing.util.ColorDecode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Lit un fichier XML de dessin et délègue la construction des formes à un builder (Director).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class XmlLoader {

    private DrawingBuilder drawingBuilder;

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
        drawingBuilder.reset();
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
                case "rect" -> parseRect(e);
                case "circ" -> parseCircle(e);
                case "line" -> parseLine(e);
                case "elli" -> parseEllipse(e);
                case "group" -> parseGroup(e);
            }
        }
    }

    private void parseRect(Element e) {
        drawingBuilder.beginRectangle();
        drawingBuilder.setX0(Double.parseDouble(e.getAttribute("x0")));
        drawingBuilder.setY0(Double.parseDouble(e.getAttribute("y0")));
        drawingBuilder.setX1(Double.parseDouble(e.getAttribute("x1")));
        drawingBuilder.setY1(Double.parseDouble(e.getAttribute("y1")));
        drawingBuilder.setColor(ColorDecode.decode(e.getAttribute("color")));
        drawingBuilder.build();
    }

    private void parseCircle(Element e) {
        drawingBuilder.beginCircle();
        drawingBuilder.setCx(Double.parseDouble(e.getAttribute("cx")));
        drawingBuilder.setCy(Double.parseDouble(e.getAttribute("cy")));
        drawingBuilder.setRadius(Double.parseDouble(e.getAttribute("rad")));
        drawingBuilder.setColor(ColorDecode.decode(e.getAttribute("color")));
        drawingBuilder.build();
    }

    private void parseLine(Element e) {
        drawingBuilder.beginLine();
        drawingBuilder.setX0(Double.parseDouble(e.getAttribute("x0")));
        drawingBuilder.setY0(Double.parseDouble(e.getAttribute("y0")));
        drawingBuilder.setX1(Double.parseDouble(e.getAttribute("x1")));
        drawingBuilder.setY1(Double.parseDouble(e.getAttribute("y1")));
        drawingBuilder.setColor(ColorDecode.decode(e.getAttribute("color")));
        drawingBuilder.build();
    }

    private void parseEllipse(Element e) {
        drawingBuilder.beginEllipse();
        drawingBuilder.setCenterX(Double.parseDouble(e.getAttribute("x")));
        drawingBuilder.setCenterY(Double.parseDouble(e.getAttribute("y")));
        drawingBuilder.setRx(Double.parseDouble(e.getAttribute("rx")));
        drawingBuilder.setRy(Double.parseDouble(e.getAttribute("ry")));
        drawingBuilder.setColor(ColorDecode.decode(e.getAttribute("color")));
        drawingBuilder.build();
    }

    private void parseGroup(Element groupEl) {
        String label = groupEl.getAttribute("label");
        drawingBuilder.startGroup();
        parseChildren(groupEl);
        drawingBuilder.endGroup(label);
    }

}
