package Database;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

import Parks.Jobs.Park;

	
public class ParkDB {

	static final String JDBC_DRIVER =
		      "com.mariadb.jdbc.Driver";
		  
			static final String DB_URL =
		      "jdbc:mariadb://localhost:3306/GroupProjectDB";
		   
			static final String DB_USER = "root";
//			static final String DB_PASS = "";
			static final String DB_PASS = "1234";
		private static Connection conn;
		private List<Park> list;

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
		
		public List<Park> getPark() throws SQLException{
			if(conn==null){
				createConnection();
			}
			Statement stmt = null;
			String query = "select parkId, pUserName, name, street, city, state, zipcode"
					+ " from GroupProjectDB.Park ";
			list = new ArrayList<Park>();
			
			
			
			try{
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					int parkId = rs.getInt("parkId");
					String parkManagerName = rs.getString("pUserName");
					String name = rs.getString("name");
					String street = rs.getString("street");
					String city = rs.getString("city");
					String state = rs.getString("state");
					int zip = rs.getInt("zipcode");
					
					Park park = new Park(parkId, parkManagerName, name, street, city, state, zip);
					list.add(park);
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

	
	
}
