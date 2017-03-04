package job;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;

import com.sun.rowset.JdbcRowSetImpl;

public class JobManagerUser {
	
	static final String DB_URL =
		      "jdbc:mariadb://localhost:3306/GroupProjectDB";
		   
	static final String DB_USER = "root";
	static final String DB_PASS = "1234";
	private JdbcRowSet rowSet = null;
	
	public JobManagerUser() throws ClassNotFoundException {
		try {
	        rowSet = new JdbcRowSetImpl();
	        rowSet.setUrl(DB_URL);
	        rowSet.setUsername(DB_USER);
	        rowSet.setPassword(DB_PASS);
	        System.out.println("Connection Established for Jobs!");
	        rowSet.setCommand("SELECT * FROM JOBS");
	        rowSet.execute();

		}  catch (SQLException ex) {
			ex.printStackTrace();
	    }
	}
	public JobManager create(JobManager jm) {
		try {
			rowSet.moveToInsertRow();
	        rowSet.updateInt("jobId", jm.getJobId());
	        rowSet.updateString("jobDescription", jm.getJobDescription());
	        rowSet.updateString("jobDate", jm.getJobDate());
	        rowSet.updateString("jobStatus", jm.getJobStatus());
	        rowSet.updateString("jobLocation", jm.getJobLocation());
	        rowSet.updateInt("numOfVolunteers", jm.getNumOfVolunteers());
	        rowSet.insertRow();
	        rowSet.moveToCurrentRow();
	    } catch (SQLException ex) {
	    	try {
	    		rowSet.rollback();
	            jm = null;
	        } catch (SQLException e) {

	        }
	         	ex.printStackTrace();
	    }
	    return jm;
	}

	public JobManager update(JobManager jm) {
		try {
			rowSet.updateInt("jobId", jm.getJobId());
	        rowSet.updateString("jobDescription", jm.getJobDescription());
	        rowSet.updateString("jobDate", jm.getJobDate());
	        rowSet.updateString("jobStatus", jm.getJobStatus());
	        rowSet.updateString("jobLocation", jm.getJobLocation());
	        rowSet.updateInt("numOfVolunteers", jm.getNumOfVolunteers());
	        rowSet.updateRow();
	        rowSet.moveToCurrentRow();
	    } catch (SQLException ex) {
	    	try {
	    		rowSet.rollback();
	        } catch (SQLException e) {

	        }
	        	ex.printStackTrace();
	    }
	    	return jm;
	}

	public void delete() {
		try {
			rowSet.moveToCurrentRow();
	        rowSet.deleteRow();
	    } catch (SQLException ex) {
	    	try {
	    		rowSet.rollback();
	        } catch (SQLException e) { }
	        	ex.printStackTrace();
	    }

	}

	public JobManager moveFirst() {
		JobManager jm = new JobManager();
	    try {
	    	rowSet.first();
	        jm.setJobId(rowSet.getInt("jobId"));
	        jm.setJobDescription(rowSet.getString("jobDescription"));
	        jm.setJobDate(rowSet.getString("jobDate"));
	        jm.setJobStatus(rowSet.getString("jobStatus"));
	        jm.setJobLocation(rowSet.getString("jobLocation"));
	        jm.setNumOfVolunteers(rowSet.getInt("numOfVolunteers"));

	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    }
	    return jm;
	}

	public JobManager moveLast() {
		JobManager jm = new JobManager();
	    try {
	    	rowSet.last();
	    	jm.setJobId(rowSet.getInt("jobId"));
	        jm.setJobDescription(rowSet.getString("jobDescription"));
	        jm.setJobDate(rowSet.getString("jobDate"));
	        jm.setJobStatus(rowSet.getString("jobStatus"));
	        jm.setJobLocation(rowSet.getString("jobLocation"));
	        jm.setNumOfVolunteers(rowSet.getInt("numOfVolunteers"));

	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    }
	    return jm;
	}

	public JobManager moveNext() {
		JobManager jm = new JobManager();
	    try {
	    	if (rowSet.next() == false)
	    		rowSet.previous();
	    	jm.setJobId(rowSet.getInt("jobId"));
	        jm.setJobDescription(rowSet.getString("jobDescription"));
	        jm.setJobDate(rowSet.getString("jobDate"));
	        jm.setJobStatus(rowSet.getString("jobStatus"));
	        jm.setJobLocation(rowSet.getString("jobLocation"));
	        jm.setNumOfVolunteers(rowSet.getInt("numOfVolunteers"));

	      } catch (SQLException ex) {
	         ex.printStackTrace();
	      }
	      return jm;
	}

	public JobManager movePrevious() {
		JobManager jm = new JobManager();
	    try {
	    	if (rowSet.previous() == false)
	    		rowSet.next();
	    	jm.setJobId(rowSet.getInt("jobId"));
	        jm.setJobDescription(rowSet.getString("jobDescription"));
	        jm.setJobDate(rowSet.getString("jobDate"));
	        jm.setJobStatus(rowSet.getString("jobStatus"));
	        jm.setJobLocation(rowSet.getString("jobLocation"));
	        jm.setNumOfVolunteers(rowSet.getInt("numOfVolunteers"));

	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    }
	    return jm;
	}

	public JobManager getCurrent() {
		JobManager jm = new JobManager();
	    try {
	    	rowSet.moveToCurrentRow();
	    	jm.setJobId(rowSet.getInt("jobId"));
	        jm.setJobDescription(rowSet.getString("jobDescription"));
	        jm.setJobDate(rowSet.getString("jobDate"));
	        jm.setJobStatus(rowSet.getString("jobStatus"));
	        jm.setJobLocation(rowSet.getString("jobLocation"));
	        jm.setNumOfVolunteers(rowSet.getInt("numOfVolunteers"));
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    }
	    return jm;
	}

}
