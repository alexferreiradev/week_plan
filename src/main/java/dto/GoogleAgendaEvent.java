package dto;

import java.util.Date;
import java.util.Objects;

public class GoogleAgendaEvent {
	private String subject;
	private String description;
	private boolean isPrivate;
	private Date startDate;
	private Date endDate;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean aPrivate) {
		isPrivate = aPrivate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GoogleAgendaEvent that = (GoogleAgendaEvent) o;
		return isPrivate == that.isPrivate &&
				Objects.equals(subject, that.subject) &&
				Objects.equals(description, that.description) &&
				Objects.equals(startDate, that.startDate) &&
				Objects.equals(endDate, that.endDate);
	}

	@Override
	public int hashCode() {

		return Objects.hash(subject, description, isPrivate, startDate, endDate);
	}

	@Override
	public String toString() {
		return "GoogleAgendaEvent{" +
				"subject='" + subject + '\'' +
				", description='" + description + '\'' +
				", isPrivate=" + isPrivate +
				", startDate=" + startDate +
				", endDate=" + endDate +
				'}';
	}
}
