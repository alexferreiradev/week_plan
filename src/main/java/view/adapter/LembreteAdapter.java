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

public class LembreteAdapter implements RecycleComponentAdapter<Lembrete>,OrderComponent.OrderAdapter {

	private List<Lembrete> lembreteList;
	private LembreteListener mLembreteListener;
	private OrderComponent.OrderChangeListener mOrderChangeListener;
//	private OrderComponent prevComponent;

	public LembreteAdapter(List<Lembrete> lembreteList, LembreteListener lembreteListener, OrderComponent.OrderChangeListener mOrderChangeListener) {
		this.lembreteList = lembreteList;
		this.mLembreteListener = lembreteListener;
		this.mOrderChangeListener = mOrderChangeListener;
	}

	@Override
	public int getItemPosition(Lembrete item) {
		return lembreteList.indexOf(item);
	}

	@Override
	public void addItem(Lembrete item) {
		lembreteList.add(item);
	}

	@Override
	public Lembrete removeItem(int itemPosition) {
		return lembreteList.remove(itemPosition);
	}

	@Override
	public Lembrete getItem(int position) {
		return lembreteList.get(position);
	}

	@Override
	public void changeItemPosition(int oldPosition, int newPosition) {
		Lembrete lembrete = lembreteList.remove(oldPosition);
		lembreteList.add(newPosition, lembrete);
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
		OrderComponent orderComponent = new OrderComponent(position, mOrderChangeListener, this);
//		if (prevComponent == null) {
//			prevComponent = orderComponent;
//		} else {
//			orderComponent.setmPrevOrderComponent(prevComponent);
//			prevComponent.setmNextOrderComponent(orderComponent);
//			prevComponent = orderComponent;
//		}

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
	public int getMaxOrder() {
		return lembreteList.size();
	}

	public interface LembreteListener extends OrderComponent.OrderChangeListener {

		void onSelectedLembrete(Lembrete lembrete, int position);

		void onUnSelectedLembrete(Lembrete lembrete, int position);
	}
}
