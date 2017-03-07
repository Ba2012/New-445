package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import javax.sql.rowset.JdbcRowSet;

//import com.sun.rowset.JdbcRowSetImpl;

public class UrbanParkUser {
   
	static final String JDBC_DRIVER =
      "com.mariadb.jdbc.Driver";
  
	static final String DB_URL =
      "jdbc:mariadb://localhost:3306/GroupProjectDB";
   
	static final String DB_USER = "root";
	static final String DB_PASS = "";
	private List<VolunteerUser> list;
	
	Connection conn;
	ResultSet rs;

	
	public UrbanParkUser() throws SQLException, ClassNotFoundException {

		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		System.out.println("Connection Established!");
		// create a Statement from the connection
		Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		String sql = "DROP TABLE IF EXISTS USER";
		statement.executeUpdate(sql);
		statement.executeUpdate("CREATE TABLE USER"
				+ "(userId INTEGER not NULL,"
				+ "firstName VARCHAR(30) not NULL,"
				+ "lastName VARCHAR(30) not NULL,"
				+ "street VARCHAR(30) not NULL,"
				+ "city VARCHAR(30) not NULL,"
				+ "state VARCHAR(30) not NULL,"
				+ "zipcode INTEGER (30) not NULL,"
				+ "email VARCHAR(30) not NULL,"
				+ "phone VARCHAR(30) not NULL,"
				+ "PRIMARY KEY (userId));");
		statement.executeUpdate("insert into USER values(106, 'John', 'Bako', '2112 s 90th st', 'Tacoma', 'WA', 98444, 'jsbako90@gmail.com', '2532823565')");
		statement.executeUpdate("insert into USER values(107, 'Bryce', 'Anderson', '2112 s 90th st', 'Tacoma', 'WA', 98444, 'BryceAndy90@gmail.com', '2532829945')");
		rs = statement.executeQuery("SELECT * FROM User");	
		
		// Check the column count
	    ResultSetMetaData md = rs.getMetaData();
	    System.out.println("Resultset has " + md.getColumnCount() + " cols.");
	}
   
	
//	public List<User> getUsers() throws SQLException {
//		if (conn == null) {
//			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//		}
//		Statement stmt = null;
//		String query = "select title, year, length, genre, studioName "
//				+ "from ba2012.Movies ";
//
//		list = new ArrayList<User>();
//		try {
//			stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				int id = rs.getInt("userId");
//				String fName = rs.getString("firstName");
//				String lName = rs.getString("lastName");
//				String streetAddress = rs.getString("street");
//				String city = rs.getString("city");
//				String state = rs.getString("state");
//				int zip = rs.getInt("zipcode");
//				String email = rs.getString("email");
//				String phone = rs.getString("phone");
//				
//				
//				User user = new User(id,fName,lName,streetAddress,city,state,zip,email,phone);
//				list.add(user);
//			}
//		} catch (SQLException e) {
//			System.out.println(e);
//		} finally {
//			if (stmt != null) {
//				stmt.close();
//			}
//		}
//		return list;
//	}

	
	
