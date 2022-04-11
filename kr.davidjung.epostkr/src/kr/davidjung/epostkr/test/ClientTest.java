package kr.davidjung.epostkr.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import kr.davidjung.epostkr.Client;

class ClientTest {
    
	@Test
	void testClient() {		
		Client client = new Client("http://httpbin.org/get");
		client.actionGet();
		assertEquals(200, client.getStatus());
	}
}
