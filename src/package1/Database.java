package package1;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;

public class Database {
	
	private VC vc = VC.getInstance();

	static Connection connection = null;
	//this part is the address and name of your database server: jdbc:mysql://localhost:3306/VC3
	//this part of the string is for time adjustment: ?useTimezone=true&serverTimezone=UTC
	static String url = "jdbc:mysql://localhost:3306/vcrts?useTimezone=true&serverTimezone=UTC";
	static String username = "root";
	static String password = "Sport500";

	public void clientData(int clientID, int jobDuration, String jobDeadline) {
		
	    LocalDateTime timeStamp = LocalDateTime.now();
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
	    String newDate = timeStamp.format(format);

	    try {
	    	int jobID = vc.getJobID()-1;
	        connection = DriverManager.getConnection(url, username, password);
	        String sql = "INSERT INTO client_task (Client_ID, Job_ID, Approximate_Job, Job_DeadLine, Time_Stamp) VALUES ("+clientID+","+jobID+", "+jobDuration+", '"+jobDeadline+ "', '"+newDate+ "')";
	        Statement statement = connection.createStatement();
	        int row = statement.executeUpdate(sql);
	        if (row > 0)
	            System.out.println("Data was inserted!");

	        connection.close();
			
		} catch (SQLException e) {
			System.out.print("Error"+e.getMessage());
			e.getMessage();

		}
	}
	
	
	public void ownerData(int owner_ID, String vehi_info, String model_text, int year, int Approx_residency) {
		
	    LocalDateTime timeStamp = LocalDateTime.now();
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
	    String newDate = timeStamp.format(format);

	    try {
	    	int carID = vc.getCarID()-1;
	        connection = DriverManager.getConnection(url, username, password);
	        String sql = "INSERT INTO owner (Owner_ID, Car_ID, Vehicle_Make, Vehicle_Model, Vehicle_Year, Approximate_Residency, Time_Stamp) VALUES ("+ owner_ID + ","+carID+", '" + vehi_info + "', '" + model_text + "', " + year + ", " + Approx_residency + ", '" + newDate + "')";
	        Statement statement = connection.createStatement();
	        int row = statement.executeUpdate(sql);
	        if (row > 0)
	            System.out.println("Data was inserted!");

	        connection.close();
			
		} catch (SQLException e) {
			System.out.print("Error"+e.getMessage());

		}
	}
	
	public void updateClientData(int clientID, int jobDuration, int jobID) {
	    try {
	        connection = DriverManager.getConnection(url, username, password);
	        String sql = "UPDATE client_task SET Approximate_Job = ? WHERE Client_ID = ? AND Job_ID = ?";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setInt(1, jobDuration);
	        ps.setInt(2, clientID);
	        ps.setInt(3, jobID);
	        int row = ps.executeUpdate();
	        if (row > 0)
	            System.out.println("Client data updated.");
	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Error updating client data: " + e.getMessage());
	    }
	}

	public void updateOwnerData(int OwnerID, String make, String model, int year, int resTime, int carID) {
	    try {
	        connection = DriverManager.getConnection(url, username, password);
	        String sql = "UPDATE owner SET Vehicle_Make = ?, Vehicle_Model = ?, Vehicle_Year = ?, Approximate_Residency = ? WHERE Owner_ID = ? AND Car_ID = ?";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setString(1, make);
	        ps.setString(2, model);
	        ps.setInt(3, year);
	        ps.setInt(4, resTime);
	        ps.setInt(5, OwnerID);
	        ps.setInt(6, carID);
	        int row = ps.executeUpdate();
	        if (row > 0)
	            System.out.println("Owner data updated.");
	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Error updating owner data: " + e.getMessage());
	    }
	}

	public void deleteCarData(int OwnerID, int carID) {
	    try {
	        connection = DriverManager.getConnection(url, username, password);
	        String sql = "DELETE FROM owner WHERE Owner_ID = ? AND Car_ID = ?";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setInt(1, OwnerID);
	        ps.setInt(2, carID);
	        int rows = ps.executeUpdate();
	        if (rows > 0)
	            System.out.println("Owner data updated.");
	        connection.close();
	        
	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Error deleting car from database: " + e.getMessage());
	    }
	}
	public void deleteJobData(int clientID, int jobID) {
	    try {
	        connection = DriverManager.getConnection(url, username, password);
	        String sql = "DELETE FROM client_task WHERE Client_ID = ? AND Job_ID = ?";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setInt(1, clientID);
	        ps.setInt(2, jobID);
	        int rows = ps.executeUpdate();
	        if (rows > 0)
	            System.out.println("Owner data updated.");
	        connection.close();
	       
	    } catch (SQLException e) {
	        System.out.println("Error deleting job from database: " + e.getMessage());
	    }
	}

}