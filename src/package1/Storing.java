package package1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storing {

	private String owner_ID;
	private String vehi_info;
	private String Approx_residency;
	private String model_text;
	private String year_text;
	
	private String client_text;
	private String jobDuration_text; 
	private String jobDeadline_text;
	
	
	LocalDateTime timeStamp = LocalDateTime.now();
	
	DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
	String newDate = timeStamp.format(format);
	
	
	public Storing(String owner_ID, String vehi_info, String model_text, String year_text, String Approx_residency) {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("Owners.txt", true));
			writer.write("Owner ID: " + owner_ID +"\n");
			writer.write("Vehicle Make: " + vehi_info +"\n");
			writer.write("Vehicle Model: " + model_text +"\n");
			writer.write("Vehicle Year: " + year_text +"\n");
			writer.write("Approximate Residency Time Of The Vehicle: " + Approx_residency+"\n");
			writer.write("Time Stamp: "+ newDate +"\n\n");
			writer.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public Storing(String client_text, String jobDuration_text, String jobDeadline_text) {
		
try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("Client.txt", true));
			writer.write("Client ID: " + client_text +"\n");
			writer.write("Approximate Job Duration: " + jobDuration_text +"\n");
			writer.write("Job Deadline: " + jobDeadline_text+"\n");
			writer.write("Time Stamp: "+ newDate+ "\n\n");
			writer.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
}
