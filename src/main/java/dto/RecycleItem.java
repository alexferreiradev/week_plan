package dto;

import java.util.Objects;

public class RecycleItem<ItemType> {

	private boolean selected;
	private boolean enabled;
	private String toolTip;
	private ItemType item;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public ItemType getItem() {
		return item;
	}

	public void setItem(ItemType item) {
		this.item = item;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RecycleItem<?> that = (RecycleItem<?>) o;
		return selected == that.selected &&
				enabled == that.enabled &&
				Objects.equals(item, that.item);
	}

	@Override
	public int hashCode() {

		return Objects.hash(selected, enabled, item);
	}

	@Override

	public String toString() {
		return "RecycleItem{" +
				"selected=" + selected +
				", enabled=" + enabled +
				", item=" + item +
				'}';
	}

	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}

	public String getToolTip() {
		return toolTip;
	}
}
