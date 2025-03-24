package package1;

import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<Job> storedJobs;

    public Server() {
        storedJobs = new ArrayList<>();
    }

    public void storeCompleteJob(Job job) {
        if (job.isCompleted()) {
            storedJobs.add(job);
            System.out.println("Stored completed job: " + job.getJobID());
        } else {
            System.out.println("Job not completed. Not storing.");
        }
    }

    public List<Job> listStoredJobs() {
        return storedJobs;
    }

    public List<String> getJobResults() {
        List<String> results = new ArrayList<>();
        for (Job job : storedJobs) {
            results.add("Job ID: " + job.getJobID() + 
                        " | Duration: " + job.getJobDuration() +
                        " | Completed: " + job.isCompleted());
        }
        return results;
    }
}
