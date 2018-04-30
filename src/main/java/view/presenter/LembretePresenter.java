package view.presenter;

import builder.GoogleCSVFileBuilder;
import dto.GoogleAgendaEvent;
import dto.RecycleItem;
import file.CSVFile;
import model.Lembrete;
import view.adapter.LembreteAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LembretePresenter implements LembreteContract.Presenter {

	private LembreteContract.View mView;
	private List<Lembrete> mLembreteList;
	private LembreteAdapter mLembreteAdapter;
	private int mTotalItemsSelected;

	public LembretePresenter(LembreteContract.View view, List<Lembrete> lembreteList) {
		this.mView = view;
		mLembreteList = lembreteList;
	}

	private String buildTextToInbox() {
		StringBuilder stringBuilder = new StringBuilder("is:reminder {");
		for (int i = 0; i < mLembreteAdapter.getTotalItems(); i++) {
			RecycleItem<Lembrete> item = mLembreteAdapter.getItem(i);
			if (item.isSelected()) {
				stringBuilder.append("\"").append(item.getItem().getDescricao()).append("\", ");
			}
		}
		stringBuilder.append("}");
		String textToInbox = stringBuilder.toString();
		textToInbox = textToInbox.replaceAll(", }", "}");

		return textToInbox;
	}

	private void initTotalItemSelected() {
		mTotalItemsSelected = 0;
	}

	@Override
	public void reImport() {
		mView.openMainFrame();
	}

	@Override
	public void exportToInbox() {
		String textToInbox = buildTextToInbox();
		mView.addTextToClipboard(textToInbox);
		mView.showInfoMsg("Texto copiado para área de transferência:\n\n" + textToInbox);
	}

	@Override
	public void exportToCSV() {
		List<Lembrete> selectedLembreteList = new ArrayList<>();
		for (int i = 0; i < mLembreteAdapter.getTotalItems(); i++) {
			RecycleItem<Lembrete> item = mLembreteAdapter.getItem(i);
			if (item.isSelected()) {
				selectedLembreteList.add(item.getItem());
			}
		}

		List<GoogleAgendaEvent> events = new ArrayList<>();
		for (Lembrete lembrete : selectedLembreteList) {
			GoogleAgendaEvent googleAgendaEvent = new GoogleAgendaEvent();
			googleAgendaEvent.setSubject(lembrete.getDescricao());
			googleAgendaEvent.setDescription("Gerado pelo Week Plan Software");
			googleAgendaEvent.setPrivate(true);

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 20);
			calendar.set(Calendar.MINUTE, 0);
			googleAgendaEvent.setStartDate(calendar.getTime());
			calendar.set(Calendar.HOUR_OF_DAY, 21);
			calendar.set(Calendar.MINUTE, 30);
			googleAgendaEvent.setEndDate(calendar.getTime());

			events.add(googleAgendaEvent);
		}

		File rootPath = new File("./");
//		Date today = new Date();
//		DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
//		String dateTimeString = dateTimeInstance.format(today);
//		dateTimeString = dateTimeString.replaceAll(":", "_");
//		dateTimeString = dateTimeString.replaceAll("/", "_");
//		dateTimeString = dateTimeString.replaceAll("\\\\", "_");
//		String fileName = "week_plan_to_google_agenda_" + dateTimeString;
		String fileName = "week_plan_to_google_agenda";
		CSVFile csvFile = GoogleCSVFileBuilder.createFrom(events).setFilePath(rootPath.getPath()).setFileName(fileName).buildFile();
		csvFile.saveFile(false);

		mView.showInfoMsg("Arquivo criado com sucesso: " + csvFile.getCompletedFileName());
	}

	@Override
	public void init() {
		mView.onStart();
		mLembreteAdapter = new LembreteAdapter(mLembreteList, mView);
		mView.createList(mLembreteAdapter);
		if (mLembreteAdapter.getTotalItems() > 0) {
			mView.setSelectAllItemViewEnableState(true);
		}
		mView.setActionBarTitle(mLembreteAdapter.getTotalItems() + " lembretes importados");
		initTotalItemSelected();
		mView.updateListView();
	}

	@Override
	public void changeLembreteOrder(int currentOrder, int newOrder) {
		mLembreteAdapter.changeItemPosition(currentOrder, newOrder);
		mView.updateListView();
	}

	@Override
	public void removeSelectedItems() {
		List<RecycleItem<Lembrete>> posToRemove = new ArrayList<>();
		for (int i = 0; i < mLembreteAdapter.getTotalItems(); i++) {
			RecycleItem<Lembrete> item = mLembreteAdapter.getItem(i);
			if (item.isSelected()) {
				posToRemove.add(item);
			}
		}
		for (RecycleItem<Lembrete> recycleItem : posToRemove) {
			boolean removed = mLembreteAdapter.removeItem(recycleItem);
			if (!removed) {
				throw new IllegalStateException("Não foi encontrado item para remover");
			}
		}

		if (mLembreteAdapter.getTotalItems() == 0) {
			mView.setSelectAllItemViewSelected(false);
			mView.setSelectAllItemViewEnableState(false);
			mView.setActionBarTitle("Lembretes importados");
		} else {
			mView.setActionBarTitle(mLembreteAdapter.getTotalItems() + " lembretes importados");
		}

		initTotalItemSelected();
		setSelectedViewState();
		mView.updateListView();
		mView.hideActionBarOptions();
	}

	@Override
	public void setItemSelectState(RecycleItem<Lembrete> item, int position, boolean state) {
		if (!state) {
			mView.setSelectAllItemViewSelected(state);
			mTotalItemsSelected--;
		} else {
			mTotalItemsSelected++;
		}

		item.setSelected(state);
		setSelectedViewState();
	}

	private void setSelectedViewState() {
		if (mTotalItemsSelected > 0) {
			mView.showActionBarOptions();
			mView.setExportToCSVEnableState(true);
			mView.setRemoveABOTitle("Remover " + mTotalItemsSelected + " lembretes");
		} else {
			mView.hideActionBarOptions();
			mView.setExportToCSVEnableState(false);
		}
	}

	@Override
	public void setAllItemsState(boolean state) {
		for (int i = 0; i < mLembreteAdapter.getTotalItems(); i++) {
			RecycleItem<Lembrete> item = mLembreteAdapter.getItem(i);
			item.setSelected(state);
			mLembreteAdapter.setItemIn(i, item);
		}

		if (state) {
			mTotalItemsSelected = mLembreteAdapter.getTotalItems();
		} else {
			initTotalItemSelected();
		}
		setSelectedViewState();
		mView.updateListView();
	}
}
