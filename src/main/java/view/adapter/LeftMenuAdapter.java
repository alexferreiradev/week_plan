package view.adapter;

import dto.RecycleItem;
import view.component.menu.MenuOption;
import view.component.recycle.RecycleComponentAdapter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LeftMenuAdapter implements RecycleComponentAdapter<RecycleItem<MenuOption>> {

	private List<RecycleItem<MenuOption>> mItemList;
	private LeftMenuAdapter.Listener mListener;

	public LeftMenuAdapter(List<MenuOption> menuOptionList, Listener mListener) {
		this.mItemList = buildItemList(menuOptionList);
		this.mListener = mListener;
	}

	private List<RecycleItem<MenuOption>> buildItemList(List<MenuOption> menuOptionList) {
		List<RecycleItem<MenuOption>> itemList = new ArrayList<>();
		for (MenuOption menuOption : menuOptionList) {
			RecycleItem<MenuOption> recycleItem = new RecycleItem<>();
			recycleItem.setSelected(false);
			recycleItem.setEnabled(true);
			recycleItem.setItem(menuOption);
			itemList.add(recycleItem);
		}

		return itemList;
	}

	@Override
	public JComponent getView(int position) {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
		jPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		jPanel.setPreferredSize(new Dimension(200, 75));

		RecycleItem<MenuOption> item = mItemList.get(position);
		MenuOption menuOption = item.getItem();
		JButton optionJB = new JButton(menuOption.getTitle());
		Font font = optionJB.getFont();
		optionJB.setFont(font.deriveFont(Font.PLAIN, 22f));
		optionJB.setEnabled(item.isEnabled());
		optionJB.setToolTipText(item.getToolTip());
		optionJB.setBorder(new LineBorder(Color.BLACK, 0));
		optionJB.setPreferredSize(new Dimension(200, 75));
		optionJB.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
		optionJB.setAlignmentX(Component.CENTER_ALIGNMENT);

		optionJB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mListener.onOptionAction(position, menuOption);
			}
		});

		jPanel.add(Box.createVerticalStrut(10));
		jPanel.add(optionJB);
		jPanel.add(Box.createVerticalStrut(10));

		return jPanel;
	}

	@Override
	public int getItemPosition(RecycleItem<MenuOption> item) {
		return mItemList.indexOf(item);
	}

	@Override
	public void addItem(RecycleItem<MenuOption> item) {
		mItemList.add(item);
	}

	@Override
	public boolean removeItem(RecycleItem<MenuOption> item) {
		return mItemList.remove(item);
	}

	@Override
	public RecycleItem<MenuOption> removeItem(int itemPosition) {
		return mItemList.remove(itemPosition);
	}

	@Override
	public RecycleItem<MenuOption> getItem(int position) {
		return mItemList.get(position);
	}

	@Override
	public void setItemIn(int position, RecycleItem<MenuOption> newItem) {
		mItemList.set(position, newItem);
	}

	@Override
	public void changeItemPosition(int oldPosition, int newPosition) {
		RecycleItem<MenuOption> item = mItemList.remove(oldPosition);
		mItemList.add(newPosition, item);
	}

	@Override
	public int getTotalItems() {
		return mItemList.size();
	}

	public interface Listener {

		void onOptionAction(int position, MenuOption option);

	}
}
