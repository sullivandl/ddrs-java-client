package com.digidata.services.rest.client.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.digidata.services.rest.client.Ddrs;
import com.digidata.services.rest.client.DdrsOptions;

/**
 * This is a simple command line application to run the functional examples.
 * @author dan.sullivan
 *
 */
public class Application {

	// Set your account credentials below
	private static final String USERNAME = "<your username>";
	private static final String PASSWORD = "<your password>";
	private static final String DDRS_URL = "http://www.leapdrive.com/rest";

	private static final List<IRestExample> examples = new ArrayList<IRestExample>(
			Arrays.asList(new StartFreshExample(), 
					      new ContactListingExample(), 
					      new ContactManagementExample(),
					      new FileUploadExample(),
					      new FileSystemExample(),
					      new ErrorHandlingExample()));
	
	public static void main(String[] args) {

		// DdrsOptions allows you to modify the default configuration (proxy settings, etc.)
		DdrsOptions options = new DdrsOptions();
		options.setUserAgent("com.digidata.services.rest.client.examples");

		// Initialize the Ddrs client
		SimpleDdrsFactory ddrsFactory = new SimpleDdrsFactory(DDRS_URL, USERNAME, PASSWORD, options);
		if(!Ddrs.isInitialized())
			Ddrs.Initialize(ddrsFactory);
    	
		// Run each example
		for(IRestExample ex : examples) {
			System.out.println(String.format("Example: %1$s", ex.getClass().getSimpleName()));
			ex.execute(ddrsFactory);
			ddrsFactory.revertUser();
			System.out.println();
		}
	}
}
