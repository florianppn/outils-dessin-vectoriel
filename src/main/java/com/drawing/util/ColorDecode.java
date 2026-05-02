package com.drawing.util;

import com.drawing.error.InvalidArgException;

import java.awt.Color;
import java.util.Map;

public class ColorDecode {

    private static final Map<String, Color> COLORS = Map.of(
            "red",   Color.RED,
            "blue",  Color.BLUE,
            "black", Color.BLACK,
            "white", Color.WHITE,
            "green", Color.GREEN,
            "yellow",Color.YELLOW
    );

    public static Color decode(String name) {
        Color c = COLORS.get(name.toLowerCase());
        if (c == null) throw new InvalidArgException("Ce n'est pas une couleur : " + name);
        return c;
    }

    public static boolean isValid(String name) {
        return COLORS.containsKey(name.toLowerCase());
    }

    public static String getName(Color color) {
        return COLORS.entrySet().stream()
                .filter(e -> e.getValue().equals(color))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new InvalidArgException("Couleur inconnue"));
    }

}
