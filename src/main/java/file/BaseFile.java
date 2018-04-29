package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

abstract class BaseFile implements OwnFile {

	protected String mFilePath;
	protected String mFileName;
	protected String mCompletedFileName;
	protected String mFileText;
	protected String mFileExtension;
	protected byte[] mFileContents;

	public BaseFile(String mFilePath, String mFileName) {
		this.mFilePath = mFilePath;
		this.mFileName = mFileName;
		mFileExtension = "txt";
	}

	protected BaseFile(File file) {
		mFileName = file.getName();
		mFilePath = file.getPath();
		mFileExtension = "txt";
	}

	protected void buildContentBytes() {
		try {
			FileReader in = new FileReader(new File(getCompletedFileName()));
			BufferedReader bufferedReader = new BufferedReader(in);
			List<Integer> bytes = new ArrayList<>();
			while (bufferedReader.ready()) {
				bytes.add(bufferedReader.read());
			}
			mFileContents = new byte[bytes.size()];
			for (int i = 0; i < bytes.size(); i++) {
				int byteInt = bytes.get(i);
				mFileContents[i] = (byte) byteInt;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String buildFileTextFromContent() {
		buildContentBytes();
		StringBuilder stringBuffer = new StringBuilder();
		for (int i = 0; i < mFileContents.length; i++) {
			stringBuffer.append(String.valueOf(mFileContents[i]));
		}

		return mFileText = stringBuffer.toString();
	}

	@Override
	public String getCompletedFileName() {
		return mFilePath + "/" + mFileName + "." + mFileExtension;
	}

	public String getmFilePath() {
		return mFilePath;
	}

	public void setmFilePath(String mFilePath) {
		this.mFilePath = mFilePath;
	}

	public String getmFileName() {
		return mFileName;
	}

	public void setmFileName(String mFileName) {
		this.mFileName = mFileName;
	}

	public void setmFileText(String mFileText) {
		this.mFileText = mFileText;
	}

	public String getmFileExtension() {
		return mFileExtension;
	}

	public void setmFileExtension(String mFileExtension) {
		this.mFileExtension = mFileExtension;
	}

	public byte[] getmFileContents() {
		return mFileContents;
	}

	public void setmFileContents(byte[] mFileContents) {
		this.mFileContents = mFileContents;
	}
}
