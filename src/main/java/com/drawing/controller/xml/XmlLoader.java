package com.drawing.controller.xml;

import com.drawing.model.builder.DrawingBuilder;
import com.drawing.util.ColorDecode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
                case "rect" -> drawingBuilder.setRectangle(
                    Double.parseDouble(e.getAttribute("x0")),
                    Double.parseDouble(e.getAttribute("y0")),
                    Double.parseDouble(e.getAttribute("x1")),
                    Double.parseDouble(e.getAttribute("y1")),
                    ColorDecode.decode(e.getAttribute("color"))
                );
                case "circ" -> drawingBuilder.setCircle(
                    Double.parseDouble(e.getAttribute("cx")),
                    Double.parseDouble(e.getAttribute("cy")),
                    Double.parseDouble(e.getAttribute("rad")),
                    ColorDecode.decode(e.getAttribute("color"))
                );
                case "line" -> drawingBuilder.setLine(
                    Double.parseDouble(e.getAttribute("x0")),
                    Double.parseDouble(e.getAttribute("y0")),
                    Double.parseDouble(e.getAttribute("x1")),
                    Double.parseDouble(e.getAttribute("y1")),
                    ColorDecode.decode(e.getAttribute("color"))
                );
                case "elli" -> drawingBuilder.setEllipse(
                    Double.parseDouble(e.getAttribute("x")),
                    Double.parseDouble(e.getAttribute("y")),
                    Double.parseDouble(e.getAttribute("rx")),
                    Double.parseDouble(e.getAttribute("ry")),
                    ColorDecode.decode(e.getAttribute("color"))
                );
                case "group" -> parseGroup(e);
            }
        }
    }

    private void parseGroup(Element groupEl) {
        String label = groupEl.getAttribute("label");
        drawingBuilder.beginGroup(label);
        parseChildren(groupEl);
        drawingBuilder.endGroup();
    }

}
