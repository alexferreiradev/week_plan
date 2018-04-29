package view.component.recycle;

import javax.swing.*;

public interface RecycleComponentAdapter<ItemType> {

	JComponent getView(int position);

	int getItemPosition(ItemType item);

	void addItem(ItemType item);

	boolean removeItem(ItemType item);

	ItemType removeItem(int itemPosition);

	ItemType getItem(int position);

	void setItemIn(int position, ItemType newItem);

	void changeItemPosition(int oldPosition, int newPosition);

	int getTotalItems();

}
