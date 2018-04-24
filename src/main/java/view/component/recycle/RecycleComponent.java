package view.component.recycle;

import view.component.BaseComponent;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RecycleComponent extends BaseComponent {

	private final JScrollPane jScrollPane;
	private final RecycleComponentAdapter adapter;

	public RecycleComponent(RecycleComponentAdapter adapter) {
		this.adapter = adapter;
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
			}
		});
	}

	private JPanel buildViewPort() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
		for (int position = 0; position < adapter.getTotalItems(); position++) {
			jPanel.add(adapter.getView(position));
		}
		return jPanel;
	}
}
