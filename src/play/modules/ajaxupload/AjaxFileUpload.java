package play.modules.ajaxupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import play.data.Upload;
import play.data.parsing.TempFilePlugin;
import play.exceptions.UnexpectedException;
import play.libs.IO;

public class AjaxFileUpload implements Upload {
	
	private static final String FIELD_NAME = "qqfile";
	
	private String filename;
	private File defaultFile;
	
	public AjaxFileUpload(InputStream is, String filename) {
		this.filename = filename;
		this.defaultFile = new File(TempFilePlugin.createTempFolder(), filename);
		try {
			IOUtils.copy(is, new FileOutputStream(defaultFile));
		} catch (Exception e) {
            throw new IllegalStateException("Error when trying to write to file " + defaultFile.getAbsolutePath(), e);
		} 
	}
	
	@Override
	public byte[] asBytes() {
		return IO.readContent(defaultFile);
	}

	@Override
	public InputStream asStream() {
	 try {
            return new FileInputStream(defaultFile);
        } catch (IOException ex) {
            throw new UnexpectedException(ex);
        }
	}

	@Override
	public String getContentType() {
		// TODO: YO! Implement this!
		return null;
	}

	@Override
	public String getFileName() {
		return filename;
	}

	@Override
	public String getFieldName() {
		return FIELD_NAME;
	}

	@Override
	public Long getSize() {
		return defaultFile.length();
	}

	@Override
	public boolean isInMemory() {
		// TODO: YO! Implement this!
		return false;
	}

	@Override
	public File asFile() {
		return defaultFile;
	}

}
