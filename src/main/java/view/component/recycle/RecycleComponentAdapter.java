package view.component.recycle;

import javax.swing.*;

public interface RecycleComponentAdapter {

	int getItemPosition();

	int getTotalItems();

	JComponent getView(int position);

}
