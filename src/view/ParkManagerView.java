package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import Database.JobDB;
import Database.VolunteerUserDB;
import Parks.Jobs.Jobs;
import authentication.Login;
import authentication.LoginDialog;
import model.VolunteerUser;

public class ParkManagerView extends JFrame implements ActionListener, TableModelListener {

	/**
	 * A user interface to view the movies, add a new movie and to update an existing movie.
	 * The list is a table with all the movie information in it. The TableModelListener listens to
	 * any changes to the cells to modify the values for reach movie.
	 * @author mmuppa
	 *
	 */
	

		
		private static final long serialVersionUID = 1779520078061383929L;
		private JButton btnList, btnSearch, btnAddVol, btnDelete, btnJob, btnListJobs;
		private JPanel pnlButtons, pnlContent;
		private VolunteerUserDB volunteerDB;
		private JobDB jobDB;
		private List<Jobs> myJobs;
		private List<VolunteerUser> list;
		private String[] columnNames = {"userId",
	            "First Name",
	            "Last Name",
	            "Street Address",
	            "City",
	            "State",
	            "Zipcode",
	            "Email",
	            "Phone Number"};
		private String[] jobColumns = {"JobId",
				"ParkId",
				"Park Manager",
				"Job Name",
				"Job Description",
				"Status"};
		
		private Object[][] data;
		private JTable table;
		private JScrollPane scrollPane;
		private JPanel pnlSearch;
		private JLabel lblName;;
		private JTextField txfName;
		private JButton btnFNameSearch;
		
		private JPanel pnlAddVol;
		private JLabel[] txfLabel = new JLabel[8];
		private JTextField[] txfField = new JTextField[8];
		private JButton btnAddVolunteer;
		
		private JPanel pnlAddJobs;
		private JLabel[] jobLabels = new JLabel[6];
		private JTextField[] jobsField = new JTextField[6];
		private JButton btnAddJob;
		
		private JPanel pnlDelete;
		private JLabel[] deleteLabel = new JLabel[3];
		private JTextField[] deleteField = new JTextField[3];
		private JButton btnDeleteVolunteer;
		
		private JPanel pnlListJobs;
		private JLabel[] jobList = new JLabel[6];
		private JTextField[] jobListField = new JTextField[6];
		
