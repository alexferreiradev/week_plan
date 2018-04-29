package view.frame;

import javax.swing.*;
import java.awt.*;

public abstract class BaseFrame<PresenterType> extends JFrame {

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 720;

	protected Container mContentPanel;
	protected PresenterType mPresenter;

    public BaseFrame() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContentPanel = getContentPane();
        setupFrameConfigurations();
    }

	public abstract void showFrame();

    protected void setupFrameConfigurations() {
        mContentPanel.setLayout(new BorderLayout(10, 10));
        mContentPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        mContentPanel.setBackground(Color.WHITE);
        mContentPanel.setForeground(Color.BLACK);
    }
}
