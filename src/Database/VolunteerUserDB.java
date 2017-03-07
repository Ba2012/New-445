package Database;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	import model.VolunteerUser;
	
	
public class VolunteerUserDB {


	static final String JDBC_DRIVER =
		      "com.mariadb.jdbc.Driver";
		  
			static final String DB_URL =
		      "jdbc:mariadb://localhost:3306/GroupProjectDB";
		   
			static final String DB_USER = "root";
			static final String DB_PASS = "";
			
//		private static String userName = "root";//"ba2012"; //Change to yours
//		private static String password = "1234"; //piabMect";
//		private static String serverName = "localhost:3306//GroupProjectDB";//cssgate.insttech.washington.edu";
		private static Connection conn;
		private List<VolunteerUser> list;

		/**
		 * Creates a sql connection to MySQL using the properties for
		 * userid, password and server information.
		 * @throws SQLException
		 */
		public static void createConnection() throws SQLException {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			System.out.println("Connected to database");
		}

		/**
		 * Returns a list of volunteer objects from the database.
		 * @return list of volunteers
		 * @throws SQLException
		 */
		public List<VolunteerUser> getVolunteers() throws SQLException {
			if (conn == null) {
				createConnection();
			}
			Statement stmt = null;
			String query = "select userId, firstName, lastName, street, city, state, zipcode,"
					+ "email, phone "
					+ "from GroupProjectDB.volunteer ";

			list = new ArrayList<VolunteerUser>();
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					int id = rs.getInt("userId");
					String fName = rs.getString("firstName");
					String lName = rs.getString("lastName");
					String street = rs.getString("street");
					String city = rs.getString("city");
					String state = rs.getString("state");
					int zip = rs.getInt("zipcode");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					
					VolunteerUser user = new VolunteerUser(id, fName, lName, street, city, state, zip, email, phone);
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
		 * @param fname
		 * @return list of users that contain the first name.
		 */
		public List<VolunteerUser> getUser(String fname) {
			List<VolunteerUser> filterList = new ArrayList<VolunteerUser>();
			try {
				list = getVolunteers();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			for (VolunteerUser user : list) {
				if (user.getMyFName().toLowerCase().contains(fname.toLowerCase())) {
					filterList.add(user);
				}
			}
			return filterList;
		}

		/**
		 * Adds a new user to the table.
		 * @param user 
		 */
		public void addUser(VolunteerUser user) {
			String sql = "insert into GroupProjectDB.volunteer values " + "(?, ?, ?, ?, ?, ?, ?, ?, ?); ";
			PreparedStatement preparedStatement = null;			
			try {
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, user.getMyUserId());
				preparedStatement.setString(2, user.getMyFName());
				preparedStatement.setString(3, user.getMyLName());
				preparedStatement.setString(4, user.getMyStAddress());
				preparedStatement.setString(5, user.getMyCity());
				preparedStatement.setString(6, user.getMyState());
				preparedStatement.setInt(7, user.getMyZipcode());
				preparedStatement.setString(8, user.getMyEmail());
				preparedStatement.setString(9, user.getMyPhone());
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
		public void updateMovie(int row, String columnName, Object data) {			
			VolunteerUser user = list.get(row);
			int id = user.getMyUserId();
			String fname = user.getMyFName();
			String sql = "update GroupProjectDB.volunteer set " + columnName + " = ?  where userId= ? and lastName = ? ";
			System.out.println(sql);
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = conn.prepareStatement(sql);
				if (data instanceof String){
					preparedStatement.setString(1, (String) data);
					preparedStatement.setString(3, fname);
					preparedStatement.setInt(2, id);
				}
				else if (data instanceof Integer)
					preparedStatement.setInt(1, (Integer) data);
				preparedStatement.setString(3, fname);
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			} 			
		}
		
		public void deleteUser(int userId,String fname, String lname) throws SQLException {			
			String sql = "delete from GroupProjectDB.volunteer where userId = ? and firstName = ? and lastName = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, userId);
				preparedStatement.setString(2, fname);
				preparedStatement.setString(3, lname);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			} 
		}
		
		
		
	}


