package com.digidata.services.rest.client;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class representing Collection Aspects.
 * 
 * @author dan.sullivan
 *
 */
public class Collection extends Document {
	
	private List<Entity> members;
	
	/**
	 * Construct a new, empty collection.
	 */
	public Collection() {
		members = new ArrayList<>();
	}
	
	/**
	 * Construct a new collection by copying the incoming collection.
	 * @param copy the Collection to copy.
	 */
	public Collection(Collection copy) {
		setTotal(copy.getTotal());
		setStart(copy.getStart());
		setCount(copy.getCount());
		setMembers(new ArrayList<Entity>(copy.getMembers()));
	}
	
	/**
	 * Gets the total number of items in the collection on the server.
	 * @return the total.
	 */
	@JsonIgnore
	public int getTotal() {
		return getProperty("total", Integer.class);
	}
	
	/**
	 * Sets the total number of items in the collection on the server.
	 * @param total the total.
	 */
	@JsonIgnore
	public void setTotal(int total) {
		setProperty("total", total, Integer.class);
	}

	/**
	 * Gets the start index of this collection result.
	 * @return the start index.
	 */
	@JsonIgnore
	public int getStart() {
		return getProperty("start", Integer.class);
	}
	
	/**
	 * Sets the start index of this collection result.
	 * @param start the start index.
	 */
	@JsonIgnore
	public void setStart(int start) {
		setProperty("start", start, Integer.class);
	}
	
	/**
	 * Gets the number of items in this collection result.
	 * @return the count.
	 */
	@JsonIgnore
	public int getCount() {
		return getProperty("count", Integer.class);
	}
	
	/**
	 * Sets the number of items in this collection result.
	 * @param count the count.
	 */
	@JsonIgnore
	public void setCount(int count) {
		setProperty("count", count, Integer.class);
	}

	/**
	 * Gets the list of entities in this collection.
	 * @return the members.
	 */
	public List<Entity> getMembers() {
		return members;
	}

	/**
	 * Sets the list of entities in this collection.
	 * @param members the members.
	 */
	public void setMembers(List<Entity> members) {
		this.members = members;
	}
	
	/**
	 * Converts this collection to a {@link CollectionTyped} for easier
	 * type management.
	 * 
	 * @param responseType the type to cast the items in the collection to.
	 * @return the collection as a typed collection.
	 */
	public <T extends Entity> CollectionTyped<T> as(Class<T> responseType) {
		return new CollectionTyped<T>(this);
	}
}
