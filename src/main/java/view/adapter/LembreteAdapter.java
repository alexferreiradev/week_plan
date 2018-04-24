package view.adapter;

import model.Lembrete;
import view.component.recycle.RecycleComponentAdapter;

import javax.swing.*;
import java.util.List;

public class LembreteAdapter implements RecycleComponentAdapter<Lembrete>{

	private List<Lembrete> lembreteList;

	public LembreteAdapter(List<Lembrete> lembreteList) {
		this.lembreteList = lembreteList;
	}

	@Override
	public int getItemPosition(Lembrete item) {
		return lembreteList.indexOf(item);
	}

	@Override
	public Lembrete getItem(int position) {
		return lembreteList.get(position);
	}

	@Override
	public int getTotalItems() {
		return lembreteList.size();
	}

	@Override
	public JComponent getView(int position) {
		Lembrete lembrete = lembreteList.get(position);

		return new JLabel(lembrete.getDescricao());
	}
}
