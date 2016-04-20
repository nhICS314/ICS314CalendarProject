package ics314;

import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest {
	
	@Test
	public void testGetClassification() {
		Event beach = new Event();
		beach.setClassification("CLASS:PUBLIC");
		assertEquals("CLASS:PUBLIC", beach.getClassification()); //expect to receive PUBLIC
		assertTrue(beach.getString().contains("CLASS:PUBLIC")); // expect the full event ics PUBLIC
	}
	
	@Test
	public void testGetComment() {
		String comment = "COMMENT:Fun in the Sun!";
		Event beach = new Event();
		beach.setComment(comment); //input Fun in the Sun!
		assertEquals("COMMENT:Fun in the Sun!", beach.getComment()); //expect to receive Fun in the Sun!
		assertTrue(beach.getString().contains("COMMENT:Fun in the Sun!")); // expect the full event ics has Fun in the Sun!
	}
	
	@Test
	public void testGetTimezone() {
		String timezone = "=Pacific/Honolulu:";
		Event beach = new Event();
		beach.setTimezone(timezone); //input =Pacific/Honolulu:
		assertEquals("=Pacific/Honolulu:", beach.getTimezone()); //expect to receive =Pacific/Honolulu:
	}
	
	@Test
	public void testGetId() {
		String id = "1";
		Event beach = new Event();
		beach.setId(id); //input 1
		assertEquals("1", beach.getId()); //expect to receive 1
	}

	@Test
	public void testGetStart() {
		String start = "DTSTART;TZID=Pacific/Honolulu:20160405T144000";
		Event beach = new Event();
		beach.setStart(start); //input DTSTART;TZID=Pacific/Honolulu:20160405T144000
		assertEquals("DTSTART;TZID=Pacific/Honolulu:20160405T144000", beach.getStart()); //expect to receive DTSTART;TZID=Pacific/Honolulu:20160405T144000
	}

	@Test
	public void testGetEnd() {
		String end = "DTEND;TZID=Pacific/Honolulu:19940405T145000";
		Event beach = new Event();
		beach.setEnd(end); //input DTEND;TZID=Pacific/Honolulu:19940405T145000
		assertEquals("DTEND;TZID=Pacific/Honolulu:19940405T145000", beach.getEnd()); //expect to receive DTEND;TZID=Pacific/Honolulu:19940405T145000
	}

	@Test
	public void testGetTimestamp() {
		String timestamp = "DTSTAMP:20160419T211059Z";
		Event beach = new Event();
		beach.setTimeStamp(timestamp); //input DTSTAMP:20160419T211059Z
		assertEquals("DTSTAMP:20160419T211059Z", beach.getTimeStamp()); //expect to receive DTSTAMP:20160419T211059Z
	}
	
	@Test
	public void testGetSummary() {
		String summary = "SUMMARY:name";
		Event beach = new Event();
		beach.setSummary(summary); //input SUMMARY:name
		assertEquals("SUMMARY:name", beach.getSummary()); //expect to receive SUMMARY:name
	}

	@Test
	public void testGetGeograph() {
		String geograph = "GEO:21.301897;-157.82515";
		Event beach = new Event();
		beach.setGeograph(geograph); //input GEO:21.301897;-157.82515
		assertEquals("GEO:21.301897;-157.82515", beach.getGeograph()); //expect to receive GEO:21.301897;-157.82515
	}

}
