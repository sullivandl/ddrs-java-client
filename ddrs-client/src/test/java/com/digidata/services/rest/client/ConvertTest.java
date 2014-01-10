package com.digidata.services.rest.client;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * 
 * @author dan.sullivan
 *
 */
public class ConvertTest {

	@Test
	public void parse_int() {
		assertThat(Convert.parse("23", Integer.class), equalTo(new Integer(23)));
	}
	
	@Test
	public void parse_boolean() {
		assertThat(Convert.parse("true", Boolean.class), equalTo(new Boolean("true")));
	}
	
	@Test
	public void parse_double() {
		assertThat(Convert.parse("23.23", Double.class), equalTo(new Double("23.23")));
	}
	
	@Test 
	public void parse_dateNormalOffset() {
		String strVal = "1970-01-01T00:00:01.00-0000";
		Date dtVal = new Date();
		dtVal.setTime(1000);
		assertThat(Convert.parse(strVal, Date.class), equalTo(dtVal));
	}
	
	@Test 
	public void parse_dateNormalOffsetNoMilli() {
		String strVal = "1970-01-01T00:00:01-0000";
		Date dtVal = new Date();
		dtVal.setTime(1000);
		assertThat(Convert.parse(strVal, Date.class), equalTo(dtVal));
	}
	
	@Test 
	public void parse_dateUtc() {
		String strVal = "1970-01-01T00:00:01.00Z";
		Date dtVal = new Date();
		dtVal.setTime(1000);
		assertThat(Convert.parse(strVal, Date.class), equalTo(dtVal));
	}
	
	@Test 
	public void parse_dateUtcNoMilli() {
		String strVal = "1970-01-01T00:00:01Z";
		Date dtVal = new Date();
		dtVal.setTime(1000);
		assertThat(Convert.parse(strVal, Date.class), equalTo(dtVal));
	}
	
	@Test
	public void asString_int() {
		assertThat(Convert.asString(23, Integer.class), equalTo("23"));
	}
	
	@Test
	public void asString_bolean() {
		assertThat(Convert.asString(true, Boolean.class), equalTo("true"));
	}
	
	@Test
	public void asString_double() {
		assertThat(Convert.asString(23.23d, Double.class), equalTo("23.23"));
	}
	
	@Test
	@Ignore("TODO: how should we really be handling offsets?")
	public void asString_date() {
		String strVal = "1970-01-01T00:00:01.000-0700";
		Date dtVal = new Date();
		dtVal.setTime(1000);
		assertThat(Convert.asString(dtVal, Date.class), equalTo(strVal));
	}
	
}
