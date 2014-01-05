package com.digidata.services.rest.client;

import java.io.InputStream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=false)
public class Link extends DdrsElement {

	private String rel;
	private String href;
	private String type;
	private String hreflang;
	private String base;
	
	public <T extends Document> T get(Class<T> responseType) {
		return Ddrs.getClient().get(href, responseType);
	}
	
	public <T extends Document> T get(Class<T> responseType, boolean compact) {
		return Ddrs.getClient().get(href, responseType, compact);
	}
	
	public <T extends Entity> T put(T entity, Class<T> responseType) {
		return Ddrs.getClient().put(href, entity, responseType);
	}
	
	public <T extends Entity> T put(T entity, Class<T> responseType, boolean compact) {
		return Ddrs.getClient().put(href, entity, responseType, compact);
	}
	
	public void put(InputStream stream) {
		Ddrs.getClient().put(href, stream);
	}
	
	public <T extends Entity> T post(Class<T> responseType) {
		return Ddrs.getClient().post(href, responseType);
	}
	
	public <T extends Entity> T post(Class<T> responseType, boolean compact) {
		return Ddrs.getClient().post(href, responseType, compact);
	}
	
	public <T extends Entity> T post(T entity, Class<T> responseType) {
		return Ddrs.getClient().post(href, entity, responseType);
	}
	
	public <T extends Entity> T post(T entity, Class<T> responseType, boolean compact) {
		return Ddrs.getClient().post(href, entity, responseType, compact);
	}
	
	public void delete() {
		Ddrs.getClient().delete(href);
	}
	
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHreflang() {
		return hreflang;
	}
	public void setHreflang(String hreflang) {
		this.hreflang = hreflang;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	
	@Override
	public String toString() {
		return rel + " = " + href;
	}
}
