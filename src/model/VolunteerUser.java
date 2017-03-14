package model;

/**
 * 
 * @author Bryce
 *
 */
public class VolunteerUser {
	
	private int myUserId;
	private String myFName;
	private String myLName;
	private String myStAddress;
	private String myCity;
	private int myZipcode;
	private String myEmail;
	private String myPhone;
	private String myState;
	
	
	public VolunteerUser() {
		
			setMyUserId(myUserId);
			setMyFName(myFName);
			setMyLName(myLName);
			setMyStAddress(myStAddress);
			setMyCity(myCity);
			setMyZipcode(myZipcode);
			setMyEmail(myEmail);
			setMyPhone(myPhone);
		
		
	}
	
	public VolunteerUser(int id, String fName, String lName, String streetAddress, String city, String state, int zip,
			String email, String phone) {
		myUserId = id;
		myFName = fName;
		myLName = lName;
		myStAddress = streetAddress;
		myCity =city;
		myZipcode = zip;
		myEmail = email;
		myPhone = phone;
		myState = state;
		
	}


	public int getMyUserId() {
		return myUserId;
	}


	public void setMyUserId(int myUserId) {
		this.myUserId = myUserId;
	}


	public String getMyFName() {
		return myFName;
	}


	public void setMyFName(String myFName) {
		this.myFName = myFName;
	}


	public String getMyLName() {
		return myLName;
	}


	public void setMyLName(String myLName) {
		this.myLName = myLName;
	}


	public String getMyStAddress() {
		return myStAddress;
	}


	public void setMyStAddress(String myStAddress) {
		this.myStAddress = myStAddress;
	}


	public String getMyCity() {
		return myCity;
	}


	public void setMyCity(String myCity) {
		this.myCity = myCity;
	}


	public int getMyZipcode() {
		return myZipcode;
	}


	public void setMyZipcode(int myZipcode) {
		this.myZipcode = myZipcode;
	}


	public String getMyEmail() {
		return myEmail;
	}


	public void setMyEmail(String myEmail) {
		this.myEmail = myEmail;
	}


	public String getMyPhone() {
		return myPhone;
	}


	public void setMyPhone(String myPhone) {
		this.myPhone = myPhone;
	}


	public String getMyState() {
		return myState;
	}


	public void setMyState(String myState) {
		this.myState = myState;
	}


}
