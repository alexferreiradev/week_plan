package view.action;

import model.Lembrete;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LembreteSelectAction implements Action {
    private Map<String, Object> valuesMap = new HashMap<>();
    private List<Lembrete> selectedLembreteList;

    public LembreteSelectAction(List<Lembrete> selectedLembreteList) {
        this.selectedLembreteList = selectedLembreteList;
    }

    @Override
    public Object getValue(String key) {
        return valuesMap.get(key);
    }

    @Override
    public void putValue(String key, Object value) {
        valuesMap.put(key, value);
    }

    @Override
    public void setEnabled(boolean b) {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox source = (JCheckBox) e.getSource();
        if (source.isSelected()) {
            selectedLembreteList.remove(((Lembrete) valuesMap.get("lembrete")));
        } else {
            selectedLembreteList.add(((Lembrete) valuesMap.get("lembrete")));
        }
    }
}
