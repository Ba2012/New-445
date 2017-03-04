package authentication;

import java.sql.SQLException;

import Database.VolunteerUserDB;
import model.ParkManagerUser;
import model.VolunteerUser;



public class Login {
	
	private static boolean isManager;
	
	private static boolean isVolunteer;
	public static VolunteerUser myVolUser;
	public ParkManagerUser myPMUser;
	public static VolunteerUserDB myVDB;
	
	public Login() {
		setManager(isManager);
		setVolunteer(isVolunteer);
	}
	 
    public static boolean authenticate(String username, String password) {
        // hard-coded user-name and password
    	
    	try {
			isVolunteer = myVDB.getVolunteers().contains(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        if (username.equals("b") && password.equals("s")) {
        	isManager = true;
        	isVolunteer = false;
            return true;
        } else if (username.equals("mary") && password.equals("female")) {
        	isManager = false;
        	isVolunteer = true;
        	return true;
        }
        return false;
    }

	/**
	 * @return the isManager
	 */
	public boolean isManager() {
		return isManager;
	}

	/**
	 * @param isManager the isManager to set
	 */
	public void setManager(boolean isManager) {
		Login.isManager = isManager;
	}

	/**
	 * @return the isVolunteer
	 */
	public boolean isVolunteer() {
		return isVolunteer;
	}

	/**
	 * @param isVolunteer the isVolunteer to set
	 */
	public void setVolunteer(boolean isVolunteer) {
		Login.isVolunteer = isVolunteer;
	}
}