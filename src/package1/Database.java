package package1;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;

public class Database {

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
	        connection = DriverManager.getConnection(url, username, password);
	        String sql = "INSERT INTO client_task (Client_ID, Approximate_Job, Job_DeadLine, Time_Stamp) VALUES ("+clientID+", "+jobDuration+", '"+jobDeadline+ "', '"+newDate+ "')";
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
	        connection = DriverManager.getConnection(url, username, password);
	        String sql = "INSERT INTO owner (Owner_ID, Vehicle_Make, Vehicle_Model, Vehicle_Year, Approximate_Residency, Time_Stamp) VALUES ("+ owner_ID + ", '" + vehi_info + "', '" + model_text + "', " + year + ", " + Approx_residency + ", '" + newDate + "')";
	        Statement statement = connection.createStatement();
	        int row = statement.executeUpdate(sql);
	        if (row > 0)
	            System.out.println("Data was inserted!");

	        connection.close();
			
		} catch (SQLException e) {
			System.out.print("Error"+e.getMessage());

		}
	}
	
	
	
}
