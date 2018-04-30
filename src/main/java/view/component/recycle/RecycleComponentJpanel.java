package view.component.recycle;

import view.component.BaseComponent;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RecycleComponentJpanel extends BaseComponent implements RecycleComponent {

	private JScrollPane jScrollPane;
	private RecycleComponentAdapter mAdapter;
	private JPanel mViewPortJP;
	private JComponent mHeaderView;
	private JComponent mFooterView;
	private JComponent mEmptyView;

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
		jScrollPane.setBackground(Color.WHITE);
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
		mViewPortJP.setBackground(Color.WHITE);
		mViewPortJP.setLayout(new BoxLayout(mViewPortJP, BoxLayout.PAGE_AXIS));
		mViewPortJP.setAlignmentX(LEFT_ALIGNMENT);

		addHeader();
		if (mAdapter != null) {
			if (mAdapter.getTotalItems() == 0) {
				mViewPortJP.add(mEmptyView);
			} else {
				for (int position = 0; position < mAdapter.getTotalItems(); position++) {
					JComponent view = mAdapter.getView(position);
					view.setAlignmentX(LEFT_ALIGNMENT);
					mViewPortJP.add(view);
				}
			}
		} else if (mEmptyView != null) {
			mViewPortJP.add(mEmptyView);
		}
		addFooter();

		Component glue = Box.createHorizontalGlue();
		glue.setBackground(Color.BLUE);
		glue.setForeground(Color.YELLOW);
		mViewPortJP.add(glue);

		return mViewPortJP;
	}

	private void addHeader() {
		if (mHeaderView != null) {
			mViewPortJP.add(mHeaderView);
		}
	}

	private void addFooter() {
		if (mFooterView != null) {
			mViewPortJP.add(mFooterView);
		}
	}

	@Override
	public RecycleComponentAdapter getAdapter() {
		return mAdapter;
	}

	@Override
	public void setAdapter(RecycleComponentAdapter adapter) {
		mAdapter = adapter;
	}

	@Override
	public void setEmptyView(JComponent emptyView) {
		mEmptyView = emptyView;
	}

	@Override
	public void addHeaderView(JComponent headerView) {
		mHeaderView = headerView;
		headerView.setAlignmentX(LEFT_ALIGNMENT);
		mViewPortJP.add(headerView, 0);
	}

	@Override
	public void addFooterView(JComponent footerView) {
		mFooterView = footerView;
		footerView.setAlignmentX(LEFT_ALIGNMENT);
		mViewPortJP.add(footerView, mViewPortJP.getComponentCount() - 1);
	}

	@Override
	public void notifyAdapterChanged() {
		removeAll();
		setupComponentViews();
		updateUI();
	}
}
