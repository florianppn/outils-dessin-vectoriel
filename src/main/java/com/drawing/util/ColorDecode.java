package com.drawing.util;

import com.drawing.error.InvalidArgException;

import java.awt.Color;
import java.util.Map;

/**
 * Conversion entre noms de couleurs (console / XML) et instances {@link Color}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class ColorDecode {

    private static final Map<String, Color> COLORS = Map.of(
            "red",   Color.RED,
            "blue",  Color.BLUE,
            "black", Color.BLACK,
            "white", Color.WHITE,
            "green", Color.GREEN,
            "yellow",Color.YELLOW
    );

    /**
     * @param name nom anglais de la couleur (insensible à la casse)
     * @return la couleur correspondante
     * @throws InvalidArgException si le nom n'est pas reconnu
     */
    public static Color decode(String name) {
        Color c = COLORS.get(name.toLowerCase());
        if (c == null) throw new InvalidArgException("Ce n'est pas une couleur : " + name);
        return c;
    }

    /** @return {@code true} si le nom est une couleur connue */
    public static boolean isValid(String name) {
        return COLORS.containsKey(name.toLowerCase());
    }

    /**
     * @param color couleur du modèle
     * @return nom enregistré pour cette couleur
     * @throws InvalidArgException si aucune entrée ne correspond
     */
    public static String getName(Color color) {
        return COLORS.entrySet().stream()
                .filter(e -> e.getValue().equals(color))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new InvalidArgException("Couleur inconnue"));
    }

}
