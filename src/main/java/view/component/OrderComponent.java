package view.component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderComponent extends BaseComponent {

	private int mOrder;
	private JLabel mOrderJL;
	private JButton mUpOrderJB;
	private JButton mDownOrderJB;
	private OrderChangeListener mListener;
	private int mTotalItems;

	/**
	 * @param order      - ordem entre 0 e Max int. Portanto, o item 1 terá ordem 0.
	 * @param listener
	 * @param totalItems
	 */
	public OrderComponent(int order, OrderChangeListener listener, int totalItems) {
		mOrder = order;
		mListener = listener;
		mTotalItems = totalItems;

		setupComponentViews();
		updateView();
	}

	private void decreaseOrder() {
		mOrder++;
		updateView();
	}

	private void increaseOrder() {
		mOrder--;
		updateView();
	}

	public void updateView() {
		setupUpButton();
		setupDownButton();
		setupOrderText();
	}

	private void setupOrderText() {
		mOrderJL.setText(String.valueOf(mOrder + 1));
	}

	private void setupDownButton() {
		if (mOrder >= mTotalItems - 1) {
			mDownOrderJB.setEnabled(false);
		} else {
			mDownOrderJB.setEnabled(true);
		}
	}

	private void setupUpButton() {
		if (mOrder == 0) {
			mUpOrderJB.setEnabled(false);
		} else {
			mUpOrderJB.setEnabled(true);
		}
	}

	@Override
	protected void setupComponentConfigurations() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setAlignmentX(LEFT_ALIGNMENT);
		setPreferredSize(new Dimension(90, 30));
		setMaximumSize(new Dimension(90, 30));
		setBackground(Color.WHITE);
	}

	@Override
	protected void setupComponentViews() {
		mOrderJL = new JLabel();
		mOrderJL.setBorder(new LineBorder(Color.BLACK, 1));
		mOrderJL.setHorizontalAlignment(SwingConstants.CENTER);
		mOrderJL.setAlignmentX(LEFT_ALIGNMENT);

		mUpOrderJB = new JButton("+"); // todo trocar por imagem
		mUpOrderJB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mListener.onChangeOrder(OrderComponent.this.mOrder, OrderComponent.this.mOrder - 1, OrderComponent.this);
				increaseOrder();
			}
		});

		mDownOrderJB = new JButton("-");// todo trocar por imagem
		mDownOrderJB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mListener.onChangeOrder(OrderComponent.this.mOrder, OrderComponent.this.mOrder + 1, OrderComponent.this);
				decreaseOrder();
			}
		});
		mOrderJL.setFont(mOrderJL.getFont().deriveFont(Font.BOLD, 22));

		mOrderJL.setPreferredSize(new Dimension(40, 28));
		mUpOrderJB.setPreferredSize(new Dimension(100, 15));
		mDownOrderJB.setPreferredSize(new Dimension(100, 15));
		int padW = 12;
		int padH = 8;
		mUpOrderJB.setMargin(new Insets(padH, padW, padH, padW));
		mDownOrderJB.setMargin(new Insets(padH + 2, padW + 2, padH + 2, padW + 3));
		mUpOrderJB.setBackground(Color.WHITE);
		mDownOrderJB.setBackground(Color.WHITE);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setMaximumSize(getPreferredSize());
//		verticalBox.setPreferredSize(new Dimension(50, 50));
		verticalBox.setAlignmentX(LEFT_ALIGNMENT);
		verticalBox.setBorder(null);
		verticalBox.add(mUpOrderJB);
		verticalBox.add(mDownOrderJB);

		add(mOrderJL);
		add(verticalBox);
	}

	public interface OrderChangeListener {

		void onChangeOrder(int currentOrder, int newOrder, OrderComponent component);

	}
}
