

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class VC {
	//private ArrayList<Car> avalCars = new ArrayList<>();
	static ArrayList<Job> jobList = new ArrayList<Job>();
	private int jobID = 1;
	private static VC inst;
	
	// public VC(int ownerID, String make, String model, int year ) {
	// Car car = new Car(make, model, ownerID, year);
	// }
	
	private VC() {
		
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
		
		  jobList.add(job);
	        
		
	}
	
	public  void jobCompletion() {
		 //System.out.println("Jobs in list: " + jobList);
		int count = 0;
		int[] ID = new int[getJobList().size()];
		int[] dur = new int[getJobList().size()];
		int[] completion = new int[getJobList().size()];
		int i = 0;
		
		for (Job curJob : getJobList()) {
			ID[i] = (curJob.getJobID());
			dur[i] = (curJob.getJobDuration());
			completion[i] = count + curJob.getJobDuration();
			count+=curJob.getJobDuration();
			i++;
		}

		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("VC.txt", false));
			writer.write("ID: " + Arrays.toString(ID) + "\n");
			writer.write("Duration: " + Arrays.toString(dur) + "\n");
			writer.write("Completion of jobs are: \n" + Arrays.toString(completion));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
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
}
