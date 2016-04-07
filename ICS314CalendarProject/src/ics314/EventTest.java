package ics314;

import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest {

	@Test
	public void testGetCmnt() {
		String comment = "Fun in the Sun!";
		Event beach = new Event();
		beach.setcomnt(comment); //input Fun in the Sun!
		assertEquals("Fun in the Sun!", beach.getComment()); //expect to receive Fun in the Sun!
	}
}
