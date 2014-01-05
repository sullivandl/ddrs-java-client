package com.digidata.services.rest.client;

import java.util.List;

public class CollectionTyped<T extends Entity> extends Collection {

	public CollectionTyped() {
		super();
	}
	
	public CollectionTyped(Collection copy) {
		super(copy);
	}
	
	@SuppressWarnings(value = { "unchecked", "rawtypes" })
	public List<T> getMembersTyped() {
		return (List) getMembers();
	}

	@SuppressWarnings(value = { "unchecked", "rawtypes" })
	public void setMembersTyped(List<T> members) {
		setMembers((List) members); 
	}
}
