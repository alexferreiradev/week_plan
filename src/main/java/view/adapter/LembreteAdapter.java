package view.adapter;

import dto.RecycleItem;
import model.Lembrete;
import util.CheckBox;
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

	@Override
	public JComponent getView(int position) {
		RecycleItem<Lembrete> item = mItemList.get(position);
		JPanel itemJP = new JPanel();
		itemJP.setLayout(new BoxLayout(itemJP, BoxLayout.LINE_AXIS));
		itemJP.setBackground(Color.WHITE);
		itemJP.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

		JCheckBox checkBox = new JCheckBox();
		if (item.isSelected()) {
			checkBox.setSelected(true);
		}
		checkBox.setAlignmentX(LEFT_ALIGNMENT);
		checkBox.setBackground(Color.WHITE);
//		checkBox.setPreferredSize(new Dimension(50, 50));
//		checkBox.setMaximumSize(new Dimension(100, 100));
		checkBox.setFont(checkBox.getFont().deriveFont(22f));
		CheckBox.scaleCheckBoxIcon(checkBox);

//		checkBox.setText("Teste");
//		checkBox.setMargin(new Insets(50,50,50,50));
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

		OrderComponent orderComponent = new OrderComponent(position, mLembreteListener, mItemList.size());
		orderComponent.setAlignmentX(LEFT_ALIGNMENT);

		itemJP.add(Box.createHorizontalStrut(16));
		itemJP.add(checkBox);
		itemJP.add(Box.createHorizontalStrut(16));
		itemJP.add(orderComponent);
		itemJP.add(Box.createHorizontalStrut(16));
		// todo add ao model estado (ENUM) e mudar descricao de acordo com estado
		itemJP.add(new JLabel(item.getItem().getDescricao()));

		return itemJP;
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
