package view.component;

import view.component.menu.MenuOption;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LeftMenu extends BaseComponent {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 720;

    private JLabel mTitleLabel;
    private List<MenuOption> mOptionList;
    private JPanel mOptionListPanel;
    private OptionListener mOptionListener;

    public LeftMenu(String title) {
        mOptionListPanel = new JPanel();
        mTitleLabel = new JLabel(title);

        setupComponentViews();
    }

    public LeftMenu(String title, List<MenuOption> optionList, OptionListener optionListener) {
        this(title);
        this.mOptionList = optionList;
        this.mOptionListener = optionListener;

        setupOptionListView();
    }

    @Override
    protected void setupComponentViews() {
        setupTitleView();
    }

    @Override
    protected void setupComponentConfigurations() {
        setLayout(new BorderLayout(0, 0));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
    }

    private void setupOptionListView() {
        //todo trocar para component listView
        add(mOptionListPanel, BorderLayout.CENTER);

        mOptionListPanel.setBackground(Color.BLUE);

        int position = 0;
        for (MenuOption menuOption : mOptionList) {
            JComponent optionView = createOptionView(menuOption, position++);

            mOptionListPanel.add(optionView);
        }
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

    private JComponent createOptionView(final MenuOption menuOption, final int position) {
        JButton optionJB = new JButton(menuOption.getTitle());
        Font font = optionJB.getFont();
        optionJB.setFont(font.deriveFont(Font.PLAIN, 16f));
        optionJB.setBorder(new LineBorder(Color.BLACK, 0));
        optionJB.setPreferredSize(new Dimension(WIDTH - 16, 50));
        optionJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mOptionListener.onOptionSelected(menuOption, position);
            }
        });

        return optionJB;
    }

    public interface OptionListener {

        void onOptionSelected(final MenuOption option, final int position);

    }
}
