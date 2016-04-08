package ics314;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author Nicole, Sammy, and Joseph
 * 
 */


class CalendarEvents {
	public static void main(String[] args) throws IOException {
		Interface next = new Interface();
		boolean updateDistances = next.askToUpdateDistances();
						
		if(updateDistances){
			updateDistances();
		} else {
			writeEventFiles();
		}


	}
	private static void writeEventFiles() throws IOException {
		boolean addEvent = true;
		Interface next = new Interface();

		while (addEvent) {
			Event e = new Event();
			next.askForFileName(e);
			next.askForClassification(e);
			next.askForDescription(e);
			next.getDStamp(e);
			next.askForTimeZone(e);
			next.askForStartTime(e);
			next.askForEndTime(e);
			next.askForLocation(e);
			
			
			writeIcsFile(e);
			
			addEvent = next.askForAddAnother();
		}// end while		
	}
	private static void writeIcsFile(Event e) throws IOException {
			String fileName = e.getFileNameWithExtension();
			File file = new File(fileName);

			// Check if file was created
			if (file.createNewFile()) {
				System.out.println("This file '" + fileName + "' has been created!");
			} else {
				System.out.println("This file '" + fileName + "' already exists, it will be updated.");
			}
			
			String calWrite = "BEGIN:VCALENDAR\r\n";
			calWrite +=		"VERSION:2.0\r\n";
			calWrite +=	 "PRODID://ICS Team Project/Calendar/v1.0//EN\r\n";
			calWrite = calWrite + e.getString() + "END:VCALENDAR\r\n";

			// Create file writer & buffer writer
			FileWriter fWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bWriter = new BufferedWriter(fWriter);

			// Write the file
			bWriter.write(calWrite);

			// Close the buffer
			bWriter.close();


	}
	public static void updateDistances() throws IOException{

		Interface next = new Interface();
		///Users/niki/git/ICS314CalendarProject/ICS314CalendarProject for the demo this is the directory
		ArrayList<File> icsFiles = next.askForDirectoryOfIcsFiles();
		Event[] eventArray = new Event[icsFiles.size()];
		for (int i = 0; i < icsFiles.size(); i++){
			File currentIcsFile = icsFiles.get(i);
			Event e = new Event (currentIcsFile);
			eventArray[i]=e;
		}
	
		Arrays.sort(eventArray); // uses the comparable in Event to sort

		// Compute Great Circle Distance if multiple events
		for (int i = 1; i< eventArray.length;i++){
			Event previousEvent = eventArray[i-1];
			Event currentEvent = eventArray[i];
			
			 //set the comment to first event
			next.greatCircleDist(previousEvent, currentEvent);
			
			writeIcsFile(previousEvent);
		}
				
	}
}
