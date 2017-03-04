package job;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class ParkJobsUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1607855194661484632L;
	
	private JTextField jobIdField = new JTextField(10);
	private JTextField jobDescriptionField = new JTextField(30);
	private JTextField jobDateField = new JTextField(30);
	private JTextField jobStatusField = new JTextField(30);
	private JTextField jobLocationField = new JTextField(30);
	private JTextField numOfVolunteersField = new JTextField(30);
	
	private JButton createButton = new JButton("New...");
	private JButton updateButton = new JButton("Update");
	private JButton deleteButton = new JButton("Delete");
	private JButton firstButton = new JButton("First");
	private JButton previousButton = new JButton("Previous");
	private JButton nextButton = new JButton("Next");
	private JButton lastButton = new JButton("Last");
	
	private JobManagerUser user;

	public ParkJobsUI() throws ClassNotFoundException, SQLException {
		user = new JobManagerUser();
		setBorder(new TitledBorder
				(new EtchedBorder(),"Job Details"));
		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		add(initButtons(), BorderLayout.CENTER);
		setFieldData(user.moveFirst());
	}

	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		
		panel.add(createButton);
		createButton.addActionListener(new ButtonHandler());
		
		panel.add(updateButton);
		updateButton.addActionListener(new ButtonHandler());
		
		panel.add(deleteButton);
		deleteButton.addActionListener(new ButtonHandler());
		
		panel.add(firstButton);
		firstButton.addActionListener(new ButtonHandler());
		
		panel.add(previousButton);
		previousButton.addActionListener(new ButtonHandler());
		
		panel.add(nextButton);
		nextButton.addActionListener(new ButtonHandler());
		
		panel.add(lastButton);
		lastButton.addActionListener(new ButtonHandler());
		
		panel.add(lastButton);
		lastButton.addActionListener(new ButtonHandler());
		
		return panel;
	}

	private JPanel initFields() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		panel.add(new JLabel("ID #"), "align label");
		panel.add(jobIdField, "wrap");
		jobIdField.setEnabled(false);
		panel.add(new JLabel("Description"), "align label");
		panel.add(jobDescriptionField, "wrap");
		panel.add(new JLabel("Date"), "align label");
		panel.add(jobDateField, "wrap");
		panel.add(new JLabel("Status"), "align label");
		panel.add(jobStatusField, "wrap");
		panel.add(new JLabel("Location"), "align label");
		panel.add(jobLocationField, "wrap");
		panel.add(new JLabel("Volunteers"), "align label");
		panel.add(numOfVolunteersField, "wrap");

		return panel;
	}

	private JobManager getFieldData() {
		JobManager jm = new JobManager();
		jm.setJobId(Integer.parseInt(jobIdField.getText()));
      	jm.setJobDescription(jobDescriptionField.getText());
      	jm.setJobDate(jobDateField.getText());
      	jm.setJobStatus(jobStatusField.getText());
      	jm.setJobLocation(jobLocationField.getText());
      	jm.setNumOfVolunteers(Integer.parseInt(numOfVolunteersField.getText()));

      	return jm;
	}

	private void setFieldData(JobManager jm) {
		jobIdField.setText(String.valueOf(jm.getJobId()));
		jobDescriptionField.setText(jm.getJobDescription());
		jobDateField.setText(jm.getJobDate());
		jobStatusField.setText(jm.getJobStatus());
		jobLocationField.setText(jm.getJobLocation());
      	numOfVolunteersField.setText(String.valueOf(jm.getNumOfVolunteers()));
	}

	private boolean isEmptyFieldData() {
		return (jobIdField.getText().trim().isEmpty()
				&& jobDescriptionField.getText().trim().isEmpty()
				&& jobDateField.getText().trim().isEmpty()
				&& jobStatusField.getText().trim().isEmpty()
				&& jobLocationField.getText().trim().isEmpty()
				&& numOfVolunteersField.getText().trim().isEmpty());
	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JobManager jm = getFieldData();
			switch (e.getActionCommand()) {
			case "Save":
				if (isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null,
							"Cannot create an empty record");
					return;
				}
				if (user.create(jm) != null)
					JOptionPane.showMessageDialog(null,
							"New person created successfully.");
					createButton.setText("New...");
					break;
			case "New...":
				jm.setJobId(jm.getJobId() + 1);
				jm.setJobDescription("");
				jm.setJobDate("");
				jm.setJobStatus("");
				jm.setJobLocation("");
				jm.setNumOfVolunteers(jm.getNumOfVolunteers());
				createButton.setText("Save");
            	break;
	         case "Update":
	            if (isEmptyFieldData()) {
	            	JOptionPane.showMessageDialog(null,
	            			"Cannot update an empty record");
	            	return;
	            }
	            if (user.update(jm) != null)
	            	JOptionPane.showMessageDialog(
	            		   null,"Person with ID:" + String.valueOf(jm.getJobId()
	            				   + " is updated successfully"));
	               	break;
	         case "Delete":
	        	 if (isEmptyFieldData()) {
	        		 JOptionPane.showMessageDialog(null,
	        				 "Cannot delete an empty record");
	        		 return;
	        	 }
	             jm = user.getCurrent();
	             user.delete();
	             JOptionPane.showMessageDialog(
	            		 null,"Person with ID:"
	            				 + String.valueOf(jm.getJobId()
	            						 + " is deleted successfully"));
	               	break;
	         case "First":
	        	 setFieldData(user.moveFirst()); break;
	         case "Previous":
	        	 setFieldData(user.movePrevious()); break;
	         case "Next":
	        	 setFieldData(user.moveNext()); break;
	         case "Last":
	        	 setFieldData(user.moveLast()); break;
	         default:
	        	 JOptionPane.showMessageDialog(null,
	        			 "invalid command");
			}
		}
	}

}
