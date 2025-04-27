
package package1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Storing {

	private int owner_ID;
	private String vehi_info;
	private int Approx_residency;
	private String model_text;
	private int year;

	private int clientID;
	private int jobDuration;
	private String jobDeadline;

	// generate time stamp
	LocalDateTime timeStamp = LocalDateTime.now();
	DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
	String newDate = timeStamp.format(format);
	

	//--------------------------------
	VC vc = VC.getInstance();
	//------------------------
	
	public Storing() {
		
	}
	
	public Storing(int owner_ID, String vehi_info, String model_text, int year, int Approx_residency) {

		if (!validateOwnerID(owner_ID) || !validateYear(year) || vehi_info.isEmpty() || model_text.isEmpty()) {
			System.out.println("Error: Data validation failed. Entry not saved.");
			return;
		}

		try {
			int carID = vc.getCarID()-1;
			BufferedWriter writer = new BufferedWriter(new FileWriter("Owners.txt", true));
			writer.write("Owner ID: " + owner_ID + "\n");
			writer.write("Job ID: "+carID+"\n");
			writer.write("Vehicle Make: " + vehi_info + "\n");
			writer.write("Vehicle Model: " + model_text + "\n");
			writer.write("Vehicle Year: " + year + "\n");
			writer.write("Approximate Residency Time Of The Vehicle: " + Approx_residency + "\n");
			writer.write("Time Stamp: " + newDate + "\n\n");
			writer.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public Storing(int clientID, int jobDuration, String jobDeadline) {
		if (!validateClientID(clientID)) {
			System.out.println("Error: Data validation failed. Entry not saved.");
			return;
		}
		try {
			int jobID = vc.getJobID()-1;
			BufferedWriter writer = new BufferedWriter(new FileWriter("Client.txt", true));
			writer.write("Client ID: " + clientID + "\n");
			writer.write("Job ID: "+jobID+"\n");
			writer.write("Approximate Job Duration (hrs): " + jobDuration + "\n");
			writer.write("Job Deadline: " + jobDeadline + "\n");
			writer.write("Time Stamp: " + newDate + "\n\n");
			writer.close();
			

			

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	// Deletes from the client txt so that the job file wont add all the clinets back if one is deleted from the dataFrame
	public void deletefromClients(int clientID, int jobID) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Client.txt"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("tempFile.txt"));

			String line;
			while ((line = reader.readLine()) != null) {
				StringBuilder entry = new StringBuilder();
				entry.append(line).append("\n");
				for (int i = 0; i < 5; i++) {
					String nextLine = reader.readLine();
					if (nextLine != null) {
						entry.append(nextLine).append("\n");
					}
				}
				String[] entryLines = entry.toString().split("\n");
				if (entryLines.length >= 2) {
					int client_ID = Integer.parseInt(entryLines[0].split(": ")[1]);
					int job_ID = Integer.parseInt(entryLines[1].split(": ")[1]);
					if (client_ID == clientID && job_ID == jobID) {
						continue;
					}
				}				
				writer.write(entry.toString());
			}
			reader.close();
			writer.close();
			java.io.File original = new java.io.File("Client.txt");
			java.io.File tempFile = new java.io.File("tempFile.txt");
			if (original.delete()) {
				tempFile.renameTo(original);
			} else {
				System.out.println("Could not delete original file");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deletefromOwners(int ownerID, int carID) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Owners.txt"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("tempFile.txt"));

			String line;
			while ((line = reader.readLine()) != null) {
				StringBuilder entry = new StringBuilder();
				entry.append(line).append("\n");
				for (int i = 0; i < 7; i++) {
					String nextLine = reader.readLine();
					if (nextLine != null) {
						entry.append(nextLine).append("\n");
					}
				}
				String[] entryLines = entry.toString().split("\n");
				if (entryLines.length >= 2) {
					int owner_ID = Integer.parseInt(entryLines[0].split(": ")[1]);
					int car_ID = Integer.parseInt(entryLines[1].split(": ")[1]);
					if (owner_ID == ownerID && car_ID == carID) {
						continue;
					}
				}				
				writer.write(entry.toString());
			}
			reader.close();
			writer.close();
			java.io.File original = new java.io.File("Owners.txt");
			java.io.File tempFile = new java.io.File("tempFile.txt");
			if (original.delete()) {
				tempFile.renameTo(original);
			} else {
				System.out.println("Could not delete original file");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	
	// Validate Owner ID (Must be exactly 6 digits)
	private boolean validateOwnerID(int ownerID) {
		if (String.valueOf(ownerID).length() != 6) {
			System.out.println("Error: Owner ID must be exactly 6 digits!");
			return false;
		}
		return true;
	}

	// Validate Year (Must be a 4-digit number)
	private boolean validateYear(int year) {
		if (year < 1990 || year > 2026) { // Ensure year is in a reasonable range
			System.out.println("Error: Vehicle Year must be between 1990 and 2026!");
			return false;
		}
		return true;
	}

	// Validate Client ID(Must be exactly 6 digits)
	private boolean validateClientID(int clientID) {
		if (String.valueOf(clientID).length() != 6) {
			System.out.println("Error: Owner ID must be exactly 6 digits!");
			return false;
		}
		return true;
	}
}
