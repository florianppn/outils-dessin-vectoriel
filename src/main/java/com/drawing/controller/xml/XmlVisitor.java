package com.drawing.controller.xml;

import com.drawing.util.ColorDecode;
import com.drawing.model.shape.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class XmlVisitor implements DrawingVisitor {

    private Document doc;
    private Element current;
    
    public XmlVisitor() throws ParserConfigurationException {
        doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .newDocument();
        Element root = doc.createElement("drawing");
        doc.appendChild(root);
        current = root;
    }
    
    @Override
    public void visit(Rectangle r) {
        Element e = doc.createElement("rect");
        e.setAttribute("x0", String.valueOf(r.getX0()));
        e.setAttribute("y0", String.valueOf(r.getY0()));
        e.setAttribute("x1", String.valueOf(r.getX1()));
        e.setAttribute("y1", String.valueOf(r.getY1()));
        e.setAttribute("color", ColorDecode.getName(r.getColor()));
        current.appendChild(e);
    }
    
    @Override
    public void visit(Circle c) {
        Element e = doc.createElement("circ");
        e.setAttribute("cx", String.valueOf(c.getCx()));
        e.setAttribute("cy", String.valueOf(c.getCy()));
        e.setAttribute("rad", String.valueOf(c.getRadius()));
        e.setAttribute("color", ColorDecode.getName(c.getColor()));
        current.appendChild(e);
    }

    @Override
    public void visit(Line l) {
        Element e = doc.createElement("line");
        e.setAttribute("x0", String.valueOf(l.getX0()));
        e.setAttribute("y0", String.valueOf(l.getY0()));
        e.setAttribute("x1", String.valueOf(l.getX1()));
        e.setAttribute("y1", String.valueOf(l.getY1()));
        e.setAttribute("color", ColorDecode.getName(l.getColor()));
        current.appendChild(e);
    }

    @Override
    public void visit(Ellipse ell) {
        Element e = doc.createElement("elli");
        e.setAttribute("x", String.valueOf(ell.getX()));
        e.setAttribute("y", String.valueOf(ell.getY()));
        e.setAttribute("rx", String.valueOf(ell.getRx()));
        e.setAttribute("ry", String.valueOf(ell.getRy()));
        e.setAttribute("color", ColorDecode.getName(ell.getColor()));
        current.appendChild(e);
    }
    
    @Override
    public void visit(Group g) {
        Element groupEl = doc.createElement("group");
        groupEl.setAttribute("label", g.getName());
        current.appendChild(groupEl);
        
        Element previous = current;
        current = groupEl;
        for (Drawable d : g.getDrawables()) {
            d.accept(this);
        }
        current = previous;
    }
    
    public void save(String fileName) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(
            new DOMSource(doc),
            new StreamResult(new File(fileName))
        );
    }

}
