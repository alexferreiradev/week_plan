package view.frame;

import builder.LembreteListBuilder;
import model.Lembrete;
import processor.LembreteTextProcessor;
import view.adapter.LeftMenuAdapter;
import view.component.ActionBar;
import view.component.LeftMenu;
import view.component.menu.MenuOption;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends BaseFrame implements LeftMenuAdapter.Listener {

    private static final String CREATE_LEMBRETE_ACTION = "create_lembrete";

    private JPanel lembreteJP;
    private JTextArea lembreteTextJTA;
    private LeftMenu leftMenu;
    private ActionBar mActionBar;

	public MainFrame() throws HeadlessException {
		lembreteJP = new JPanel();
	}

	@Override
	public void showFrame() {
		List<MenuOption> options = new ArrayList<>();
		options.add(new MenuOption("Importar", CREATE_LEMBRETE_ACTION));
		leftMenu = new LeftMenu("Lembretes", options, this);
		mActionBar = new ActionBar("Texto para importação");

		mContentPanel.add(leftMenu, BorderLayout.WEST);
		mContentPanel.add(lembreteJP, BorderLayout.CENTER);
		setupImportPanel();

		pack();
		setVisible(true);
	}

	private void setupImportPanel() {
        lembreteJP.setLayout(new BoxLayout(lembreteJP, BoxLayout.PAGE_AXIS));
        lembreteJP.setPreferredSize(new Dimension(WIDTH - LeftMenu.WIDTH - 32, 100));

        lembreteJP.add(mActionBar);

        lembreteTextJTA = new JTextArea(buildTestTextToDebug());
        JScrollPane jScrollPane = new JScrollPane(lembreteTextJTA);
        lembreteJP.add(jScrollPane);
    }

    private String buildTestTextToDebug() {
        return "Resultados principais\n" +
                "Lembrete\n" +
                "1Preparar férias\n" +
                "Lembrete\n" +
                "2add atalho no intellij para compare With clipboard\n" +
                "Lembrete\n" +
                "3configurar vpn da ZG no note\n" +
                "Lembrete\n" +
                "4verificar com pais, quanto estavam devendo\n" +
                "Lembrete\n" +
                "5pesquisar embalador de vacuo e criar documento com diferentes modelos e marcas\n" +
                "Todos os resultados\n" +
                "Lembrete\n" +
                "6teste agendado\n" +
                "Lembrete\n" +
                "7teste 2\n" +
                "Lembrete\n" +
                "Verificar pagamentos realizados pelo Itau no dia 10/04\n" +
                "Lembrete\n" +
                "apagar imagem no aws - portfolio, pois ira vencer o prazo de teste\n" +
                "Lembrete\n" +
                "Espremer limão. 1 semana\n" +
                "Lembrete\n" +
                "cobrar camiseta da Udacity";
    }

    private void showLembreteList(List<Lembrete> lembreteList) {
        LembreteFrame lembreteFrame = new LembreteFrame(this, lembreteList);
		lembreteFrame.showFrame();
        dispose();
    }

    private List<Lembrete> buildLembreteList() {
        List<String> lembreteTextList = LembreteTextProcessor.fromTextCopied(lembreteTextJTA.getText()).processToList();
		return LembreteListBuilder.fromTextList(lembreteTextList).build();
    }

	@Override
	public void onOptionAction(int position, MenuOption option) {
		System.out.println("Menu selecionado: " + option.getTitle());
		switch (option.getAction()) {
			case CREATE_LEMBRETE_ACTION:
				showLembreteList(buildLembreteList());
				break;
		}
	}
}
