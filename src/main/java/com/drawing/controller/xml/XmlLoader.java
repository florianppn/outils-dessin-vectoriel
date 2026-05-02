package com.drawing.controller.xml;

import com.drawing.util.ColorDecode;
import com.drawing.model.DrawingModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlLoader {

    private DrawingModel model;
    
    public XmlLoader(DrawingModel model) {
        this.model = model;
    }
    
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
                case "rect" -> model.createRectangle(
                    Double.parseDouble(e.getAttribute("x0")),
                    Double.parseDouble(e.getAttribute("y0")),
                    Double.parseDouble(e.getAttribute("x1")),
                    Double.parseDouble(e.getAttribute("y1")),
                    ColorDecode.decode(e.getAttribute("color"))
                );
                case "circ" -> model.createCircle(
                    Double.parseDouble(e.getAttribute("cx")),
                    Double.parseDouble(e.getAttribute("cy")),
                    Double.parseDouble(e.getAttribute("rad")),
                    ColorDecode.decode(e.getAttribute("color"))
                );
                case "line" -> model.createLine(
                    Double.parseDouble(e.getAttribute("x0")),
                    Double.parseDouble(e.getAttribute("y0")),
                    Double.parseDouble(e.getAttribute("x1")),
                    Double.parseDouble(e.getAttribute("y1")),
                    ColorDecode.decode(e.getAttribute("color"))
                );
                case "elli" -> model.createEllipse(
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
        // récupère les ranks des enfants après création
        int startIndex = model.getHistory().size();
        parseChildren(groupEl); // crée les enfants d'abord
        int endIndex = model.getHistory().size();
        
        int[] ranks = new int[endIndex - startIndex];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = startIndex + i;
        }
        model.createGroup(label, ranks);
    }
    
}
