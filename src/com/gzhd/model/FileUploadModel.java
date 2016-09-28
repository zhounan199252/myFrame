package com.gzhd.model;

import java.io.File;

public class FileUploadModel {

	private File NewFile;

	private String NewFileFileName;

	private String NewFileContentType;

	public File getNewFile() {
		return NewFile;
	}

	public void setNewFile(File newFile) {
		NewFile = newFile;
	}

	public String getNewFileFileName() {
		return NewFileFileName;
	}

	public void setNewFileFileName(String newFileFileName) {
		NewFileFileName = newFileFileName;
	}

	public String getNewFileContentType() {
		return NewFileContentType;
	}

	public void setNewFileContentType(String newFileContentType) {
		NewFileContentType = newFileContentType;
	}

}
