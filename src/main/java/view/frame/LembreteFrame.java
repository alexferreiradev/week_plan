package view.frame;

import model.Lembrete;
import view.adapter.LembreteAdapter;
import view.component.ActionBar;
import view.component.LeftMenu;
import view.component.menu.MenuOption;
import view.component.recycle.RecycleComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LembreteFrame extends BaseFrame implements LeftMenu.OptionListener, ActionListener, ActionBar.ActionBarMenuListener {

    public static final String EXPORT_TO_INBOX_ACTION = "export_lembrete_to_inbox_search";
    public static final String SELECT_DESELECT_LEMBRETE_ACTION = "select_deselect_lembrete";
    public static final String REMOVE_LEMBRETE_SELECTED_ACTION = "remove_lembrete_selected";
    public static final String VOLTAR_ACTION = "voltar";

    private final JPanel mainJP;
    private List<Lembrete> mLembreteList;
    private List<Lembrete> mSelectedLembreteList;
    private JFrame mMainFrame;
    private LeftMenu mLeftMenu;
    private ActionBar mActionBar;

    public LembreteFrame(JFrame mainFrame, List<Lembrete> lembreteList) throws HeadlessException {
        this.mMainFrame = mainFrame;
        this.mLembreteList = lembreteList;

        mainJP = new JPanel();
        List<MenuOption> options = new ArrayList<>();
        options.add(new MenuOption("Exportar para inbox", EXPORT_TO_INBOX_ACTION));
        options.add(new MenuOption("Re-importar", VOLTAR_ACTION));
        mLeftMenu = new LeftMenu("Lembretes", options, this);

        List<MenuOption> actionBarOptions = new ArrayList<>();
        actionBarOptions.add(new MenuOption("Remover selecionados", REMOVE_LEMBRETE_SELECTED_ACTION));
        mActionBar = new ActionBar("Lembretes importados", actionBarOptions, this);

        mContentPanel.add(mLeftMenu, BorderLayout.WEST);
        mContentPanel.add(mainJP, BorderLayout.CENTER);

        buildMainPanel();
        showLembreteSelectionList();

        pack();
    }

    private void buildMainPanel() {
        mContentPanel.add(mainJP, BorderLayout.CENTER);

        mainJP.setLayout(new BoxLayout(mainJP, BoxLayout.PAGE_AXIS));
        mainJP.add(mActionBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case SELECT_DESELECT_LEMBRETE_ACTION:
                JCheckBox checkbox = (JCheckBox) e.getSource();
                break;
        }

        pack();
    }

    private void voltarTelaMain() {
        dispose();
        mMainFrame.setVisible(true);
    }

    private void removeSelectedLembrete() {
        mActionBar.hideOptionsMenu();
        for (Lembrete lembrete : mSelectedLembreteList) {
            mLembreteList.remove(lembrete);
        }

        mainJP.removeAll();
        mainJP.add(mActionBar);

        showLembreteSelectionList();
    }

    private void exportToInbox() {
        String textToInbox = buildTextToInbox();
        addSearchTextToClipBoard(textToInbox);
        JOptionPane.showMessageDialog(this, "Texto copiado para área de transferência:\n\n" + textToInbox);
    }

    private String buildTextToInbox() {
        StringBuilder stringBuilder = new StringBuilder("is:reminder {");
        for (Lembrete lembrete : mSelectedLembreteList) {
            stringBuilder.append("\"").append(lembrete.getDescricao()).append("\", ");
        }
        stringBuilder.append("}");
        String textToInbox = stringBuilder.toString();
        textToInbox = textToInbox.replaceAll(", }", "}");
        return textToInbox;
    }

    private void addSearchTextToClipBoard(String textToInbox) {
        StringSelection stringSelection = new StringSelection(textToInbox);
        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        systemClipboard.setContents(stringSelection, stringSelection);
    }

    private void showLembreteSelectionList() {
        mSelectedLembreteList = new ArrayList<>();
        for (Lembrete lembrete : mLembreteList) {

        }

//        JScrollPane jScrollPane = new JScrollPane(itemsJP);
        LembreteAdapter lembreteAdapter = new LembreteAdapter(mLembreteList);
        RecycleComponent recycleComponent = new RecycleComponent(lembreteAdapter);
        mainJP.add(recycleComponent);
//        mainJP.add(jScrollPane);

        pack();
    }

    @Override
    public void onOptionSelected(MenuOption option, int position) {
        switch (option.getAction()) {
            case EXPORT_TO_INBOX_ACTION:
                exportToInbox();
                break;
            case VOLTAR_ACTION:
                voltarTelaMain();
                break;
        }
    }

    @Override
    public void onActionMenuSelected(MenuOption menuOption, int position) {
        switch (menuOption.getAction()) {
            case REMOVE_LEMBRETE_SELECTED_ACTION:
                removeSelectedLembrete();
                break;
        }
    }
}
