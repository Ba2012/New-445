package authentication;

import Database.VolunteerUserDB;
import model.ParkManagerUser;
import model.VolunteerUser;


/**
 * 
 * @author John
 *
 */
public class Login {
	
	private static boolean isManager;
	
	private static boolean isVolunteer;
	public static VolunteerUser myVolUser;
	public static ParkManagerUser myPMUser;
	public static VolunteerUserDB myVDB;
	
	public Login() {
		setManager(isManager);
		setVolunteer(isVolunteer);
	}
	 
    public static boolean authenticate(String username, String password) {
        // hard-coded user-name and password
    	
        if (username.equals("Bryce") && password.equals("school")|| username.equals("Larry") && password.equals("banana")) {
        	isManager = true;
        	isVolunteer = false;
        	myPMUser = new ParkManagerUser();
            return true;
        } else if (username.equals("John") && password.equals("anything")|| username.equals("Ethan") && password.equals("Qwerty")) {
        	isManager = false;
        	isVolunteer = true;
        	myVolUser = new VolunteerUser();
        	if (username.equals("John")) {
        		myVolUser.setMyUserId(1);
        	} else {
        		myVolUser.setMyUserId(2);
        	}
        	
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