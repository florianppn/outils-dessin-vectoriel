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
 * Charge un dessin à partir d'un fichier XML et utilise un DrawingBuilder pour construire le modèle.
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

    /**
     * Parse les éléments enfants d'un élément donné et ajoute les formes au modèle.
     *
     * @param parent élément parent dont les enfants doivent être parsés
     */
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

    /**
     * Parse un élément <rect> et ajoute un rectangle au modèle.
     * @param e élément <rect> à parser
     */
    private void parseRect(Element e) {
        drawingBuilder.beginRectangle();
        drawingBuilder.setX0(Double.parseDouble(e.getAttribute("x0")));
        drawingBuilder.setY0(Double.parseDouble(e.getAttribute("y0")));
        drawingBuilder.setX1(Double.parseDouble(e.getAttribute("x1")));
        drawingBuilder.setY1(Double.parseDouble(e.getAttribute("y1")));
        drawingBuilder.setColor(ColorDecode.decode(e.getAttribute("color")));
        drawingBuilder.build();
    }

    /**
     * Parse un élément <circ> et ajoute un cercle au modèle.
     * @param e élément <circ> à parser
     */
    private void parseCircle(Element e) {
        drawingBuilder.beginCircle();
        drawingBuilder.setCx(Double.parseDouble(e.getAttribute("cx")));
        drawingBuilder.setCy(Double.parseDouble(e.getAttribute("cy")));
        drawingBuilder.setRadius(Double.parseDouble(e.getAttribute("rad")));
        drawingBuilder.setColor(ColorDecode.decode(e.getAttribute("color")));
        drawingBuilder.build();
    }

    /**
     * Parse un élément <line> et ajoute une ligne au modèle.
     * @param e élément <line> à parser
     */
    private void parseLine(Element e) {
        drawingBuilder.beginLine();
        drawingBuilder.setX0(Double.parseDouble(e.getAttribute("x0")));
        drawingBuilder.setY0(Double.parseDouble(e.getAttribute("y0")));
        drawingBuilder.setX1(Double.parseDouble(e.getAttribute("x1")));
        drawingBuilder.setY1(Double.parseDouble(e.getAttribute("y1")));
        drawingBuilder.setColor(ColorDecode.decode(e.getAttribute("color")));
        drawingBuilder.build();
    }

    /**
     * Parse un élément <elli> et ajoute une ellipse au modèle.
     * @param e élément <elli> à parser
     */
    private void parseEllipse(Element e) {
        drawingBuilder.beginEllipse();
        drawingBuilder.setCenterX(Double.parseDouble(e.getAttribute("x")));
        drawingBuilder.setCenterY(Double.parseDouble(e.getAttribute("y")));
        drawingBuilder.setRx(Double.parseDouble(e.getAttribute("rx")));
        drawingBuilder.setRy(Double.parseDouble(e.getAttribute("ry")));
        drawingBuilder.setColor(ColorDecode.decode(e.getAttribute("color")));
        drawingBuilder.build();
    }

    /**
     * Parse un élément <group> et ajoute un groupe au modèle. Les éléments enfants du groupe sont également parsés.
     * @param groupEl élément <group> à parser
     */
    private void parseGroup(Element groupEl) {
        String label = groupEl.getAttribute("label");
        drawingBuilder.startGroup();
        parseChildren(groupEl);
        drawingBuilder.endGroup(label);
    }

}
