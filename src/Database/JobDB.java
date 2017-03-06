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

public class JobDB {

	static final String JDBC_DRIVER = "com.mariadb.jdbc.Driver";

	static final String DB_URL = "jdbc:mariadb://localhost:3306/GroupProjectDB";

	static final String DB_USER = "root";
	static final String DB_PASS = "";

	// private static String userName = "root";//"ba2012"; //Change to yours
	// private static String password = "1234"; //piabMect";
	// private static String serverName =
	// "localhost:3306//GroupProjectDB";//cssgate.insttech.washington.edu";
	private static Connection conn;
	private List<JobUser> list;

	/**
	 * Creates a sql connection to MySQL using the properties for userid,
	 * password and server information.
	 * 
	 * @throws SQLException
	 */
	public static void createConnection() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		// Properties connectionProps = new Properties();
		// connectionProps.put("user", userName);
		// connectionProps.put("password", password);
		//
		// conn =
		// DriverManager.getConnection("jdbc:mariadb://localhost:3306/GroupProjectDB"
		// + "/", connectionProps);

		System.out.println("Connected to database");
	}

	
	public List<JobUser> getJobs() throws SQLException{
		if(conn==null){
			createConnection();
		}
		Statement stmt = null;
		String query = "select jobId, parkId, PUserName, jobName, jobDescription, status"
				+ " from GroupProjectDB.Jobs ";
		list = new ArrayList<JobUser>();
		
		
		
		try{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int jobId = rs.getInt("jobId");
				int parkId = rs.getInt("parkId");
				String PUserName = rs.getString("PUserName");
				String jobName = rs.getString("jobName");
				String jobDescription = rs.getString("jobDescription");
				String status = rs.getString("status");
				
				
				JobUser job = new JobUser(jobId, parkId, PUserName, jobName, jobDescription, status);
				list.add(job);
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
	public void addJob(Jobs theJob) {
		String sql = "insert into GroupProjectDB.Jobs values " 
				+ "(?,?,?,?,?,?); ";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, theJob.getMyJobId());
			preparedStatement.setInt(2, theJob.getMyParkId());
			preparedStatement.setString(3, theJob.getMyPUserName());
			preparedStatement.setString(4,  theJob.getMyName());
			preparedStatement.setString(4,  theJob.getMyDescription());
			preparedStatement.setString(4,  theJob.getMyStatus());
			
		} catch(SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
	
}
