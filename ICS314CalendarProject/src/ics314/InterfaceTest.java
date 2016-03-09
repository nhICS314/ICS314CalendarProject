package ics314;

import static org.junit.Assert.*;

import org.junit.Test;

public class InterfaceTest {

	@Test
	public void testFindTimeZone() {
		String tzid = null;
		tzid = Interface.findTimeZone("HST");
		assertEquals("=Pacific/Honolulu:", tzid);
		
		assertEquals("second attempt", "=Pacific/Honolulu:", tzid);
	}
	

}
