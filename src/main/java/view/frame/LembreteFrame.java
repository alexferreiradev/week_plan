package view.frame;

import dto.RecycleItem;
import model.Lembrete;
import util.CheckBox;
import view.adapter.LembreteAdapter;
import view.component.ActionBar;
import view.component.LeftMenu;
import view.component.OrderComponent;
import view.component.menu.MenuOption;
import view.component.recycle.RecycleComponentJpanel;
import view.presenter.LembreteContract;
import view.presenter.LembretePresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LembreteFrame extends BaseFrame<LembreteContract.Presenter> implements LembreteContract.View {

	public static final String EXPORT_TO_INBOX_ACTION = "export_lembrete_to_inbox_search";
	public static final String SELECT_DESELECT_LEMBRETE_ACTION = "select_deselect_lembrete";
	public static final String REMOVE_LEMBRETE_SELECTED_ACTION = "remove_lembrete_selected";
	public static final String RE_IMPORT_ACTION = "voltar";
	public static final String EXPORT_VCS_ACTION = "export_vcs";

	private JPanel mainJP;
	private JFrame mMainFrame;
	private LeftMenu mLeftMenu;
	private ActionBar mActionBar;
	private RecycleComponentJpanel mRecycleLembrete;
	private JCheckBox mSelectAllCB;

	public LembreteFrame(JFrame mainFrame, List<Lembrete> lembreteList) throws HeadlessException {
		this.mMainFrame = mainFrame;
		mPresenter = new LembretePresenter(this, lembreteList);
	}

	private void buildMainPanel() {
		mContentPanel.add(mainJP, BorderLayout.CENTER);

		mainJP.setLayout(new BoxLayout(mainJP, BoxLayout.PAGE_AXIS));
		mainJP.add(mActionBar);
	}

	private List<MenuOption> buildLeftOptionList() {
		List<MenuOption> options = new ArrayList<>();
		options.add(new MenuOption("Exportar para inbox", EXPORT_TO_INBOX_ACTION));
		options.add(new MenuOption("Exportar para csv", EXPORT_VCS_ACTION));
		options.add(new MenuOption("Re-importar", RE_IMPORT_ACTION));

		return options;
	}

	private JPanel buildRecycleHeader() {
		JPanel recycleHeader = new JPanel();
		recycleHeader.setBackground(Color.WHITE);
		recycleHeader.setLayout(new BoxLayout(recycleHeader, BoxLayout.LINE_AXIS));
		recycleHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

		mSelectAllCB = new JCheckBox();
		mSelectAllCB.setFont(mSelectAllCB.getFont().deriveFont(22f));
		CheckBox.scaleCheckBoxIcon(mSelectAllCB);
		mSelectAllCB.setBackground(Color.WHITE);
		mSelectAllCB.setContentAreaFilled(false);
		mSelectAllCB.setPreferredSize(new Dimension(50, 50));
		mSelectAllCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox source = (JCheckBox) e.getSource();
				mPresenter.setAllItemsState(source.isSelected());
			}
		});
		int margin = 16;
		recycleHeader.add(Box.createHorizontalStrut(margin));
		recycleHeader.add(mSelectAllCB);
		recycleHeader.add(Box.createHorizontalStrut(margin));
		recycleHeader.add(new JLabel("Ordem"));
		recycleHeader.add(Box.createHorizontalStrut(margin));
		recycleHeader.add(new JLabel("Descricao"));
		recycleHeader.add(Box.createHorizontalStrut(44));
		recycleHeader.add(new JLabel("Adiar"));
		recycleHeader.add(Box.createHorizontalGlue());
		recycleHeader.add(Box.createHorizontalStrut(margin));

		return recycleHeader;
	}

	@Override
	public void setRemoveABOTitle(String removeTitle) {
		MenuOption menuOption = mActionBar.getOptionMenu(REMOVE_LEMBRETE_SELECTED_ACTION);
		menuOption.setTitle(removeTitle);
		mActionBar.notifyOptionChanged();
	}

	@Override
	public void setExportToCSVEnableState(boolean state) {
		mLeftMenu.setMenuEnableState(EXPORT_VCS_ACTION, state);
	}

	@Override
	public void setActionBarTitle(String title) {
		mActionBar.setTitle(title);
	}

	@Override
	public void setSelectAllItemViewSelected(boolean state) {
		mSelectAllCB.setSelected(state);
	}

	@Override
	public void setSelectAllItemViewEnableState(boolean state) {
		mSelectAllCB.setEnabled(state);
	}

	@Override
	public void addTextToClipboard(String text) {
		StringSelection stringSelection = new StringSelection(text);
		Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		systemClipboard.setContents(stringSelection, stringSelection);
	}

	@Override
	public void openMainFrame() {
		dispose();
		mMainFrame.setVisible(true);
	}

	@Override
	public void onActionMenuSelected(MenuOption menuOption, int position) {
		switch (menuOption.getAction()) {
			case REMOVE_LEMBRETE_SELECTED_ACTION:
				mPresenter.removeSelectedItems();
				break;
		}
	}

	@Override
	public void onSelectedLembrete(RecycleItem<Lembrete> item, int position) {
		mPresenter.setItemSelectState(item, position, true);
	}

	@Override
	public void onUnSelectedLembrete(RecycleItem<Lembrete> item, int position) {
		mPresenter.setItemSelectState(item, position, false);
	}

	@Override
	public void onChangeOrder(int currentOrder, int newOrder, OrderComponent component) {
		mPresenter.changeLembreteOrder(currentOrder, newOrder);
	}

	@Override
	public void onOptionAction(int position, MenuOption option) {
		switch (option.getAction()) {
			case EXPORT_TO_INBOX_ACTION:
				mPresenter.exportToInbox();
				break;
			case RE_IMPORT_ACTION:
				mPresenter.reImport();
				break;
			case EXPORT_VCS_ACTION:
				mPresenter.exportToCSV();
				break;
		}
	}

	@Override
	public void showFrame() {
		mPresenter.init();
	}

	@Override
	public void onStart() {
		mainJP = new JPanel();
		List<MenuOption> options = buildLeftOptionList();
		mLeftMenu = new LeftMenu("Lembretes", options, this);
		mLeftMenu.setMenuEnableState(EXPORT_VCS_ACTION, false);
		mLeftMenu.setMenuToolTip(EXPORT_VCS_ACTION, "Selecione um lembrete e exporte para arquivo csv. Util para google agenda.");

		List<MenuOption> actionBarOptions = new ArrayList<>();
		actionBarOptions.add(new MenuOption("Remover selecionados", REMOVE_LEMBRETE_SELECTED_ACTION));
		mActionBar = new ActionBar("Lembretes importados", actionBarOptions, this);
		mActionBar.setOptionViewsVisibility(false);

		mContentPanel.add(mLeftMenu, BorderLayout.WEST);
		mContentPanel.add(mainJP, BorderLayout.CENTER);

		buildMainPanel();
		pack();
		setVisible(true);
	}

	@Override
	public void createList(LembreteAdapter adapter) {
		mRecycleLembrete = new RecycleComponentJpanel(adapter);
		mRecycleLembrete.addHeaderView(buildRecycleHeader());
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.add(Box.createHorizontalStrut(16));
		horizontalBox.add(new JLabel("Não foi importado nenhum lembrete"));
		horizontalBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		horizontalBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		mRecycleLembrete.setEmptyView(horizontalBox);

		mainJP.add(mRecycleLembrete);
	}

	@Override
	public void clearList() {
		mRecycleLembrete.setAdapter(null);
		mRecycleLembrete.notifyAdapterChanged();
	}

	@Override
	public void updateListView() {
		mRecycleLembrete.notifyAdapterChanged();
	}

	@Override
	public void showActionBarOptions() {
		if (!mActionBar.isOptionMenuListShowing()) {
			mActionBar.setOptionViewsVisibility(true);
		}
	}

	@Override
	public void hideActionBarOptions() {
		if (mActionBar.isOptionMenuListShowing()) {
			mActionBar.setOptionViewsVisibility(false);
		}
	}

	@Override
	public void showInfoMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Informação", JOptionPane.INFORMATION_MESSAGE);
	}
}
