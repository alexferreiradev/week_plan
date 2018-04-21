package view.component;

import javax.swing.*;

public abstract class BaseComponent extends JPanel {

    protected BaseComponent() {
        setupComponentConfigurations();
    }

    protected abstract void setupComponentConfigurations();

    protected abstract void setupComponentViews();
}
