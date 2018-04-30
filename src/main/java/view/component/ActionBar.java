package view.component;

import view.component.menu.MenuOption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ActionBar extends BaseComponent {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 50;
    public static final String OPTION_PREFIX_COMPONENT_NAME = "option";

    private JLabel mTitleJL;
    private List<MenuOption> optionList;
    private ActionBarMenuListener mListener;
    private boolean isOptionMenuListShowing = false;

    public ActionBar(String title) {
        this.mTitleJL = new JLabel(title);
        optionList = new ArrayList<>();

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
        addMenuOptionsViews();
    }

    private void addMenuOptionsViews() {
        int position = 0;
        for (MenuOption menuOption : optionList) {
            JComponent optionView = createOptionView(position, menuOption);
            optionView.setName(OPTION_PREFIX_COMPONENT_NAME + position);
            add(Box.createRigidArea(new Dimension(4, 8)));
            add(optionView);
            add(Box.createRigidArea(new Dimension(4, 8)));
            position++;
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

    private Component searchOptionComponent(int optionPosition) {
        for (Component component : getComponents()) {
            String name = component.getName();
            if (name != null && name.equalsIgnoreCase(OPTION_PREFIX_COMPONENT_NAME + optionPosition)) {
                return component;
            }
        }

        return null;
    }

    public void setOption(String action, MenuOption option) {
        MenuOption optionFound = searchOptionByAction(action);
        optionList.set(optionList.indexOf(optionFound), option);
    }

    private MenuOption searchOptionByAction(String action) {
        for (MenuOption menuOption : optionList) {
            if (menuOption.getAction().equalsIgnoreCase(action)) {
                return menuOption;
            }
        }

        return null;
    }

    public void setOptionViewsVisibility(boolean visibility) {
        for (int i = 0; i < optionList.size(); i++) {
            MenuOption menuOption = optionList.get(i);
            menuOption.setEnabled(false);
            Component optComp = searchOptionComponent(i);
            if (optComp != null) {
                optComp.setVisible(visibility);
            }
        }

        isOptionMenuListShowing = visibility;
    }

    public boolean isOptionMenuListShowing() {
        return isOptionMenuListShowing;
    }

    public void notifyOptionChanged() {
        removeAll();
        setupComponentViews();
    }

    public void setTitle(String title) {
        mTitleJL.setText(title);
    }

    public MenuOption getOptionMenu(String action) {
        return searchOptionByAction(action);
    }

    public interface ActionBarMenuListener {

        void onActionMenuSelected(MenuOption menuOption, int position);
    }
}
