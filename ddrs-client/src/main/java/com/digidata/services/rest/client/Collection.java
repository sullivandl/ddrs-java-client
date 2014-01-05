package com.digidata.services.rest.client;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Collection extends Document {
	
	private List<Entity> members;
	
	public Collection() {
		members = new ArrayList<>();
	}
	
	public Collection(Collection copy) {
		setTotal(copy.getTotal());
		setStart(copy.getStart());
		setCount(copy.getCount());
		setMembers(copy.getMembers());
	}
	
	@JsonIgnore
	public int getTotal() {
		return getProperty("total", Integer.class);
	}
	
	@JsonIgnore
	public void setTotal(int total) {
		setProperty("total", total, Integer.class);
	}

	@JsonIgnore
	public int getStart() {
		return getProperty("start", Integer.class);
	}
	
	@JsonIgnore
	public void setStart(int start) {
		setProperty("start", start, Integer.class);
	}
	
	@JsonIgnore
	public int getCount() {
		return getProperty("count", Integer.class);
	}
	
	@JsonIgnore
	public void setCount(int count) {
		setProperty("count", count, Integer.class);
	}

	public List<Entity> getMembers() {
		return members;
	}

	public void setMembers(List<Entity> members) {
		this.members = members;
	}
	
	public <T extends Entity> CollectionTyped<T> as(Class<T> responseType) {
		return new CollectionTyped<T>(this);
	}
}
