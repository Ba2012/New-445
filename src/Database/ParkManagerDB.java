package Database;




	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

import model.ParkManagerUser;

	
	public class ParkManagerDB {

	static final String JDBC_DRIVER =
		      "com.mariadb.jdbc.Driver";
		  
			static final String DB_URL =
		      "jdbc:mariadb://localhost:3306/GroupProjectDB";
		   
			static final String DB_USER = "root";
			static final String DB_PASS = "1234";
			
//		private static String userName = "root";//"ba2012"; //Change to yours
//		private static String password = "1234"; //piabMect";
//		private static String serverName = "localhost:3306//GroupProjectDB";//cssgate.insttech.washington.edu";
		private static Connection conn;
		private List<ParkManagerUser> list;

		/**
		 * Creates a sql connection to MySQL using the properties for
		 * userid, password and server information.
		 * @throws SQLException
		 */
		public static void createConnection() throws SQLException {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//			Properties connectionProps = new Properties();
//			connectionProps.put("user", userName);
//			connectionProps.put("password", password);
//
//			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/GroupProjectDB" + "/", connectionProps);

			System.out.println("Connected to database");
		}
	
	
		public List<ParkManagerUser> getManagers() throws SQLException{
			if(conn==null){
				createConnection();
			}
			Statement stmt = null;
			String query = "select pUserName, parkId, password, name"
					+ " from GroupProjectDB.ParkManagers ";
			list = new ArrayList<ParkManagerUser>();
			
			
			
			try{
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					String userName = rs.getString("pUserName");
					int id = rs.getInt("parkId");
					String pw = rs.getString("password");
					String name = rs.getString("name");
					
					ParkManagerUser user = new ParkManagerUser(userName, id, pw, name);
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

		public void addManagerUser(ParkManagerUser user) {
			
			String sql = "insert into GroupProjectDB.ParkManager values " 
					+ "(?,?,?,?); ";
			PreparedStatement preparedStatement = null;
			try{
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, user.getUserId());
				preparedStatement.setInt(2, user.getParId());
				preparedStatement.setString(3, user.getPw());
				preparedStatement.setString(4,  user.getName());
				
			} catch(SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			} 
		}
		
		public void getVolunteers() {
			
			 
		}
}



















