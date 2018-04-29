package file;

public interface OwnFile {

	void saveFile(boolean append);

	String getFileText();

	String getCompletedFileName();

	void setFileText(String text);

}
