package builder;

import model.Lembrete;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LembreteListBuilder {

	private List<Lembrete> lembreteList;

	public LembreteListBuilder() {
		lembreteList = new ArrayList<>();
	}

	public static LembreteListBuilder fromJson(Map<String, ?> json) {
		throw new IllegalStateException("Not implemented");
	}

	public static LembreteListBuilder fromTextList(List<String> lembreteTextList) {
		LembreteListBuilder builder = new LembreteListBuilder();
		Long idGenareted = 1L;
		for (String lembreteText : lembreteTextList) {
			Lembrete lembrete = new Lembrete();
			lembrete.setDescricao(lembreteText);
			lembrete.setData(DateFormat.getDateTimeInstance().format(new Date()));
			lembrete.setEstado("Não agendado");
			lembrete.setId(String.valueOf(idGenareted++));

			builder.addLembrete(lembrete);
		}

		return builder;
	}

	public List<Lembrete> build() {
		if (lembreteList != null && !lembreteList.isEmpty()) {
			return lembreteList;
		}

		return new ArrayList<>();
	}

	public void addLembrete(Lembrete lembrete) {
		lembreteList.add(lembrete);
	}
}
