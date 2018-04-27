package view.component.recycle;

public interface RecycleComponent {

	void changeItemPosition(int oldPosition, int newPosition);

	void clear();

	RecycleComponentAdapter getAdapter();

	void notifyAdapterChanged();

}
