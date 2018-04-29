package builder;

import dto.GoogleAgendaEvent;
import file.CSVFile;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class GoogleCSVFileBuilder {
	private static final String CSV_SEPARATOR = ",";
	private List<GoogleAgendaEvent> events;
	private String filePath;
	private String fileName;

	private GoogleCSVFileBuilder(List<GoogleAgendaEvent> events) {
		this.events = events;
	}

	public static GoogleCSVFileBuilder createFrom(List<GoogleAgendaEvent> events) {
		return new GoogleCSVFileBuilder(events);
	}

	public GoogleCSVFileBuilder setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public GoogleCSVFileBuilder setFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}

	public CSVFile buildFile() {
		String[] staticHeader = {"Subject", "Description", "Private", "Start date", "End date", "Start time", "End time"};
		CSVFile csvFile = new CSVFile(filePath, fileName);
		StringBuilder fileText = new StringBuilder();
		for (int i = 0; i < staticHeader.length; i++) {
			fileText.append(staticHeader[i]).append(CSV_SEPARATOR);
		}
		fileText = fileText.replace(fileText.length() - 1, fileText.length(), ""); // remove virgula
		fileText.append("\n");
		for (GoogleAgendaEvent event : events) {
			fileText.append(event.getSubject().trim());
			fileText.append(CSV_SEPARATOR);
			fileText.append(event.getDescription());
			fileText.append(CSV_SEPARATOR);
			fileText.append(String.valueOf(event.isPrivate()).toUpperCase());
			fileText.append(CSV_SEPARATOR);
			Date startDate = event.getStartDate();
			Date endDate = event.getEndDate();
			DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.SHORT);
			fileText.append(dateInstance.format(startDate));
			fileText.append(CSV_SEPARATOR);
			fileText.append(dateInstance.format(endDate));
			fileText.append(CSV_SEPARATOR);
			DateFormat timeInstance = DateFormat.getTimeInstance(DateFormat.MEDIUM);
			fileText.append(timeInstance.format(startDate));
			fileText.append(CSV_SEPARATOR);
			fileText.append(timeInstance.format(endDate));
			fileText.append("\n");
		}
		csvFile.setFileText(fileText.toString());

		return csvFile;
	}
}
