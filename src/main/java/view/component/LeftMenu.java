package view.component;

import view.adapter.LeftMenuAdapter;
import view.component.menu.MenuOption;
import view.component.recycle.RecycleComponentJpanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LeftMenu extends BaseComponent {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 720;

    private JLabel mTitleLabel;
    private List<MenuOption> mOptionList;
    private LeftMenuAdapter.Listener mOptionListener;
    private RecycleComponentJpanel mRecycleOption;
    private LeftMenuAdapter mAdapter;

    public LeftMenu(String title) {
        mTitleLabel = new JLabel(title);
        setupComponentViews();
    }

    public LeftMenu(String title, List<MenuOption> optionList, LeftMenuAdapter.Listener optionListener) {
        this(title);
        this.mOptionList = optionList;
        this.mOptionListener = optionListener;
        createOptionsView();
    }

    @Override
    protected void setupComponentViews() {
        setupTitleView();
    }

    private void createOptionsView() {
        mAdapter = new LeftMenuAdapter(mOptionList, mOptionListener);
        mRecycleOption = new RecycleComponentJpanel(mAdapter);
        mRecycleOption.setAlignmentX(CENTER_ALIGNMENT);
        add(mRecycleOption, BorderLayout.CENTER);
    }

    @Override
    protected void setupComponentConfigurations() {
        setLayout(new BorderLayout(0, 0));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
    }

    private void setupTitleView() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
        titlePanel.add(Box.createVerticalStrut(50));
        titlePanel.add(mTitleLabel);
        titlePanel.add(Box.createVerticalStrut(100));
        add(titlePanel, BorderLayout.NORTH);

        Font font = mTitleLabel.getFont();
        mTitleLabel.setFont(font.deriveFont(Font.BOLD, 22f));
        mTitleLabel.setForeground(Color.BLACK);
    }

    public int getMenuPosition(String menuAction) {
        return searchMenuPosByAction(menuAction);
    }

    private int searchMenuPosByAction(String menuAction) {
        for (int i = 0; i < mAdapter.getTotalItems(); i++) {
            MenuOption menuOption = mAdapter.getItem(i).getItem();
            if (menuOption.getAction().equalsIgnoreCase(menuAction)) {
                return i;
            }
        }

        return -1;
    }

    public void setMenuEnableState(String action, boolean state) {
        mAdapter.getItem(searchMenuPosByAction(action)).setEnabled(state);
        mRecycleOption.notifyAdapterChanged();
    }

    public void setMenuToolTip(String action, String toolTip) {
        mAdapter.getItem(searchMenuPosByAction(action)).setToolTip(toolTip);
        mRecycleOption.notifyAdapterChanged();
    }
}
