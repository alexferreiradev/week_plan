package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class CSVFile extends BaseFile {
	public static final String FILE_EXTENSION = "csv";

	public CSVFile(String mFilePath, String mFileName) {
		super(mFilePath, mFileName);
		mFileExtension = FILE_EXTENSION;
	}

	public CSVFile(File file) {
		super(file);
		mFileExtension = FILE_EXTENSION;
	}

	@Override
	public void saveFile(boolean append) {
		if (mFileText == null) {
			throw new IllegalArgumentException("Tentando salvar arquivo sem conteudo");
		}

		try {
			FileWriter fileWriter = new FileWriter(mFilePath + "/" + mFileName + "." + mFileExtension);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(mFileText);
			bufferedWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getFileText() {
		return buildFileTextFromContent();
	}

	@Override
	public void setFileText(String text) {
		mFileText = text;
		// todo validar conteudo para ver se é csv
	}

	@Override

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CSVFile CSVFile = (CSVFile) o;
		return Objects.equals(mFilePath, CSVFile.mFilePath) &&
				Objects.equals(mFileName, CSVFile.mFileName) &&
				Objects.equals(mFileText, CSVFile.mFileText) &&
				Objects.equals(mFileExtension, CSVFile.mFileExtension) &&
				Arrays.equals(mFileContents, CSVFile.mFileContents);
	}

	@Override
	public int hashCode() {

		int result = Objects.hash(mFilePath, mFileName, mFileText, mFileExtension);
		result = 31 * result + Arrays.hashCode(mFileContents);
		return result;
	}

	@Override
	public String toString() {
		return "CSVFile{" +
				"mFilePath='" + mFilePath + '\'' +
				", mFileName='" + mFileName + '\'' +
				", mFileText='" + mFileText + '\'' +
				", mFileExtension='" + mFileExtension + '\'' +
				", mFileContents=" + Arrays.toString(mFileContents) +
				'}';
	}
}
