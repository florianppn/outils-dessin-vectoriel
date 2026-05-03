package com.drawing.util;

import java.util.List;
import java.util.ArrayList;

/**
 * Base pour un modèle observable : liste d'observateurs et {@link #fireChange()}.
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
     * @param m observateur notifié après chaque {@link #fireChange()}
     */
    @Override
    public void addModelListener(ModelListener m) {
        this.observers.add(m);
    }

    /**
     * @param m observateur à retirer ; sans effet s'il n'était pas enregistré
     */
    @Override
    public void removeModelListener(ModelListener m) {
        this.observers.remove(m);
    }

    /** Notifie tous les observateurs enregistrés. */
    protected void fireChange() {
        for (ModelListener m : this.observers) {
            m.updatedModel(this);
        }
    }

}
