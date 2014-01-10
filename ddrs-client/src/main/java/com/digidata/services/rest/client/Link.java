package com.digidata.services.rest.client;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class representing link elements.
 * @author dan.sullivan
 *
 */
@JsonIgnoreProperties(ignoreUnknown=false)
public class Link extends DdrsElement {

	private String rel;
	private String href;
	private String type;
	private String hreflang;
	private String base;
	

	/**
	 * Gets the link's rel attribute.
	 * @return the rel attribute value.
	 */
	public String getRel() {
		return rel;
	}
	
	/**
	 * Sets the link's rel attribute.
	 * @param rel the rel attribute value.
	 */
	public void setRel(String rel) {
		this.rel = rel;
	}
	
	/**
	 * Gets the link's href attribute.
	 * @return the href attribute value.
	 */
	public String getHref() {
		return href;
	}
	
	/**
	 * Sets the link's href attribute.
	 * @param href the href attribute value.
	 */
	public void setHref(String href) {
		this.href = href;
	}
	
	/**
	 * Gets the link's type attribute.
	 * @return the type attribute value.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the link's type attribute.
	 * @param type the type attribute value.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Gets the link's hreflang attribute.
	 * @return the hreflang attribute value.
	 */
	public String getHreflang() {
		return hreflang;
	}
	
	/**
	 * Sets the link's hreflang attribute.
	 * @param hreflang the hreflang attribute value.
	 */
	public void setHreflang(String hreflang) {
		this.hreflang = hreflang;
	}
	
	/**
	 * Gets the link's base attribute.
	 * @return the base attribute value.
	 */
	public String getBase() {
		return base;
	}
	
	/**
	 * Sets the link's base attribute.
	 * @param base the base attribute value.
	 */
	public void setBase(String base) {
		this.base = base;
	}
	
	/**
	 * GETs a strongly typed document using the href of this link.
	 * @param responseType the expected type.
	 * @return the document retrieved from the service.
	 */
	public <T extends Document> T get(Class<T> responseType) {
		return Ddrs.getClient().get(href, responseType);
	}
	
	/**
	 * GETs a strongly typed document using the href of this link.
	 * @param responseType the expected type.
	 * @param compact whether or not to GET a compact representation of the document.
	 * @return the document retrieved from the service.
	 */
	public <T extends Document> T get(Class<T> responseType, boolean compact) {
		return Ddrs.getClient().get(href, responseType, compact);
	}
	
	/**
	 * GETs the raw data stream found at this link's href.
	 * @return the data stream from the service.
	 */
	@JsonIgnore
	public OutputStream getData() {
		return Ddrs.getClient().get(href);
	}
	
	/**
	 * PUTs a strongly typed entity to the href of this link.
	 * @param entity the entity to put.
	 * @param responseType the entity type.
	 */
	public <T extends Entity> void put(T entity, Class<T> responseType) {
		Ddrs.getClient().put(href, entity, responseType);
	}
	
	/**
	 * PUTs a raw input stream to the href of this link.
	 * @param stream the raw data stream.
	 */
	public void put(InputStream stream) {
		Ddrs.getClient().put(href, stream);
	}
	
	/**
	 * POSTs to this link's href (usually with URL parameters on a {@link LinkTemplate})
	 * and returns the strongly typed entity.
	 * @param responseType the type of entity returned by the service. 
	 * @return the newly created entity from the service.
	 */
	public <T extends Entity> T post(Class<T> responseType) {
		return Ddrs.getClient().post(href, responseType);
	}
	
	/**
	 * POSTs to this link's href (usually with URL parameters on a {@link LinkTemplate})
	 * and returns the strongly typed entity.
	 * @param responseType the type of entity returned by the service. 
	 * @param compact whether or not to return a compact representation of the new entity.
	 * @return the newly created entity from the service.
	 */
	public <T extends Entity> T post(Class<T> responseType, boolean compact) {
		return Ddrs.getClient().post(href, responseType, compact);
	}
	
	/**
	 * POSTs a strongly typed entity to this link's href.
	 * @param entity the entity to POST.
	 * @param responseType the type of entity sent and returned by the service.
	 * @return the newly created entity from the service.
	 */
	public <T extends Entity> T post(T entity, Class<T> responseType) {
		return Ddrs.getClient().post(href, entity, responseType);
	}
	
	/**
	 * POSTs a strongly typed entity to this link's href.
	 * @param entity the entity to POST.
	 * @param responseType the type of entity sent and returned by the service.
	 * @param compact whether or not to return a compact representation of the new entity.
	 * @return the newly created entity from the service.
	 */
	public <T extends Entity> T post(T entity, Class<T> responseType, boolean compact) {
		return Ddrs.getClient().post(href, entity, responseType, compact);
	}
	
	/**
	 * DELETEs the entity located at this link's href.
	 */
	public void delete() {
		Ddrs.getClient().delete(href);
	}

	
	/**
	 * Returns a friendly representation of this link.
	 * @return the string representation of this link.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
					.appendSuper(super.toString())
					.append("href", getHref())
					.append("rel", getRel())
					.append("type", getType())
					.toString();
	}
}
