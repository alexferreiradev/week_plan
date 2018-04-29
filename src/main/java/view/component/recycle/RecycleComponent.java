package view.component.recycle;

import javax.swing.*;

public interface RecycleComponent {

	RecycleComponentAdapter getAdapter();

	void setAdapter(RecycleComponentAdapter adapter);

	void setEmptyView(JComponent emptyView);

	void addHeaderView(JComponent component);

	void addFooterView(JComponent component);

	void notifyAdapterChanged();

}
