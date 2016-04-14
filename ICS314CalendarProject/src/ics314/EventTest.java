package ics314;

import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest {

	@Test
	public void testGetComment() {
		String comment = "COMMENT:Fun in the Sun!";
		Event beach = new Event();
		beach.setComment(comment); //input Fun in the Sun!
		assertEquals("COMMENT:Fun in the Sun!", beach.getComment()); //expect to receive Fun in the Sun!
		assertTrue(beach.getString().contains("COMMENT:Fun in the Sun!")); // expect the full event ics ha Fun in the Sun!
	}
	
	@Test
	public void testGetClassification() {
		Event beach = new Event();
		beach.setClassification("CLASS:PUBLIC");
		assertEquals("CLASS:PUBLIC", beach.getClassification()); //expect to receive PUBLIC
		assertTrue(beach.getString().contains("CLASS:PUBLIC")); // expect the full event ics PUBLIC
	}
}