	public VolunteerUser create(VolunteerUser u) {
		String sql = "insert into GroupProjectDB.user values " + "(?, ?, ?, ?, ?, ?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, u.getMyUserId());
			preparedStatement.setString(2, u.getMyFName());
			preparedStatement.setString(3, u.getMyLName());
			preparedStatement.setString(4, u.getMyStAddress());
			preparedStatement.setString(5, u.getMyCity());
			preparedStatement.setString(6, u.getMyState());
			preparedStatement.setInt(7, u.getMyZipcode());
			preparedStatement.setString(8, u.getMyEmail());
			preparedStatement.setString(9, u.getMyPhone());
			preparedStatement.executeUpdate();
			rs = preparedStatement.executeQuery("SELECT * FROM User");

//		} catch (SQLException ex) {
//			try {
////				((Connection) rs).rollback();
//	            u = null;
	        } catch (SQLException e) {
	
	        	e.printStackTrace();
	        }
	        	
	    
	    return u;
	}

	public VolunteerUser update(VolunteerUser u) {
		String sql = "update GroupProjectDB.user set value" + "(?, ?, ?, ?, ?, ?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, u.getMyUserId());
			preparedStatement.setString(2, u.getMyFName());
			preparedStatement.setString(3, u.getMyLName());
			preparedStatement.setString(4, u.getMyStAddress());
			preparedStatement.setString(5, u.getMyCity());
			preparedStatement.setString(6, u.getMyState());
			preparedStatement.setInt(7, u.getMyZipcode());
			preparedStatement.setString(8, u.getMyEmail());
			preparedStatement.setString(9, u.getMyPhone());
			preparedStatement.executeUpdate();
			rs = preparedStatement.executeQuery("SELECT * FROM User");

		} catch (SQLException ex) {
		
			ex.printStackTrace();
		}
		return u;
		
	}

	public void delete(VolunteerUser user) {

		//list.remove(user);
		String sql = "delete from GroupProjectDB.user where userId = ? ";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, user.getMyUserId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
		}

	}

	public VolunteerUser moveFirst() {
		VolunteerUser u = new VolunteerUser();
		try {
			rs.first();
			u.setMyUserId(rs.getInt("UserId"));
			u.setMyFName(rs.getString("firstName"));
			u.setMyLName(rs.getString("lastName"));
			u.setMyStAddress(rs.getString("Street"));
			u.setMyCity(rs.getString("City"));
			u.setMyState(rs.getString("state"));
			u.setMyZipcode(rs.getInt("Zipcode"));
			u.setMyEmail(rs.getString("email"));
			u.setMyPhone(rs.getString("phone"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return u;
   }

   public VolunteerUser moveLast() {
      VolunteerUser u = new VolunteerUser();
      try {
    	  rs.last();
		  u.setMyUserId(rs.getInt("UserId"));
		  u.setMyFName(rs.getString("firstName"));
		  u.setMyLName(rs.getString("lastName"));
		  u.setMyStAddress(rs.getString("Street"));
		  u.setMyCity(rs.getString("City"));
		  u.setMyState(rs.getString("state"));
		  u.setMyZipcode(rs.getInt("Zipcode"));
		  u.setMyEmail(rs.getString("email"));
		  u.setMyPhone(rs.getString("phone"));

      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return u;
   }

   public VolunteerUser moveNext() {
	   VolunteerUser u = new VolunteerUser();
	   try {
		   if (rs.next() == false) {
			   rs.previous();
		   }   
		   u.setMyUserId(rs.getInt("UserId"));
		   u.setMyFName(rs.getString("firstName"));
		   u.setMyLName(rs.getString("lastName"));
		   u.setMyStAddress(rs.getString("Street"));
		   u.setMyCity(rs.getString("City"));
		   u.setMyState(rs.getString("state"));
		   u.setMyZipcode(rs.getInt("Zipcode"));
		   u.setMyEmail(rs.getString("email"));
		   u.setMyPhone(rs.getString("phone"));

	   } catch (SQLException ex) {
		   ex.printStackTrace();
	   }
	   return u;
   }

   public VolunteerUser movePrevious() {
	   VolunteerUser u = new VolunteerUser();
	   try {
		   if (rs.previous() == false) {
			   rs.next();
		   }	   
		   u.setMyUserId(rs.getInt("UserId"));
		   u.setMyFName(rs.getString("firstName"));
		   u.setMyLName(rs.getString("lastName"));
		   u.setMyStAddress(rs.getString("Street"));
		   u.setMyCity(rs.getString("City"));
		   u.setMyState(rs.getString("state"));
		   u.setMyZipcode(rs.getInt("Zipcode"));
		   u.setMyEmail(rs.getString("email"));
		   u.setMyPhone(rs.getString("phone"));

	   } catch (SQLException ex) {
		   ex.printStackTrace();
	   }
	   return u;
   }

   public VolunteerUser getCurrent() {
	   VolunteerUser u = new VolunteerUser();
	   try {
		   rs.moveToCurrentRow();
		   u.setMyUserId(rs.getInt("UserId"));
		   u.setMyFName(rs.getString("firstName"));
		   u.setMyLName(rs.getString("lastName"));
		   u.setMyStAddress(rs.getString("Street"));
		   u.setMyCity(rs.getString("City"));
		   u.setMyState(rs.getString("state"));
		   u.setMyZipcode(rs.getInt("Zipcode"));
		   u.setMyEmail(rs.getString("email"));
		   u.setMyPhone(rs.getString("phone"));
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return u;
   }
   
//   // to close connection
//   rs.close();
//   st.close();
//   conn.close();
}