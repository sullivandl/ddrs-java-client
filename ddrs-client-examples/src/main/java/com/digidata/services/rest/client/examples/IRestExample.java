package com.digidata.services.rest.client.examples;

/**
 * Interface to define what an example needs to do.
 * 
 * @author dan.sullivan
 *
 */
public interface IRestExample {

	/**
	 * Execute the example.
	 * @param ddrsFactory the factory with which Ddrs has been initialized.
	 */
	void execute(SimpleDdrsFactory ddrsFactory);
}
