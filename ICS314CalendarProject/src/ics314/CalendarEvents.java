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
		try {
			String fileName = "mynewfile.ics";

			File file = new File(fileName);
			
			//Check if file was created
			if (file.createNewFile()) {
				System.out.println("The .ics file has been created!");
			} else {
				System.out.println("The .ics file already exists.");
			}

			// String to write to the file
			String toWrite = "BEGIN:VCALENDAR\r\n" 
					+ "VERSION:2.0\r\n"
					+ "PRODID:-//hacksw/handcal//NONSGML v1.0//EN\r\n"
					+ "BEGIN:VEVENT\r\n"
					+ "UID:19970610T172345Z-AF23B2@example.com\r\n"
					+ "DTSTAMP:19970610T172345Z\r\n"
					+ "DTSTART:19970714T170000Z\r\n" 
					+ "DTEND:19970715T040000Z\r\n"
					+ "SUMMARY:Beach Day\r\n" 
					+ "END:VEVENT\r\n"
					+ "END:VCALENDAR\r\n";
			
			//Create file writer & buffer writer
			FileWriter fWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			
			//Write the file
			bWriter.write(toWrite);
			
			//Close the buffer
			bWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
