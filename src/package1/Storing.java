package package1;

import java.io.BufferedWriter;
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

	public Storing(int owner_ID, String vehi_info, String model_text, int year, int Approx_residency) {

		if (!validateOwnerID(owner_ID) || !validateYear(year) || vehi_info.isEmpty() || model_text.isEmpty()) {
			System.out.println("Error: Data validation failed. Entry not saved.");
			return;
		}

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter("Owners.txt", true));
			writer.write("Owner ID: " + owner_ID + "\n");
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

			BufferedWriter writer = new BufferedWriter(new FileWriter("Client.txt", true));
			writer.write("Client ID: " + clientID + "\n");
			writer.write("Approximate Job Duration (hrs): " + jobDuration + "\n");
			writer.write("Job Deadline: " + jobDeadline + "\n");
			writer.write("Time Stamp: " + newDate + "\n\n");
			writer.close();

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
