package package1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List

public class VC {
	//private ArrayList<Car> avalCars = new ArrayList<>();
	static ArrayList<Job> jobList = new ArrayList<Job>();
	private int jobID = 1;
	private static VC inst;
	private Server server;
	
	// public VC(int ownerID, String make, String model, int year ) {
	// Car car = new Car(make, model, ownerID, year);
	// }
	
	private VC() {
		 server = new Server();
		 loadJobsFromFile();
		}
	
	public static VC getInstance() {
        if (inst == null) {
            inst = new VC();  
        }
        return inst;
    }

	public void addJob(int clientID, int duration) {

		LocalDateTime timeStamp = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
		String newDate = timeStamp.format(format);

		Job job = new Job(jobID, clientID, duration, newDate);
		jobList.add(job);
		System.out.print("Job created");
		jobID++;
		saveJobsToFile(); 
	       
		
	}
	
	public void jobCompletion() {
		// System.out.println("Jobs in list: " + jobList);
		int count = 0;
		List<Integer> ID = new ArrayList<>();
		List<Integer> clientID = new ArrayList<>();
		List<Integer> dur = new ArrayList<>();
		List<Integer> completion = new ArrayList<>();
		int i = 0;

		for (Job curJob : getJobList()) {
			ID.add(curJob.getJobID());
			clientID.add(curJob.getClientID());
			dur.add(curJob.getJobDuration());
			count += curJob.getJobDuration();
			completion.add(count);	
		}

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("VC.txt", false));
			writer.write("Job ID: " + ID + "\n");
			writer.write("Client ID: " + clientID + "\n");
			writer.write("Duration: " + dur + "\n");
			writer.write("Completion time for jobs are: \n" + completion);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void saveJobsToFile() {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("jobs.txt"))) {
	        for (Job job : jobList) {
	            writer.write(job.toString() + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	private void loadJobsFromFile() {
		 jobList.clear();
	    try (BufferedReader reader = new BufferedReader(new FileReader("jobs.txt"))) {
	        String line;
	        //Read each line
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            
	            if (parts.length >= 4) {
	            	//parse for info
	                int id = Integer.parseInt(parts[0]);
	                int client = Integer.parseInt(parts[1]);
	                int dur = Integer.parseInt(parts[2]);
	                String date = parts[3];
	                //recreate job object from saved data
	                Job job = new Job(id, client, dur, date);
	                jobList.add(job);
	                //ensures jobID is always one more than the highest ID loaded from the file and updates jobID so no duplicates
	                jobID = Math.max(jobID, id + 1);
	            }
	        }
	        System.out.println("Jobs loaded from file.");
	    } catch (IOException e) {
	        System.out.println("No previous jobs found.");
	    }
	}

	
	/*
	public ArrayList<Car> getAvalCars() {
		return avalCars;
	}
	
	public void setAvalCars(ArrayList<Car> avalCars) {
		this.avalCars = avalCars;
	}
	*/
	
	public ArrayList<Job> getJobList() {
		return jobList;
	}

	public static void setJobList(ArrayList<Job> jobList) {
		VC.jobList = jobList;
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	
	public void markJobAsCompleted(int jobID) {
	    for (Job job : jobList) {
	        if (job.getJobID() == jobID) {
	            job.setCompleted(true);
	            server.storeCompleteJob(job);
	            break;
	        }
	    }
	}

	public ArrayList<String> getServerResults() {
	    return new ArrayList<>(server.getJobResults());
	}

}
