

public class Job {
	private int jobID;
	private int clientID;
	private int jobDuration;
	private String date;
	
	public Job(int jobID, int clientID, int jobDuration, String date) {
		this.jobID = jobID;
		this.clientID = clientID;
		this.jobDuration = jobDuration;
		this.date = date;
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public int getJobDuration() {
		return jobDuration;
	}

	public void setJobDuration(int jobDuration) {
		this.jobDuration = jobDuration;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
