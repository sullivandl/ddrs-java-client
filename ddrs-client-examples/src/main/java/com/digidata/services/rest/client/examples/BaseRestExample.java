package com.digidata.services.rest.client.examples;

import java.util.UUID;

/**
 * A simple base class so we can reuse some code in our examples.
 * @author dan.sullivan
 *
 */
public abstract class BaseRestExample implements IRestExample {

	private SimpleDdrsFactory ddrsFactory;
	
	/**
	 * Write the given line to the underlying output stream.
	 * @param line the line of text to write.
	 * @return this instance for chaining purposes.
	 */
	protected BaseRestExample println(Object line) {
		System.out.println(line);
		return this;
	}
	
	/**
	 * Write a line of text using {@code String.format}.
	 * @param format the format to use.
	 * @param params the parameters to apply to the format.
	 * @return this instance for chaining purposes.
	 */
	protected BaseRestExample println(String format, Object... params) {
		System.out.println(String.format(format, params));
		return this;
	}
	
	/**
	 * A helper method to get a random string of text.
	 * @return a random string.
	 */
	protected String random() {
		return "jddrs" + UUID.randomUUID().toString().substring(0, 7);
	}
	
	/**
	 * Returns the configured {@link SimpleDdrsFactory}.
	 * @return the factory.
	 */
	protected SimpleDdrsFactory getDdrsFactory() {
		return ddrsFactory;
	}
	
	/**
	 * Run the example.
	 */
	public void execute(SimpleDdrsFactory ddrsFactory) {
		this.ddrsFactory = ddrsFactory;
		execute();
	}
	
	public abstract void execute();
}
