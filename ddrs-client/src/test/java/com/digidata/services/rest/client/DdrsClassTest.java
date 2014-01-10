package com.digidata.services.rest.client;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author dan.sullivan
 *
 */
public class DdrsClassTest extends DdrsTest {

	@Before
	public void setup() {
		super.setup();
	}
	
	@Test
	public void construct_getRestClient() {
		new Ddrs("construct_getRestClient", mock(IClientCustomization.class), mock(DdrsOptions.class));
		verify(restFramework, times(1)).getRestClient("construct_getRestClient");
	}
	
	@Test
	public void construct_callsCustomize() {
		IClientCustomization customization = mock(IClientCustomization.class);
		new Ddrs("", customization, new DdrsOptions());
		verify(customization, times(1)).customize(restClient);
	}
	
	@Test
	public void construct_usesTimeoutOption() {
		DdrsOptions options = mock(DdrsOptions.class);
		when(options.getTimeout()).thenCallRealMethod();
		new Ddrs("", mock(IClientCustomization.class), options);
		verify(restClient).setTimeout(options.getTimeout());
	}
	
	@Test
	public void construct_usesUserAgentOption() {
		DdrsOptions options = mock(DdrsOptions.class);
		when(options.getUserAgent()).thenCallRealMethod();
		new Ddrs("", mock(IClientCustomization.class), options);
		verify(restClient).setUserAgent(options.getUserAgent());
	}
	
	@Test
	public void construct_usesProxy() {
		DdrsOptions options = mock(DdrsOptions.class);
		when(options.getProxyHost()).thenReturn("127.0.0.1");
		when(options.getProxyPort()).thenReturn(8888);
		new Ddrs("", mock(IClientCustomization.class), options);
		verify(restClient).setProxy(options.getProxyHost(), options.getProxyPort());
	}
	
	@Test
	public void construct_skipsProxyNoHost() {
		DdrsOptions options = mock(DdrsOptions.class);
		when(options.getProxyHost()).thenReturn(null);
		when(options.getProxyPort()).thenReturn(8888);
		new Ddrs("", mock(IClientCustomization.class), options);
		verify(restClient, never()).setProxy(anyString(), anyInt());
	}
	
	@Test
	public void construct_skipsProxyNoPort() {
		DdrsOptions options = mock(DdrsOptions.class);
		when(options.getProxyHost()).thenReturn("127.0.0.1");
		when(options.getProxyPort()).thenReturn(0);
		new Ddrs("", mock(IClientCustomization.class), options);
		verify(restClient, never()).setProxy(anyString(), anyInt());
	}
	
	@Test
	public void construct_setsContentType() {
		DdrsOptions options = mock(DdrsOptions.class);
		new Ddrs("", mock(IClientCustomization.class), options);
		verify(restClient).setContentType(Ddrs.CONTENT_TYPE_JSON);
	}
}
