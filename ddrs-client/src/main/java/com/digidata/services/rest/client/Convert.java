package com.digidata.services.rest.client;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.digidata.services.rest.client.exceptions.DdrsException;
import com.digidata.services.rest.client.exceptions.DdrsParseException;

/**
 * Simple converter to help serialize and deserialize the basic types returned by the
 * service.
 * 
 * @author dan.sullivan
 *
 */
class Convert {

	private static final String[] ISO8601_FORMATS = new String[] {
		"yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
		"yyyy-MM-dd'T'HH:mm:ssXXX",
		"yyyy-MM-dd'T'HH:mm:ss.SSSZ",
		"yyyy-MM-dd'T'HH:mm:ssZ"
	};
	
	private static final String PREFERRED_ISO8601_FORMAT = ISO8601_FORMATS[0];
	
	/**
	 * Convert the string value to the desired type.
	 * @param strValue the value to parse.
	 * @param responseType the type to return.
	 * @return the value of the incoming string converted to the specified type.
	 * @throws DdrsException if the string value cannot be converted to the given type.
	 */
	@SuppressWarnings("unchecked")
	static <T> T parse(String strValue, Class<T> responseType) {
		
		//  Sounds like life will be better in Java 8.  A bit ugly, but
		//   seems ok for our few types - int, double, Date
		try {
			if(strValue == null) {
				return null;
			} else if(responseType != Date.class) {
				return (T) responseType.getConstructor(String.class).newInstance(strValue);
			} else {
				return (T) parseIso8601(strValue);
			}
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ParseException e) {
			throw new DdrsParseException(String.format("Unable to parse property of type %1$s with value '%2$s'", responseType, strValue), e);
		}
	}
	
	/**
	 * Convert the typed value to a string.
	 * @param value the typed value.
	 * @param responseType the type of the incoming value.
	 * @return the String representation of the incoming value.
	 */
	static <T> String asString(T value, Class<T> responseType) {
		if(value == null) {
			return null;
		} else if(value instanceof Date) {
			SimpleDateFormat format = new SimpleDateFormat(PREFERRED_ISO8601_FORMAT);
			return format.format((Date) value);
		} else {
			return value.toString();
		}
	}
	
	/**
	 * Parses the string as an ISO8601 date.
	 * @param dateString the date string to parse.
	 * @return the Date representation of the incoming string.
	 * @throws ParseException if the incoming string is not a valid ISO8601 date.
	 */
	private static Date parseIso8601(String dateString) throws ParseException {
		
		ParseException last = null;
		for(String format : ISO8601_FORMATS) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat(format);
				return dateFormat.parse(dateString);
			} catch(ParseException ex) {
				last = ex;
			}
		}
		
		throw last;
	}
}
