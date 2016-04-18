package ics314;

import static org.junit.Assert.*;
import org.junit.Test;

public class GreatCircleTest {

	@Test
	public void testDist() {
		double computedDist = 0;
		computedDist = Interface.calculateGreatCircleDistance(35.6895, 139.69, 12.8797, 121.77);
		
		assertEquals(3108, computedDist, 100);
	}

}
