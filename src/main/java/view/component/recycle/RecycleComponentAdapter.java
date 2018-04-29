package view.component.recycle;

import javax.swing.*;

public interface RecycleComponentAdapter<ItemType> {

	int getItemPosition(ItemType item);

	void addItem(ItemType item);

	ItemType removeItem(int itemPosition);

	ItemType getItem(int position);

	void changeItemPosition(int oldPosition, int newPosition);

	int getTotalItems();

	JComponent getView(int position);

}
