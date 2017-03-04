package view;

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

import model.UrbanParkUser;
import model.VolunteerUser;
import net.miginfocom.swing.MigLayout;

public class UrbanParksUI extends JPanel {

	/**
	 * Auto serialized id.
	 */
	private static final long serialVersionUID = 3514128051554864048L;
	
	private JTextField idField = new JTextField(10);
	private JTextField fNameField = new JTextField(30);
	private JTextField lNameField = new JTextField(30);
	private JTextField stAddressField = new JTextField(30);
	private JTextField cityField = new JTextField(30);
	private JTextField stateField = new JTextField(30);
	private JTextField zipcodeField = new JTextField(10);
	private JTextField emailField = new JTextField(30);
	private JTextField phoneField = new JTextField(30);

	private JButton createButton = new JButton("New...");
	private JButton updateButton = new JButton("Update");
	private JButton deleteButton = new JButton("Delete");
	private JButton firstButton = new JButton("First");
	private JButton previousButton = new JButton("Previous");
	private JButton nextButton = new JButton("Next");
	private JButton lastButton = new JButton("Last");
	private JButton viewjobButton = new JButton("View_Jobs");
	
	private UrbanParkUser user;

	public UrbanParksUI() throws ClassNotFoundException, SQLException {
		user = new UrbanParkUser();
		setBorder(new TitledBorder
				(new EtchedBorder(),"Volunteers Info"));
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
		
		// for job info
		panel.add(viewjobButton);
		viewjobButton.addActionListener(new ButtonHandler());
		
		return panel;
	}

	private JPanel initFields() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		panel.add(new JLabel("ID #"), "align label");
		panel.add(idField, "wrap");
		idField.setEnabled(false);
		panel.add(new JLabel("First Name"), "align label");
		panel.add(fNameField, "wrap");
		panel.add(new JLabel("Last Name"), "align label");
		panel.add(lNameField, "wrap");
		panel.add(new JLabel("Street"), "align label");
		panel.add(stAddressField, "wrap");
		panel.add(new JLabel("City"), "align label");
		panel.add(cityField, "wrap");
		panel.add(new JLabel("State"), "align label");
		panel.add(stateField, "wrap");
		panel.add(new JLabel("Zipcode"), "align label");
		panel.add(zipcodeField, "wrap");
		panel.add(new JLabel("Email"), "align label");
		panel.add(emailField, "wrap");
		panel.add(new JLabel("Phone"), "align label");
		panel.add(phoneField, "wrap");
		return panel;
	}

	private VolunteerUser getFieldData() {
		VolunteerUser u = new VolunteerUser();
		u.setMyUserId(Integer.parseInt(idField.getText()));
      	u.setMyFName(fNameField.getText());
      	u.setMyLName(lNameField.getText());
      	u.setMyStAddress(stAddressField.getText());
      	u.setMyCity(cityField.getText());
      	u.setMyState(stateField.getText());
      	u.setMyZipcode(Integer.parseInt(zipcodeField.getText()));
      	u.setMyEmail(emailField.getText());
      	u.setMyPhone(phoneField.getText());
      	return u;
	}

	private void setFieldData(VolunteerUser u) {
		idField.setText(String.valueOf(u.getMyUserId()));
		fNameField.setText(u.getMyFName());
      	lNameField.setText(u.getMyLName());
      	stAddressField.setText(u.getMyStAddress());
      	cityField.setText(u.getMyCity());
      	stateField.setText(u.getMyState());
      	zipcodeField.setText(String.valueOf(u.getMyZipcode()));
      	emailField.setText(u.getMyEmail());
      	phoneField.setText(u.getMyPhone());
	}

	private boolean isEmptyFieldData() {
		return (fNameField.getText().trim().isEmpty()
				&& lNameField.getText().trim().isEmpty()
				&& stAddressField.getText().trim().isEmpty()
				&& cityField.getText().trim().isEmpty()
				&& stateField.getText().trim().isEmpty()
				&& zipcodeField.getText().trim().isEmpty()
				&& emailField.getText().trim().isEmpty()
				&& phoneField.getText().trim().isEmpty());
	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			VolunteerUser u = getFieldData();
			switch (e.getActionCommand()) {
			case "Save":
				if (isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null,
							"Cannot create an empty record");
					return;
				}
				if (user.create(u) != null)
					
					JOptionPane.showMessageDialog(null,
							"New person created successfully.");
					createButton.setText("New...");
					break;
			case "New...":
				u.setMyUserId(u.getMyUserId() + 1);
				u.setMyFName("");
				u.setMyFName("");
				u.setMyStAddress("");
				u.setMyCity("");
				u.setMyState("");
				u.setMyZipcode(u.getMyZipcode() + 1);
				u.setMyEmail("");
				u.setMyPhone("");
				setFieldData(u);
				createButton.setText("Save");
            	break;
	         case "Update":
	            if (isEmptyFieldData()) {
	            	JOptionPane.showMessageDialog(null,
	            			"Cannot update an empty record");
	            	return;
	            }
	            if (user.update(u) != null)
	            	JOptionPane.showMessageDialog(
	            		   null,"Person with ID:" + String.valueOf(u.getMyUserId()
	            				   + " is updated successfully"));
	               	break;
	         case "Delete":
	        	 if (isEmptyFieldData()) {
	        		 JOptionPane.showMessageDialog(null,
	        				 "Cannot delete an empty record");
	        		 return;
	        	 }
//	             u = user.getCurrent();
	             user.delete(u);
	             JOptionPane.showMessageDialog(
	            		 null,"Person with ID:"
	            				 + String.valueOf(u.getMyUserId()
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
	         case "View_Jobs":
	        	 UrbanParksMain.closeF();
	        	 try {
					UrbanParksMain.jobFrameDisplay();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	         default:
	        	 JOptionPane.showMessageDialog(null,
	        			 "invalid command");
			}
		}
	}
}
