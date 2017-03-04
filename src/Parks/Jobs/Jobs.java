package Parks.Jobs;

public class Jobs {
	
	public int myJobId;
	public int myParkId;
	public String myPUserName;
	public String myName;
	public String myDescription;
	public String myStatus;
	
	
	
	public Jobs(int jobId, int parkId, String theManagersId, 
			String theName, String theDecription, String theStatus) {
		
		myJobId = jobId;
		myParkId = parkId;
		myPUserName = theManagersId;
		myName = theName;
		myDescription = theDecription;
		myStatus = theStatus;
		
	}
	public int getMyJobId() {
		return myJobId;
	}
	public void setMyJobId(int myJobId) {
		this.myJobId = myJobId;
	}
	public int getMyParkId() {
		return myParkId;
	}
	public void setMyParkId(int myParkId) {
		this.myParkId = myParkId;
	}
	public String getMyPUserName() {
		return myPUserName;
	}
	public void setMyPUserName(String myPUserName) {
		this.myPUserName = myPUserName;
	}
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}
	public String getMyDescription() {
		return myDescription;
	}
	public void setMyDescription(String myDescription) {
		this.myDescription = myDescription;
	}
	public String getMyStatus() {
		return myStatus;
	}
	public void setMyStatus(String myStatus) {
		this.myStatus = myStatus;
	}
	

}