		/**
		 * Creates the frame and components and launches the GUI.
		 */
		public ParkManagerView() {
			super("Park Manager");
			
			volunteerDB = new VolunteerUserDB();
			jobDB = new JobDB();
			try
			{
				list = volunteerDB.getVolunteers();
				
				data = new Object[list.size()][columnNames.length];
				for (int i=0; i<list.size(); i++) {
					data[i][0] = list.get(i).getMyUserId();
					data[i][1] = list.get(i).getMyFName();
					data[i][2] = list.get(i).getMyLName();
					data[i][3] = list.get(i).getMyStAddress();
					data[i][4] = list.get(i).getMyCity();
					data[i][5] = list.get(i).getMyState();
					data[i][6] = list.get(i).getMyZipcode();
					data[i][7] = list.get(i).getMyEmail();
					data[i][8] = list.get(i).getMyPhone();
					
				}
				
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			createComponents();
			setVisible(true);
			setSize(700, 500);
		}
	    
		/**
		 * Creates panels for Volunteer list, search, add, delete and add the corresponding 
		 * components to each panel.
		 */
		private void createComponents()
		{
			volunteerDB = new VolunteerUserDB();
			jobDB = new JobDB();
			pnlButtons = new JPanel();
			btnList = new JButton("Volunteer List");
			btnList.addActionListener(this);
			
			btnSearch = new JButton("Volunteer Search");
			btnSearch.addActionListener(this);
			
			btnAddVol = new JButton("Add Volunteer");
			btnAddVol.addActionListener(this);
			
			btnDelete = new JButton("Delete Volunteer");
			btnDelete.addActionListener(this);
			
			btnJob = new JButton("Add Job");
			btnJob.addActionListener(this);
			
			pnlButtons.add(btnList);
			pnlButtons.add(btnSearch);
			pnlButtons.add(btnAddVol);
			pnlButtons.add(btnDelete);
			pnlButtons.add(btnJob);
			add(pnlButtons, BorderLayout.NORTH);
			
			//List Panel
			pnlContent = new JPanel();
			table = new JTable(data, columnNames);
			scrollPane = new JScrollPane(table);
			pnlContent.add(scrollPane);
			table.getModel().addTableModelListener(this);
			
			//Delete panel
			pnlDelete = new JPanel();
//			pnlDelete.setLayout(new GridLayout(0, 3));
			String deleteName[] = {"First Name: ", "Last Name: ", "UserId: "};
			for(int i=0; i<deleteName.length; i++){
				JPanel panel = new JPanel();
				deleteLabel[i] = new JLabel(deleteName[i]);
				deleteField[i] = new JTextField(10);
				panel.add(deleteLabel[i]);
				panel.add(deleteField[i]);
				pnlDelete.add(panel);
			}
			JPanel dPanel = new JPanel();
			btnDeleteVolunteer = new JButton("Delete");
			btnDeleteVolunteer.addActionListener(this);
			dPanel.add(btnDeleteVolunteer);
			pnlDelete.add(dPanel);
//			pnlDelete.add(dPanel);
			
			add(pnlContent, BorderLayout.CENTER);
			
			
			//Search Panel
			pnlSearch = new JPanel();
			lblName = new JLabel("Enter Name: ");
			txfName = new JTextField(25);
			btnFNameSearch = new JButton("Search");
			btnFNameSearch.addActionListener(this);
			pnlSearch.add(lblName);
			pnlSearch.add(txfName);
			pnlSearch.add(btnFNameSearch);
			
			//Add Volunteer Panel
			pnlAddVol = new JPanel();
			pnlAddVol.setLayout(new GridLayout(9, 0));
			String labelNames[] = {"Enter First Name: ", "Enter Last Name: ", "Enter Street Address: ", "Enter City: ",
					"Enter State: ", "Enter Zipcode: ", "Enter Email: ", "Enter Phone Number: "};
			for (int i=0; i<labelNames.length; i++) {
				JPanel panel = new JPanel();
				txfLabel[i] = new JLabel(labelNames[i]);
				txfField[i] = new JTextField(10);
				panel.add(txfLabel[i]);
				panel.add(txfField[i]);
				pnlAddVol.add(panel);
			}
			JPanel panel = new JPanel();
			btnAddVolunteer = new JButton("Add");
			btnAddVolunteer.addActionListener(this);
			panel.add(btnAddVolunteer);
			pnlAddVol.add(panel);
			
			add(pnlContent, BorderLayout.CENTER);
			
			//add job panel
			pnlAddJobs = new JPanel();
			pnlAddJobs.setLayout(new GridLayout(6, 0));
			String addJobNames[] = {"Enter Job ID Number: ", "Enter Your Park ID: ", "Enter Your UserName: " ,					
					"Enter the Name of the Job: ","Enter a Job Description: "};
		for (int i=0; i<addJobNames.length; i++) {
			JPanel JobPanel = new JPanel();
			jobLabels[i] = new JLabel (addJobNames[i]);
			jobsField[i] = new JTextField(10);
			JobPanel.add(jobLabels[i]);
			JobPanel.add(jobsField[i]);
			pnlAddJobs.add(JobPanel);
		}
		JPanel thePanel = new JPanel();
		btnAddJob = new JButton("Add Job");
		btnAddJob.addActionListener(this);
		thePanel.add(btnAddJob);
		pnlAddJobs.add(thePanel);
		
		
		
		}	
		

		/**
		 * @param args
		 */
		public static void main(String[] args) {
			LoginUIMain.loginUI();
		}
		
		public static void displayDatabase() {
			ParkManagerView testGUI = new ParkManagerView();
			testGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		/**
		 * Event handling to change the panels when different tabs are clicked,
		 * add and search buttons are clicked on the corresponding add and search panels.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnList) {
				try {
					list = volunteerDB.getVolunteers();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				data = new Object[list.size()][columnNames.length];
				for (int i=0; i<list.size(); i++) {
					data[i][0] = list.get(i).getMyUserId();
					data[i][1] = list.get(i).getMyFName();
					data[i][2] = list.get(i).getMyLName();
					data[i][3] = list.get(i).getMyStAddress();
					data[i][4] = list.get(i).getMyCity();
					data[i][5] = list.get(i).getMyState();
					data[i][6] = list.get(i).getMyZipcode();
					data[i][7] = list.get(i).getMyEmail();
					data[i][8] = list.get(i).getMyPhone();
				}
				pnlContent.removeAll();
				table = new JTable(data, columnNames);
				table.getModel().addTableModelListener(this);
				scrollPane = new JScrollPane(table);
				pnlContent.add(scrollPane);
				pnlContent.revalidate();
				this.repaint();
				
			} else if (e.getSource() == btnSearch) {
				pnlContent.removeAll();
				pnlContent.add(pnlSearch);
				pnlContent.revalidate();
				this.repaint();
			} else if (e.getSource() == btnAddVol) {
				pnlContent.removeAll();
				pnlContent.add(pnlAddVol);
				pnlContent.revalidate();
				this.repaint();
				
			} else if (e.getSource() == btnFNameSearch) {
				String name = txfName.getText();
				if (name.length() > 0) {
					list = volunteerDB.getUser(name);
					data = new Object[list.size()][columnNames.length];
					for (int i=0; i<list.size(); i++) {
						//data[i][0] = list.get(i).getUserId();
						data[i][0] = list.get(i).getMyFName();
						data[i][1] = list.get(i).getMyLName();
						data[i][2] = list.get(i).getMyStAddress();
						data[i][3] = list.get(i).getMyCity();
						data[i][4] = list.get(i).getMyState();
						data[i][5] = list.get(i).getMyZipcode();
						data[i][6] = list.get(i).getMyEmail();
						data[i][7] = list.get(i).getMyPhone();
					}
					pnlContent.removeAll();
					table = new JTable(data, columnNames);
					table.getModel().addTableModelListener(this);
					scrollPane = new JScrollPane(table);
					pnlContent.add(scrollPane);
					pnlContent.revalidate();
					this.repaint();
				}
			} else if (e.getSource() == btnAddVolunteer) {
				Random rd = new Random();
				VolunteerUser user = new VolunteerUser(Math.abs(rd.nextInt(1000)), txfField[0].getText()
						,txfField[1].getText(), txfField[2].getText(), txfField[3].getText(), txfField[4].getText(),
						Integer.parseInt(txfField[5].getText()), txfField[6].getText(), txfField[7].getText());
				volunteerDB.addUser(user);
				JOptionPane.showMessageDialog(null, "Volunteer added Successfully!");
				for (int i=0; i<txfField.length; i++) {
					txfField[i].setText("");
				}
			} else if (e.getSource() == btnDelete) {
				pnlContent.removeAll();
				pnlContent.add(pnlDelete);
				pnlContent.revalidate();
				this.repaint();			
			} else if (e.getSource() == btnDeleteVolunteer) {
				pnlContent.removeAll();
				pnlContent.add(pnlDelete);
				pnlContent.revalidate();
				
				this.repaint();
				String fname = deleteField[0].getText();
				String lname = deleteField[1].getText();
				int id = Integer.parseInt(deleteField[2].getText());
				try {
					volunteerDB.deleteUser(id, fname, lname);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//FIX THIS!
			} else if (e.getSource() == btnJob){ 
				pnlContent.removeAll();
				pnlContent.add(pnlAddJobs);
				pnlContent.revalidate();
				this.repaint();					
			} else if(e.getSource()==btnAddJob) {
				Jobs newJob = new Jobs(Integer.parseInt(jobsField[0].getText()), Integer.parseInt(jobsField[1].getText()),
						jobsField[2].getText(), jobsField[3].getText(), jobsField[4].getText(), "Availible");
				
				jobDB.addJob(newJob);
				JOptionPane.showMessageDialog(null, "Job added Successfully!");
//				for(int i = 0; i<jobsField.length; i++) {
//					jobsField[i].setText("");
//				}
			}
			
		}

		/**
		 * Event handling for any cell being changed in the table.
		 */
		@Override
		public void tableChanged(TableModelEvent e) {
			int row = e.getFirstRow();
	        int column = e.getColumn();
	        TableModel model = (TableModel)e.getSource();
	        String columnName = model.getColumnName(column);
	        Object data = model.getValueAt(row, column);
	        
	        volunteerDB.updateUser(row, columnName, data);
			
		}
		
		

	}



