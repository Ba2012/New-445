package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.JobUser;
import model.VolunteerUser;

public class JobUserDB {


static final String JDBC_DRIVER =
	      "com.mariadb.jdbc.Driver";
	  
		static final String DB_URL =
	      "jdbc:mariadb://localhost:3306/GroupProjectDB";
	   
		static final String DB_USER = "root";
//		static final String DB_PASS = "";
		static final String DB_PASS = "1234";
	private static Connection conn;
	private List<JobUser> list;

	/**
	 * Creates a sql connection to MySQL using the properties for
	 * userid, password and server information.
	 * @throws SQLException
	 */
	public static void createConnection() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		System.out.println("Connected to database");
	}
	//////////

	/**
	 * Returns a list of job objects from the database.
	 * @return list of jobs
	 * @throws SQLException
	 */
	public List<JobUser> getJobs() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select jobId, parkId, pUserName, name, description, status "
				+ "from GroupProjectDB.Jobs; ";

		list = new ArrayList<JobUser>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int jobId = rs.getInt("jobId");
				int parkId = rs.getInt("parkId");
				String PUserName = rs.getString("pUserName");
				String jobName = rs.getString("name");
				String jobDescription = rs.getString("description");
				String status = rs.getString("status");
				
				JobUser user = new JobUser(jobId, parkId, PUserName, jobName, jobDescription, status);
				list.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return list;
	}

	/**
	 * @param jobName
	 * @return list of users that contain the job name.
	 */
	public List<JobUser> getJob(String jobName) {
		List<JobUser> filterList = new ArrayList<JobUser>();
		try {
			list = getJobs();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (JobUser user : list) {
			if (user.getMyName().toLowerCase().contains(jobName.toLowerCase())) {
				filterList.add(user);
			}
		}
		return filterList;
	}

	/**
	 * Adds a new user to the table.
	 * @param theJob 
	 */
	public void addJob(int theJob, VolunteerUser theVol) {
		String sql = "INSERT INTO GroupProjectDB.VolunteerJoinJob(jobId,userId)  VALUES( ?, ?); ";
		PreparedStatement preparedStatement = null;			
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, theJob);
			preparedStatement.setInt(2, theVol.getMyUserId());			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}

	/**
	 * Modifies the movie information corresponding to the index in the list.
	 * @param row index of the element in the list
	 * @param columnName attribute to modify
	 * @param data value to supply
	 */
	public void updateJobs(int row, String columnName, Object data) {			
		JobUser user = list.get(row);
		int id = user.getMyJobId();
		String jobName = user.getMyName();
		String sql = "update GroupProjectDB.Volunteer set " + columnName + " = ?  where jobId = ? and jobName = ? ";
		System.out.println(sql);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			if (data instanceof String){
				preparedStatement.setString(1, (String) data);
				preparedStatement.setString(3, jobName);
				preparedStatement.setInt(2, id);
			}
			else if (data instanceof Integer)
				preparedStatement.setInt(1, (Integer) data);
			preparedStatement.setString(3, jobName);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 			
	}
	
	public void deleteUser(int jobId,String jobName, String jobDescription) throws SQLException {			
		String sql = "delete from GroupProjectDB.VolunteerJobs where jobId = ? and jobName = ? and jobDescription = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, jobId);
			preparedStatement.setString(2, jobName);
			preparedStatement.setString(3, jobDescription);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
	
}



