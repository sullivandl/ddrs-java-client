package com.digidata.services.rest.client;

/**
 * Implement this interface to manage your DDRS session context.
 * 
 * @see Ddrs#Initialize(IDdrsFactory)
 * @author dan.sullivan
 *
 */
public interface IDdrsFactory {
	
	/**
	 * Gets the DDRS instance to use in this session.
	 * @return the {@link Ddrs} associated with the 'current' user.
	 */
	Ddrs getDdrs();
}
