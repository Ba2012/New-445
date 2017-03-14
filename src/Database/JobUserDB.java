package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Parks.Jobs.Jobs;
import model.JobUser;
import model.VolunteerUser;
/**
 * 
 * @author Bryce
 *
 */
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
	
	public List<JobUser> getAllJobsByVol(int volId) throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select J.jobId, parkId, pUserName, name, description "
				+ "from GroupProjectDB.Jobs J RIGHT OUTER JOIN GroupProjectDB.VolunteerJoinJob V "
				+ "ON J.jobId=V.jobId "
				+ "WHERE V.userId="+volId+"; ";

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
				
				JobUser user = new JobUser(jobId, parkId, PUserName, jobName, jobDescription,"");
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
	public void deleteJob(int jobId) throws SQLException {			
		String sql = "delete from GroupProjectDB.Jobs where jobId = "+jobId+";";
		String sql2 = "delete from GroupprojectDB.VolunteerJoinJob where jobId= "+jobId+";";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
			stmt.executeQuery(sql2);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
	public List<JobUser> getJobsPM(int managersParkID) throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select jobId, PM.parkId, PM.pUserName, J.name, description, status "
				+ "from GroupProjectDB.Jobs J"
				+ " join GroupProjectDB.ParkManager PM"
				+ " on J.parkId = PM.parkId"
				+ "where PM.parkId = "+ managersParkID + ";";

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
	
	public List<JobUser> getJobsVol(int jobId, int userId) throws SQLException{
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select parkId, J.pUserName name as Park Manager, J.name, description"
				+ "from GroupProjectDB.VolunteerJoinJob J"
				+ "where J.jobId = "+ jobId + " and J.userId = " + userId + ";";

		list = new ArrayList<JobUser>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int parkIds = rs.getInt("parkId");
				String PUserName = rs.getString("pUserName");
				String jobName = rs.getString("Park Manager");
				String jobDescription = rs.getString("description");
				
				JobUser user = new JobUser(0, parkIds, PUserName, jobName, jobDescription, "null");
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

	public void addManagerJob(Jobs theJob) {
		if(conn==null){
			try {
				createConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String sql = "insert into GroupProjectDB.Jobs values " 
				+ "(?,?,?,?,?,?); ";
		
		PreparedStatement preparedStatement;
		try{
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, theJob.getMyJobId());
			preparedStatement.setInt(2, theJob.getMyParkId());
			preparedStatement.setString(3, theJob.getMyPUserName());
			preparedStatement.setString(4,  theJob.getMyName());
			preparedStatement.setString(5,  theJob.getMyDescription());
			preparedStatement.setString(6,  theJob.getMyStatus());
			preparedStatement.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}

	public void deleteMyJob(int myUserId, int num) {
		String sql2 = "delete from GroupprojectDB.VolunteerJoinJob where jobId= "+num+" and userId = "+myUserId + " ;";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();

			stmt.executeQuery(sql2);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
		
	
	


}



