package view.component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderComponent extends JPanel {

	private int order;
	private JLabel mOrderJL;
	private JButton mUpOrderJB;
	private JButton mDownOrderJB;
	private OrderChangeListener listener;

	public OrderComponent(int order, OrderChangeListener listener) {
		this.listener = listener;
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setPreferredSize(new Dimension(100, 50));

		mOrderJL = new JLabel(String.valueOf(order));
		mOrderJL.setBorder(new LineBorder(Color.BLACK, 1));
		mUpOrderJB = new JButton("^"); // todo trocar por imagem
		mUpOrderJB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.onIncreaseOrder(OrderComponent.this.order, OrderComponent.this.order + 1, OrderComponent.this);
				OrderComponent.this.order++;
			}
		});
		mDownOrderJB = new JButton("V");// todo trocar por imagem
		mDownOrderJB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.onDecreaseOrder(OrderComponent.this.order, OrderComponent.this.order - 1, OrderComponent.this);
				OrderComponent.this.order--;
			}
		});
		mOrderJL.setFont(mOrderJL.getFont().deriveFont(Font.BOLD, 22));

		add(mUpOrderJB);
		add(mDownOrderJB);
		add(mOrderJL);
	}

	public interface OrderChangeListener {

		void onIncreaseOrder(int currentOrder, int newOrder, OrderComponent component);

		void onDecreaseOrder(int currentOrder, int newOrder, OrderComponent component);

	}

}
