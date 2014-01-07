package com.digidata.services.rest.client;

import java.util.List;

/**
 * Class representing a Collection Aspect of a specific Entity type.
 * 
 * A convenience class for library consumers to easily convert {@link Collection}
 * instances using the {@link Collection#as(Class)} method.
 * @author dan.sullivan
 *
 * @param <T> the type of entities contained in this collection.
 */
public class CollectionTyped<T extends Entity> extends Collection {

	/**
	 * Constructs a new stronlgy typed collection.
	 */
	public CollectionTyped() {
		super();
	}
	
	/**
	 * Constructs a new, strongly typed collection from a non-typed collection.
	 * @param copy the collection from which to construct this typed variant.
	 */
	public CollectionTyped(Collection copy) {
		super(copy);
	}
	
	/**
	 * Gets all the members strongly typed as T.
	 * @return the strongly typed list.
	 */
	@SuppressWarnings(value = { "unchecked", "rawtypes" })
	public List<T> getMembersTyped() {
		return (List) getMembers();
	}

	/**
	 * Sets all the members in the collection as type T.
	 * @param members
	 */
	@SuppressWarnings(value = { "unchecked", "rawtypes" })
	public void setMembersTyped(List<T> members) {
		setMembers((List) members); 
	}
}
