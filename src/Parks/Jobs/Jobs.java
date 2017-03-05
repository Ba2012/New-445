package Parks.Jobs;

public class Jobs {
	
	private int myJobId;
	private int myParkId;
	private String myPUserName;
	private String myName;
	private String myDescription;
	private String myStatus;
	
	
	
	public Jobs(int jobId, int parkId, String theManagersId, 
			String theName, String theDecription, String theStatus) {
		myJobId = jobId;
		myParkId = parkId;
		myPUserName = theManagersId;
		myName = theName;
		myDescription = theDecription;
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
	 * @return the myParkId
	 */
	public int getMyParkId() {
		return myParkId;
	}



	/**
	 * @param myParkId the myParkId to set
	 */
	public void setMyParkId(int myParkId) {
		this.myParkId = myParkId;
	}



	/**
	 * @return the myPUserName
	 */
	public String getMyPUserName() {
		return myPUserName;
	}



	/**
	 * @param myPUserName the myPUserName to set
	 */
	public void setMyPUserName(String myPUserName) {
		this.myPUserName = myPUserName;
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
