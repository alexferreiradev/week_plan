package view.component.menu;

import java.util.Objects;

public class MenuOption {

    private String title;
    private String action;
    private boolean enabled;

    public MenuOption(String title, String action) {
        this.title = title;
        this.action = action;
        enabled = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuOption that = (MenuOption) o;
        return enabled == that.enabled &&
                Objects.equals(title, that.title) &&
                Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, action, enabled);
    }

    @Override
    public String toString() {
        return "MenuOption{" +
                "title='" + title + '\'' +
                ", action='" + action + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
