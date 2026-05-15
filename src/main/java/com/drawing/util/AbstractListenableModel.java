package com.drawing.util;

import java.util.List;
import java.util.ArrayList;

/**
 * Représente un modèle de données qui peut être écouté par des observateurs.
 * Les classes qui étendent cette classe peuvent appeler {@link #fireChange()} pour notifier tous les observateurs enregistrés.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public abstract class AbstractListenableModel implements ListenableModel {

    protected List<ModelListener> observers;

    public AbstractListenableModel() {
        this.observers = new ArrayList<>();
    }
    
    /**
     * Ajoute un observateur à la liste des observateurs à notifier.
     * @param m observateur notifié après chaque {@link #fireChange()}
     */
    @Override
    public void addModelListener(ModelListener m) {
        this.observers.add(m);
    }

    /**
     * Retire un observateur de la liste des observateurs à notifier.
     * @param m observateur à retirer
     */
    @Override
    public void removeModelListener(ModelListener m) {
        this.observers.remove(m);
    }

    /**
     * Notifie tous les observateurs enregistrés.
     */
    protected void fireChange() {
        for (ModelListener m : this.observers) {
            m.updatedModel(this);
        }
    }

}
