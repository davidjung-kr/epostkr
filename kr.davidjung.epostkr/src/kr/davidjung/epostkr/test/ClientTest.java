package kr.davidjung.epostkr.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import kr.davidjung.epostkr.Client;

class ClientTest {
    
	@Test
	void testClient() throws Exception {		
		Client client = new Client("http://httpbin.org/get");
		client.get();
		assertEquals(200, client.getStatus());
	}
	
	@Test
	void testClientPost() throws Exception {		
		Client client = new Client("http://httpbin.org/post");
	}
}