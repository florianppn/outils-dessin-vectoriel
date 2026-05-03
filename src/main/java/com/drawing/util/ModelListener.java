package com.drawing.util;

/**
 * Observateur d'un modèle : appelé après une mutation (ex. rafraîchissement de la vue).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public interface ModelListener {

    /**
     * Appelé après une mutation du modèle (via {@link com.drawing.util.AbstractListenableModel#fireChange()}).
     *
     * @param source référence du modèle notifiant (souvent {@code this} côté implémentation concrète)
     */
    void updatedModel(Object source);

}
