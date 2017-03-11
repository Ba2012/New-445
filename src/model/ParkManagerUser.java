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
	
	public ParkManagerUser(ParkManagerUser thePM) {
		myUserId = thePM.getUserId();
		myParkId = thePM.getParId();
		myPassword = thePM.getPw();
		myName = thePM.myName;
	}

	public ParkManagerUser() {
		setMyName(myName);
		setMyParkId(myParkId);
		setMyPassword(myPassword);
		setMyUserId(myUserId);
	}

	public void setMyUserId(String myUserId) {
		this.myUserId = myUserId;
	}

	public void setMyParkId(int myParkId) {
		this.myParkId = myParkId;
	}

	public void setMyPassword(String myPassword) {
		this.myPassword = myPassword;
	}

	public void setMyName(String myName) {
		this.myName = myName;
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
