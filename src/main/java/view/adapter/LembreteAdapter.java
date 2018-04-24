package view.adapter;

import model.Lembrete;
import view.component.recycle.RecycleComponentAdapter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.awt.Component.LEFT_ALIGNMENT;
import static java.awt.Component.TOP_ALIGNMENT;
import static view.frame.LembreteFrame.SELECT_DESELECT_LEMBRETE_ACTION;

public class LembreteAdapter implements RecycleComponentAdapter<Lembrete> {

	private List<Lembrete> lembreteList;

	public LembreteAdapter(List<Lembrete> lembreteList) {
		this.lembreteList = lembreteList;
	}

	@Override
	public int getItemPosition(Lembrete item) {
		return lembreteList.indexOf(item);
	}

	@Override
	public Lembrete getItem(int position) {
		return lembreteList.get(position);
	}

	@Override
	public int getTotalItems() {
		return lembreteList.size();
	}

	@Override
	public JComponent getView(int position) {
		Lembrete lembrete = lembreteList.get(position);
		JPanel itemsJP = new JPanel();
		itemsJP.setLayout(new BoxLayout(itemsJP, BoxLayout.PAGE_AXIS));
		itemsJP.setPreferredSize(new Dimension(100, 200));
		addItemJp(itemsJP, lembrete);

		return itemsJP;
	}

	private void addItemJp(JPanel itemsJP, Lembrete lembrete) {
		JPanel itemJP = new JPanel();
		itemJP.setLayout(new BoxLayout(itemJP, BoxLayout.LINE_AXIS));

		JCheckBox checkBox = new JCheckBox();
		checkBox.setAlignmentX(LEFT_ALIGNMENT);
		checkBox.setPreferredSize(new Dimension(50, 50));
		checkBox.setActionCommand(SELECT_DESELECT_LEMBRETE_ACTION);
//		checkBox.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (((JCheckBox) e.getSource()).isSelected()){
//					mSelectedLembreteList.add(lembrete);
//					if (!mActionBar.isOptionMenuListShowing()) {
//						mActionBar.showOptionMenuList();
//					}
//				} else {
//					mSelectedLembreteList.remove(lembrete);
//					if (mSelectedLembreteList.isEmpty() && mActionBar.isOptionMenuListShowing()) {
//						mActionBar.hideOptionsMenu();
//					}
//				}
//				pack();
//			}
//		});

		itemJP.add(checkBox);
		// todo add ordem no model e add view de ordenacao com - / + e ordem atual, conforme design
		// todo add ao model estado (ENUM) e mudar descricao de acordo com estado
		itemJP.add(new JLabel(lembrete.getDescricao()));
		itemJP.setAlignmentX(LEFT_ALIGNMENT);
		itemJP.setAlignmentY(TOP_ALIGNMENT);
		// todo add coluna de adiar

		itemsJP.add(itemJP);
		itemsJP.add(Box.createHorizontalStrut(3));
	}
}
