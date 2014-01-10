package com.digidata.services.rest.client;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * Simple base class to mock and init the Ddrs client.
 * 
 * @author dan.sullivan
 *
 */
public class DdrsTest {

	protected IRestClient restClient = mock(IRestClient.class);
	protected static IRestFramework restFramework = mock(IRestFramework.class);
	protected static IDdrsFactory ddrsFactory = mock(IDdrsFactory.class);
	protected static UsernamePasswordCustomization credentials = mock(UsernamePasswordCustomization.class);
	
	public void setup() {
		reset(restFramework);
		reset(ddrsFactory);
		
		// Setup framework
		when(restFramework.getRestClient(anyString())).thenReturn(restClient);
		when(restFramework.getUsernamePassword(anyString(), anyString())).thenReturn(credentials);
		
		if(!Ddrs.isInitialized())
			Ddrs.Initialize(ddrsFactory, restFramework);
	}
}
