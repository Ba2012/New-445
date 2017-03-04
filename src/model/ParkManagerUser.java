package model;
import java.util.Objects;

public class ParkManagerUser {

	private String myUserId;
	private int myParkId;
	private String myPassword;
	private String myName;
	
	
	public ParkManagerUser(String userId, int theParkId, String pw, String theName) {
		
		myUserId = Objects.requireNonNull(userId);
		myParkId = Objects.requireNonNull(theParkId);
		myPassword = Objects.requireNonNull(pw);
		myName = Objects.requireNonNull(theName);
		
	}
	
	public String getUserId() {
		return myUserId;
	}
	
	public int getParId() {
		return myParkId;
	}
	
	public String getName() {
		return  myName;
	}

	public String getPw() {		
		return myPassword;
	}
	
	
	
}
