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
		
		Interface next = new Interface();
		Event e = new Event();
		next.askForClassification(e);
		next.askForDescription(e);
		next.getDStamp(e);
		next.askForTimeZone(e);
		next.askForStartTime(e);
		next.askForEndTime(e);
		next.askForLocation(e);
		
		try {
			String fileName = next.name + ".ics";

			File file = new File(fileName);
			
			//Check if file was created
			if (file.createNewFile()) {
				System.out.println("This .ics file has been created!");
			} else {
				System.out.println("This .ics file already exists.");
			}

			// String to write to the file
			String toWrite = "BEGIN:VCALENDAR\r\n" 
					+ e.getVersion()
					+ e.getprodid()
					+ "BEGIN:VEVENT\r\n"
					+ e.getuid()
					+ e.getstmp()
					+ e.getstrt() 
					+ e.getnd()
					+ e.getsm()
					+ e.getgeo()
					+ "END:VEVENT\r\n"
					+ "END:VCALENDAR\r\n";
			
			//Create file writer & buffer writer
			FileWriter fWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			
			//Write the file
			bWriter.write(toWrite);
			
			//Close the buffer
			bWriter.close();

		} catch (IOException err) {
			err.printStackTrace();
		}
	}
}
