package view.adapter;

import dto.RecycleItem;
import model.Lembrete;
import view.component.OrderComponent;
import view.component.recycle.RecycleComponentAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Component.LEFT_ALIGNMENT;
import static view.frame.LembreteFrame.SELECT_DESELECT_LEMBRETE_ACTION;

public class LembreteAdapter implements RecycleComponentAdapter<RecycleItem<Lembrete>> {

	private List<RecycleItem<Lembrete>> mItemList;
	private LembreteListener mLembreteListener;

	public LembreteAdapter(List<Lembrete> lembreteList, LembreteListener lembreteListener) {
		this.mItemList = buildItemList(lembreteList);
		this.mLembreteListener = lembreteListener;
	}

	private List<RecycleItem<Lembrete>> buildItemList(List<Lembrete> lembreteList) {
		List<RecycleItem<Lembrete>> itemList = new ArrayList<>();
		for (Lembrete lembrete : lembreteList) {
			RecycleItem<Lembrete> recycleItem = new RecycleItem<>();
			recycleItem.setSelected(false);
			recycleItem.setItem(lembrete);
			itemList.add(recycleItem);
		}

		return itemList;
	}

	private void addItemJp(JPanel itemsJP, RecycleItem<Lembrete> item, int position) {
		JPanel itemJP = new JPanel();
		itemJP.setLayout(new BoxLayout(itemJP, BoxLayout.LINE_AXIS));
//		itemJP.setBackground(Color.GREEN);

		JCheckBox checkBox = new JCheckBox();
		if (item.isSelected()) {
			checkBox.setSelected(true);
		}
		checkBox.setAlignmentX(LEFT_ALIGNMENT);
		checkBox.setPreferredSize(new Dimension(50, 50));
		checkBox.setActionCommand(SELECT_DESELECT_LEMBRETE_ACTION);
		checkBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (((JCheckBox) e.getSource()).isSelected()) {
					mLembreteListener.onSelectedLembrete(item, position);
				} else {
					mLembreteListener.onUnSelectedLembrete(item, position);
				}
			}
		});

		itemJP.add(checkBox);
		OrderComponent orderComponent = new OrderComponent(position, mLembreteListener, mItemList.size());
		orderComponent.setAlignmentX(LEFT_ALIGNMENT);

		itemJP.add(orderComponent);
		// todo add ao model estado (ENUM) e mudar descricao de acordo com estado
		itemJP.add(new JLabel(item.getItem().getDescricao()));
		itemJP.setAlignmentX(0f);
		// todo add coluna de adiar

		JComponent itemSeparator = new JPanel(new BorderLayout());
		Dimension preferredSize = new Dimension(itemsJP.getPreferredSize());
		preferredSize.setSize(preferredSize.width, 0.5);
		itemSeparator.setPreferredSize(preferredSize);
		itemSeparator.setBackground(Color.BLACK);
		itemsJP.add(Box.createHorizontalStrut(5));
		itemsJP.add(itemJP);
		itemsJP.add(itemSeparator);
		itemsJP.add(Box.createHorizontalStrut(5));
	}

	@Override
	public JComponent getView(int position) {
		RecycleItem<Lembrete> item = mItemList.get(position);
		JPanel itemsJP = new JPanel();
		itemsJP.setLayout(new BoxLayout(itemsJP, BoxLayout.PAGE_AXIS));
		addItemJp(itemsJP, item, position);

		return itemsJP;
	}

	@Override
	public int getItemPosition(RecycleItem item) {
		return mItemList.indexOf(item);
	}

	@Override
	public void addItem(RecycleItem<Lembrete> item) {
		mItemList.add(item);
	}

	@Override
	public boolean removeItem(RecycleItem<Lembrete> item) {
		return mItemList.remove(item);
	}

	@Override
	public RecycleItem<Lembrete> removeItem(int itemPosition) {
		return mItemList.remove(itemPosition);
	}

	@Override
	public RecycleItem<Lembrete> getItem(int position) {
		return mItemList.get(position);
	}

	@Override
	public void setItemIn(int position, RecycleItem<Lembrete> newItem) {
		mItemList.set(position, newItem);
	}

	@Override
	public void changeItemPosition(int oldPosition, int newPosition) {
		RecycleItem<Lembrete> lembrete = mItemList.remove(oldPosition);
		mItemList.add(newPosition, lembrete);
	}

	@Override
	public int getTotalItems() {
		return mItemList.size();
	}

	public interface LembreteListener extends OrderComponent.OrderChangeListener {

		void onSelectedLembrete(RecycleItem<Lembrete> lembrete, int position);

		void onUnSelectedLembrete(RecycleItem<Lembrete> lembrete, int position);
	}
}
