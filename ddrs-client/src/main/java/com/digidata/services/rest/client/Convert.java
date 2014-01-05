package com.digidata.services.rest.client;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.digidata.services.rest.client.exceptions.DdrsClientException;

class Convert {

	private static final String[] ISO8601_FORMATS = new String[] {
		"yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
		"yyyy-MM-dd'T'HH:mm:ssXXX",
		"yyyy-MM-dd'T'HH:mm:ss.SSSZ",
		"yyyy-MM-dd'T'HH:mm:ssZ"
	};
	
	private static final String PREFERRED_ISO8601_FORMAT = ISO8601_FORMATS[0];
	
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
			throw new DdrsClientException(String.format("Unable to get property of type %s", responseType), e);
		}
	}
	
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
