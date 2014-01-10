package com.digidata.services.rest.client;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.digidata.services.rest.client.entities.User;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * 
 * @author dan.sullivan
 *
 */
public class LinkTest extends DdrsTest {

	private Ddrs ddrs;
	
	@Before
	@Override
	public void setup() {
		super.setup();
		
		ddrs = new Ddrs("", "", "");
		when(ddrsFactory.getDdrs()).thenReturn(ddrs);
	}
	
	@Test
	public void startFresh_returnsUser() {
		User user = new User();
		when(restClient.get("users/me", User.class)).thenReturn(user);
		assertSame(user, Ddrs.startFresh());
	}
	
	@Test
	public void get_callsClient() {
		Link link = new Link();
		link.setHref("get_callsClient");
		link.get(User.class);
		verify(restClient, times(1)).get(link.getHref(), User.class);
	}
	
	@Test
	public void get_callsClientCompact() {
		Link link = new Link();
		link.setHref("get_callsClientCompact");
		link.get(User.class, false);
		verify(restClient, times(1)).get(link.getHref(), User.class, false);
	}
	
	@Test
	public void getData_callsClient() {
		Link link = new Link();
		link.setHref("get_callsClientStream");
		link.getData();
		verify(restClient, times(1)).get(link.getHref());
	}
	
	@Test
	public void put_callsClient() {
		Link link = new Link();
		User user = new User();
		link.setHref("put_callsClient");
		link.put(user, User.class);
		verify(restClient, times(1)).put(link.getHref(), user, User.class);
	}
	
	@Test
	public void putData_callsClient() {
		Link link = new Link();
		InputStream stream = mock(InputStream.class);
		link.setHref("putData_callsClient");
		link.put(stream);
		verify(restClient, times(1)).put(link.getHref(), stream);
	}
	
	@Test
	public void post_callsClient() {
		Link link = new Link();
		link.setHref("post_callsClient");
		link.post(User.class);
		verify(restClient, times(1)).post(link.getHref(), User.class);
	}
	
	@Test
	public void post_callsClientCompact() {
		Link link = new Link();
		link.setHref("post_callsClient");
		link.post(User.class, false);
		verify(restClient, times(1)).post(link.getHref(), User.class, false);
	}
	
	@Test
	public void post_callsClientWithEntity() {
		Link link = new Link();
		User user = new User();
		link.setHref("post_callsClientWithEntity");
		link.post(user, User.class);
		verify(restClient, times(1)).post(link.getHref(), user, User.class);
	}
	
	@Test
	public void post_callsClientWithEntityCompact() {
		Link link = new Link();
		User user = new User();
		link.setHref("post_callsClientWithEntityCompact");
		link.post(user, User.class, false);
		verify(restClient, times(1)).post(link.getHref(), user, User.class, false);
	}
	
	@Test
	public void delete_callsClient() {
		Link link = new Link();
		link.setHref("delete_callsClient");
		link.delete();
		verify(restClient, times(1)).delete(link.getHref());
	}
}
