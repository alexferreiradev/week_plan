package view.presenter;

import dto.RecycleItem;
import model.Lembrete;
import view.adapter.LeftMenuAdapter;
import view.adapter.LembreteAdapter;
import view.component.ActionBar;

public interface LembreteContract {

	interface Presenter {
		void init();

		void reImport();

		void exportToInbox();

		void exportToCSV();

		// funcoes da lista
		void changeLembreteOrder(int currentOrder, int newOrder);

		void removeSelectedItems();

		void setItemSelectState(RecycleItem<Lembrete> item, int position, boolean state);

		void setAllItemsState(boolean state);
	}

	interface View extends LembreteAdapter.LembreteListener, LeftMenuAdapter.Listener, ActionBar.ActionBarMenuListener {

		void onStart();

		void createList(LembreteAdapter adapter);

		void clearList();

		void updateListView();

		void showActionBarOptions();

		void hideActionBarOptions();

		void showInfoMsg(String msg);

		void openMainFrame();

		void addTextToClipboard(String text);

		void setSelectAllItemState(boolean state);

		void setExportToCSVEnableState(boolean state);
	}
}
