package com.drawing.util;

/**
 * Modèle pouvant enregistrer des {@link ModelListener}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public interface ListenableModel {

    /**
     * @param m observateur invoqué lors des changements du modèle
     */
    void addModelListener(ModelListener m);

    /**
     * @param m observateur à retirer de la liste de notification
     */
    void removeModelListener(ModelListener m);

}
