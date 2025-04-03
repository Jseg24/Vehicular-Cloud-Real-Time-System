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
import java.util.List;

import javax.swing.JOptionPane;

public class VC {
	
	static ArrayList<Car> carList = new ArrayList<Car>();
	static ArrayList<Job> jobList = new ArrayList<Job>();
	private int jobID = 1;
	private int carID = 1;
	private static VC inst;
	private Server server;
	private Job pendingJob = null;
	
	
	
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
	
	public void setPendingJob(Job job) {
		this.pendingJob = job;
	}
	//---------------------- Taking one job or one car at a time
	public boolean addCar(int own, String make, String model, int year, int res) {
		Car car = new Car(carID, own, make, model, year, res);
		if(checkCar()) {
			carList.add(car);
			carID++;
			saveJobsToFile();
			return true;
		}
		else
			return false;
	}
	//-------------------------
	public boolean addJob(int clientID, int duration) {

		LocalDateTime timeStamp = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
		String newDate = timeStamp.format(format);

		Job job = new Job(jobID, clientID, duration, newDate);
		
		if(checkJob()) {
		jobList.add(job);
		jobID++;
		saveJobsToFile();
		return true;
		}
		else {
			return false;
		}
		
	}
	
	//-------------------------------------------
	
	public boolean checkCar() {
		int n = JOptionPane.showConfirmDialog(null, "Accept Latest Car?" ,"",JOptionPane.YES_NO_OPTION);

		if (n == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Accepted");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Rejected");
			return false;
		}
	}
	
	public boolean checkJob() {
		int n = JOptionPane.showConfirmDialog(null, "Accept Latest Job Submissions?" ,"",JOptionPane.YES_NO_OPTION);

		if (n == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Accepted");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Rejected");
			return false;
		}
	}
	//--------------------------------------------------
	
	
	public void jobCompletion() {
	
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
	
	
		//private 
	static void saveJobsToFile() {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("jobs.txt"))) {
	        for (Job job : jobList) {
	        	System.out.println("Saving to file: " + job);
	            writer.write(job.toString() + "\n");
	        }
	        System.out.println("jobs.txt written.");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	void loadJobsFromFile() {
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
	
	//----------------------------------------------------
	public void carCompletion() {
		
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
	
	
		//private 
	static void saveCarsToFile() {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("jobs.txt"))) {
	        for (Job job : jobList) {
	        	System.out.println("Saving to file: " + job);
	            writer.write(job.toString() + "\n");
	        }
	        System.out.println("jobs.txt written.");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	void loadCarsFromFile() {
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
	//------------------------------------------------------------------
	
	
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
