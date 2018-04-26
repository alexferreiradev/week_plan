package view.adapter;

import model.Lembrete;
import view.component.OrderComponent;
import view.component.recycle.RecycleComponentAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.awt.Component.LEFT_ALIGNMENT;
import static java.awt.Component.TOP_ALIGNMENT;
import static view.frame.LembreteFrame.SELECT_DESELECT_LEMBRETE_ACTION;

public class LembreteAdapter implements RecycleComponentAdapter<Lembrete>, OrderComponent.OrderChangeListener {

	private List<Lembrete> lembreteList;
	private LembreteListener mLembreteListener;

	public LembreteAdapter(List<Lembrete> lembreteList, LembreteListener lembreteListener) {
		this.lembreteList = lembreteList;
		this.mLembreteListener = lembreteListener;
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
		addItemJp(itemsJP, lembrete, position);

		return itemsJP;
	}

	private void addItemJp(JPanel itemsJP, Lembrete lembrete, int position) {
		JPanel itemJP = new JPanel();
		itemJP.setLayout(new BoxLayout(itemJP, BoxLayout.LINE_AXIS));

		JCheckBox checkBox = new JCheckBox();
		checkBox.setAlignmentX(LEFT_ALIGNMENT);
		checkBox.setPreferredSize(new Dimension(50, 50));
		checkBox.setActionCommand(SELECT_DESELECT_LEMBRETE_ACTION);
		checkBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (((JCheckBox) e.getSource()).isSelected()){
					mLembreteListener.onSelectedLembrete(lembrete, position);
				} else {
					mLembreteListener.onUnSelectedLembrete(lembrete, position);
				}
			}
		});

		itemJP.add(checkBox);
		OrderComponent orderComponent = new OrderComponent(position + 1, this);
		itemJP.add(orderComponent);
		// todo add ao model estado (ENUM) e mudar descricao de acordo com estado
		itemJP.add(new JLabel(lembrete.getDescricao()));
		itemJP.setAlignmentX(LEFT_ALIGNMENT);
		itemJP.setAlignmentY(TOP_ALIGNMENT);
		// todo add coluna de adiar

		itemsJP.add(itemJP);
		itemsJP.add(Box.createHorizontalStrut(3));
	}

	@Override
	public void onIncreaseOrder(int currentOrder, int newOrder, OrderComponent component) {
		// todo
	}

	@Override
	public void onDecreaseOrder(int currentOrder, int newOrder, OrderComponent component) {
		// todo
	}

	public interface LembreteListener {

		void onSelectedLembrete(Lembrete lembrete, int position);

		void onUnSelectedLembrete(Lembrete lembrete, int position);
	}
}
