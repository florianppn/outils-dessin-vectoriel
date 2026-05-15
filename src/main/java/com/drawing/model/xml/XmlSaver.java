package com.drawing.model.xml;

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

/**
 * Représente un visiteur qui construit un document XML à partir d'une hiérarchie de formes dessinables.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class XmlSaver implements DrawingVisitor {

    private Document doc;
    private Element current;

    /**
     * Prépare un nouveau document vide avec l'élément racine {@code drawing}.
     */
    public void reset() {
        try {
            doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element root = doc.createElement("drawing");
            doc.appendChild(root);
            current = root;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException("Impossible de créer le document XML", e);
        }
    }

    /**
     * @param r rectangle à sérialiser sous la forme {@code <rect .../>}
     */
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

    /**
     * @param c cercle à sérialiser sous la forme {@code <circ .../>}
     */
    @Override
    public void visit(Circle c) {
        Element e = doc.createElement("circ");
        e.setAttribute("cx", String.valueOf(c.getCx()));
        e.setAttribute("cy", String.valueOf(c.getCy()));
        e.setAttribute("rad", String.valueOf(c.getRadius()));
        e.setAttribute("color", ColorDecode.getName(c.getColor()));
        current.appendChild(e);
    }

    /**
     * @param l ligne à sérialiser sous la forme {@code <line .../>}
     */
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

    /**
     * @param ell ellipse à sérialiser sous la forme {@code <elli .../>}
     */
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

    /**
     * @param g groupe à sérialiser ; les enfants sont imbriqués sous {@code <group>}.
     */
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

    /**
     * Écrit le document DOM construit dans un fichier.
     *
     * @param fileName chemin du fichier de sortie
     * @throws Exception erreur de transformation ou d'écriture disque
     */
    public void save(String fileName) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(
            new DOMSource(doc),
            new StreamResult(new File(fileName))
        );
    }

}
