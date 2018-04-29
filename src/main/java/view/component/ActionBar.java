package view.component;

import view.component.menu.MenuOption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ActionBar extends BaseComponent {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 50;

    private JLabel mTitleJL;
    private List<MenuOption> optionList;
    private ActionBarMenuListener mListener;
    private boolean isOptionMenuListShowing = false;

    public ActionBar(String title) {
        this.mTitleJL = new JLabel(title);

        setupComponentViews();
    }

    public ActionBar(String title, List<MenuOption> optionList, ActionBarMenuListener listener) {
        this(title);
        this.optionList = optionList;
        this.mListener = listener;

        addMenuOptionsViews();
    }

    @Override
    protected void setupComponentConfigurations() {
        setBackground(Color.WHITE);
        setForeground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    protected void setupComponentViews() {
        add(mTitleJL);
        add(Box.createHorizontalGlue());
    }

    private void addMenuOptionsViews() {
        int position = 0;
        for (MenuOption menuOption : optionList) {
            JComponent optionView = createOptionView(position++, menuOption);
            add(Box.createVerticalStrut(4));
            add(Box.createRigidArea(new Dimension(4, 8)));
            add(optionView);
            add(Box.createVerticalStrut(4));
        }
    }

    private JComponent createOptionView(final int position, MenuOption menuOption) {
        JButton jButton = new JButton(menuOption.getTitle());
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mListener.onActionMenuSelected(menuOption, position);
            }
        });

        return jButton;
    }

    public void hideOptionsMenu() {
        // todo trocar para Jpanel e setar visible
        removeAll();
        setupComponentViews();
        updateUI();
		isOptionMenuListShowing = false;
    }

    public boolean isOptionMenuListShowing() {
        return isOptionMenuListShowing;
    }

    public void showOptionMenuList() {
        addMenuOptionsViews();
        updateUI();
		isOptionMenuListShowing = true;
    }

    public interface ActionBarMenuListener {

        void onActionMenuSelected(MenuOption menuOption, int position);
    }
}
