package view.component.menu;

public class MenuOption {

    String mTitle;
    String mAction;

    public MenuOption(String mTitle, String action) {
        this.mTitle = mTitle;
        this.mAction = action;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAction() {
        return mAction;
    }
}
