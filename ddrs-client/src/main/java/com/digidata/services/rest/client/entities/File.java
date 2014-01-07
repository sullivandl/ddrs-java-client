package com.digidata.services.rest.client.entities;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Files are the entities most closely associated with user data stored
 * in the system. They include primarily metadata for that user data.
 * 
 * @author dan.sullivan
 *
 */
public class File extends FilesystemEntity {

	/**
	 * Gets the date of last modification.
	 * @return the modified date.
	 */
	public Date getModified() {
		return getProperty("modified", Date.class);
	}

	/**
	 * Sets the date of last modification.
	 * @param modified the modified date.
	 */
	public void setModified(Date modified) {
		setProperty("modified", modified, Date.class);
	}
	
	/**
	 * Gets the file's MIME type that is derived from the file name's
	 * extension. Files with unrecognized or missing extensions will
	 * have a value of application/octet-stream
	 * @return the MIME type.
	 */
	public String getContentType() {
		return getProperty("contentType");
	}

	/**
	 * Sets the file's MIME type.
	 * @param contentType the MIME type.
	 */
	public void setContentType(String contentType) {
		setProperty("contentType", contentType);
	}
	
	/**
	 * Gets the size of the file's most recent version in bytes.
	 * @return the size of the file.
	 */
	public double getSize() {
		return getProperty("size", Double.class);
	}

	/**
	 * Sets the size of the file.
	 * @param size the size of the file.
	 */
	public void setSize(double size) {
		setProperty("size", size, Double.class);
	}

	/**
	 * Convenience method to download this file.
	 * @return the raw stream containing the file data.
	 */
	public OutputStream download() {
		return this.getAssociations().findLink("data").getData();
	}
	
	/**
	 * Convenience method to upload a new version of this file.
	 * @param stream the raw stream to PUT to this file, creating a new version.
	 */
	public void upload(InputStream stream) {
		this.getAssociations().findLink("data").put(stream);
	}
	
	/**
	 * Returns a friendly representation of this file.
	 * @return the string representation of this file.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.appendSuper(super.toString())
				.append("size", getSize())
				.append("contentType", getContentType())
				.toString();
	}
}
