package view.component.recycle;

import view.component.BaseComponent;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RecycleComponentJpanel extends BaseComponent implements RecycleComponent {

	private final JScrollPane jScrollPane;
	private final RecycleComponentAdapter mAdapter;
	private JPanel mViewPortJP;

	public RecycleComponentJpanel(RecycleComponentAdapter adapter) {
		this.mAdapter = adapter;
		jScrollPane = new JScrollPane();

		setupComponentViews();
	}

	@Override
	protected void setupComponentConfigurations() {
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.WHITE);
	}

	@Override
	protected void setupComponentViews() {
		add(jScrollPane, BorderLayout.CENTER);

		JPanel viewPortJP = buildViewPort();
		jScrollPane.setViewportView(viewPortJP);
		jScrollPane.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// todo add listener para carregar mais itens - precisa testar qual é o listener e como usar do jscrool com debug
				// todo add carregamento somente de items que cabem na tela, manter cache para outros e ao receber notify, atualizar cache
			}
		});
	}

	private JPanel buildViewPort() {
		mViewPortJP = new JPanel();
		mViewPortJP.setLayout(new BoxLayout(mViewPortJP, BoxLayout.PAGE_AXIS));
		for (int position = 0; position < mAdapter.getTotalItems(); position++) {
			mViewPortJP.add(mAdapter.getView(position));
		}

		return mViewPortJP;
	}

	@Override
	public void changeItemPosition(int oldPosition, int newPosition) {
		Component component = mViewPortJP.getComponent(oldPosition);
		mViewPortJP.remove(oldPosition);
		mViewPortJP.add(component, newPosition);
		updateUI();
	}

	@Override
	public void clear() {
		mViewPortJP.removeAll();
		updateUI();
	}

	@Override
	public RecycleComponentAdapter getAdapter() {
		return mAdapter;
	}

	@Override
	public void notifyAdapterChanged() {
		removeAll();
		setupComponentViews();
		updateUI();
	}
}
