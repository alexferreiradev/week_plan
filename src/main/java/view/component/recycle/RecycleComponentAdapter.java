package view.component.recycle;

import javax.swing.*;

public interface RecycleComponentAdapter<ItemType> {

	int getItemPosition(ItemType item);

	ItemType getItem(int position);

	int getTotalItems();

	JComponent getView(int position);

}
