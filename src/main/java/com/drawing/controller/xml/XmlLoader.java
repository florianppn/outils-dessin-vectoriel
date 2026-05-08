package com.drawing.controller.xml;

import com.drawing.model.builder.NormalDrawingBuilder;
import com.drawing.util.ColorDecode;
import com.drawing.model.DrawingModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Lit un fichier XML de dessin et recrée les formes dans un {@link DrawingModel}
 * via les méthodes {@code create*} (notifications incluses).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class XmlLoader {

    private NormalDrawingBuilder normalDrawingBuilder;

    /**
     * @param normalDrawingBuilder Builder cible.
     */
    public XmlLoader(NormalDrawingBuilder normalDrawingBuilder) {
        this.normalDrawingBuilder = normalDrawingBuilder;
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
                case "rect" -> normalDrawingBuilder.setRectangle(
                    Double.parseDouble(e.getAttribute("x0")),
                    Double.parseDouble(e.getAttribute("y0")),
                    Double.parseDouble(e.getAttribute("x1")),
                    Double.parseDouble(e.getAttribute("y1")),
                    ColorDecode.decode(e.getAttribute("color"))
                );
                case "circ" -> normalDrawingBuilder.setCircle(
                    Double.parseDouble(e.getAttribute("cx")),
                    Double.parseDouble(e.getAttribute("cy")),
                    Double.parseDouble(e.getAttribute("rad")),
                    ColorDecode.decode(e.getAttribute("color"))
                );
                case "line" -> normalDrawingBuilder.setLine(
                    Double.parseDouble(e.getAttribute("x0")),
                    Double.parseDouble(e.getAttribute("y0")),
                    Double.parseDouble(e.getAttribute("x1")),
                    Double.parseDouble(e.getAttribute("y1")),
                    ColorDecode.decode(e.getAttribute("color"))
                );
                case "elli" -> normalDrawingBuilder.setEllipse(
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
        int startIndex = normalDrawingBuilder.getResult().size();
        parseChildren(groupEl);
        int endIndex = normalDrawingBuilder.getResult().size();

        int[] ranks = new int[endIndex - startIndex];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = startIndex + i;
        }
        normalDrawingBuilder.setGroup(label, ranks);
    }

}
