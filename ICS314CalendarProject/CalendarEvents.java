package ics314;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Nicole, Sammy, and Joseph
 * 
 */
class CalendarEvents {
	public static void main(String[] args) {
		boolean addEvent = true;
		Calendar c = new Calendar();
		Event[] tempEvents = new Event[1000];
		Event first = new Event();

		Interface next = new Interface();

		next.askForCalendarName(c);

		int eventIndex = 0;
		while (addEvent) {
			Event e = new Event();
			next.askForClassification(e);
			next.askForDescription(e);
			next.getDStamp(e);
			next.askForTimeZone(e);
			next.askForStartTime(e);
			next.askForEndTime(e);
			next.askForLocation(e);
			// Compute Great Circle Distance if multiple events
			if(eventIndex >= 1) {
				next.greatCircleDist(first, e, c); //set the comment to first event
			}
			tempEvents[eventIndex] = e;
			if(eventIndex == 0)
				first = e;
			addEvent = next.askForAddAnother();
			eventIndex++;
		}// end while

		c.setEvents(tempEvents);
		Event[] events = c.getEvents();

		try {

			String fileName = c.getName() + ".ics";
			File file = new File(fileName);

			// Check if file was created
			if (file.createNewFile()) {
				System.out.println("This .ics file has been created!");
			} else {
				System.out.println("This .ics file already exists.");
			}
			
			String calWrite = "BEGIN:VCALENDAR\r\n";
			calWrite += c.getVersion();
			calWrite += c.getProdid();
			String eventsWrite = "";
			for (int i = 0; i < eventIndex; i++) {
				// String to write to the file
				eventsWrite += "BEGIN:VEVENT\r\n" + events[i].getcla() + events[i].getuid()
						+ events[i].getstmp() + events[i].getstrt() + events[i].getnd()
						+ events[i].getsm() + events[i].getgeo() + events[i].getcomnt() + "END:VEVENT\r\n";
			}// end for
			calWrite = calWrite + eventsWrite + "END:VCALENDAR\r\n";

			// Create file writer & buffer writer
			FileWriter fWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bWriter = new BufferedWriter(fWriter);

			// Write the file
			bWriter.write(calWrite);

			// Close the buffer
			bWriter.close();

		} catch (IOException err) {
			err.printStackTrace();
		}

	}
}
