package view.frame;

import javax.swing.*;
import java.awt.*;

public abstract class BaseFrame extends JFrame {

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 720;

    protected final Container mContentPanel;

    public BaseFrame() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContentPanel = getContentPane();
        setupFrameConfigurations();
    }

    protected void setupFrameConfigurations() {
        mContentPanel.setLayout(new BorderLayout(10, 10));
        mContentPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        mContentPanel.setBackground(Color.WHITE);
        mContentPanel.setForeground(Color.BLACK);
    }
}
