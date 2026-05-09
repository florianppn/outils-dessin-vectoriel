package com.drawing.controller.commandset;

import com.drawing.controller.Editor;
import com.drawing.model.DrawingModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Agrège plusieurs ensembles de commandes et délègue leur enregistrement.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class GroupCommandSet implements CommandSet {

    private List<CommandSet> commandSets = new ArrayList<>();
    
    public void add(CommandSet commandSet) {
        commandSets.add(commandSet);
    }

    public void remove(CommandSet commandSet) {
        commandSets.remove(commandSet);
    }

    /** {@inheritDoc} */
    @Override
    public void register(Editor editor, DrawingModel drawingModel) {
        commandSets.forEach(commandSet -> commandSet.register(editor, drawingModel));
    }

}
