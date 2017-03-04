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

public class JobDB {

	static final String JDBC_DRIVER = "com.mariadb.jdbc.Driver";

	static final String DB_URL = "jdbc:mariadb://localhost:3306/GroupProjectDB";

	static final String DB_USER = "root";
	static final String DB_PASS = "1234";

	// private static String userName = "root";//"ba2012"; //Change to yours
	// private static String password = "1234"; //piabMect";
	// private static String serverName =
	// "localhost:3306//GroupProjectDB";//cssgate.insttech.washington.edu";
	private static Connection conn;
	private List<Jobs> list;

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

	
	public List<Jobs> getJobs() throws SQLException{
		if(conn==null){
			createConnection();
		}
		Statement stmt = null;
		String query = "select jobId, parkId, pUserName, name, discription, status"
				+ " from GroupProjectDB.Jobs ";
		list = new ArrayList<Jobs>();
		
		
		
		try{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int jobId = rs.getInt("jobId");
				int parkId = rs.getInt("parkId");
				String parkManagerName = rs.getString("pUserName");
				String name = rs.getString("name");
				String discription = rs.getString("discription");
				String status = rs.getString("status");
				
				
				Jobs job = new Jobs(jobId, parkId, parkManagerName, name, discription, status);
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
	
}
