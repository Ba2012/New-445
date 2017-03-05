package model;

public class JobUser {
	
	private int myJobId;
	private int ParkId;
	private String PUserName;
	private String myName;
	private String myDescription;
	private String myStatus;
	
	public JobUser(int theJobId, int theParkId, String theUserName, String theName, String theDescription, String theStatus) {
		myJobId = theJobId;
		ParkId = theParkId;
		PUserName = theUserName;
		myName = theName;
		myDescription = theDescription;
		myStatus = theStatus;
		
	}

	/**
	 * @return the myJobId
	 */
	public int getMyJobId() {
		return myJobId;
	}

	/**
	 * @param myJobId the myJobId to set
	 */
	public void setMyJobId(int myJobId) {
		this.myJobId = myJobId;
	}

	/**
	 * @return the parkId
	 */
	public int getParkId() {
		return ParkId;
	}

	/**
	 * @param parkId the parkId to set
	 */
	public void setParkId(int parkId) {
		ParkId = parkId;
	}

	/**
	 * @return the pUserName
	 */
	public String getPUserName() {
		return PUserName;
	}

	/**
	 * @param pUserName the pUserName to set
	 */
	public void setPUserName(String pUserName) {
		PUserName = pUserName;
	}

	/**
	 * @return the myName
	 */
	public String getMyName() {
		return myName;
	}

	/**
	 * @param myName the myName to set
	 */
	public void setMyName(String myName) {
		this.myName = myName;
	}

	/**
	 * @return the myDescription
	 */
	public String getMyDescription() {
		return myDescription;
	}

	/**
	 * @param myDescription the myDescription to set
	 */
	public void setMyDescription(String myDescription) {
		this.myDescription = myDescription;
	}

	/**
	 * @return the myStatus
	 */
	public String getMyStatus() {
		return myStatus;
	}

	/**
	 * @param myStatus the myStatus to set
	 */
	public void setMyStatus(String myStatus) {
		this.myStatus = myStatus;
	}

}

