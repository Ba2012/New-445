package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
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
import Database.JobUserDB;
import Database.VolunteerUserDB;
import model.JobUser;
import model.VolunteerUser;

public class VolunteerView extends JFrame implements ActionListener, TableModelListener {

		private static final long serialVersionUID = 1779520078061383929L;
		private JButton btnView , btnSearch, btnAdd, btnDelete;
		private JPanel pnlButtons, pnlContent;
		private JobUserDB db;
		private List<JobUser> list;
		private VolunteerUser myVol;
		private VolunteerUserDB theVOL;
		private String[] columnNames = {"Job ID",
	            "Park ID",
	            "Park UserName",
	            "Job Name",
	            "Description",
	            "Status"};
		
		private Object[][] data;
		private JTable table;
		private JScrollPane scrollPane;
		private JPanel pnlSearch;
		private JLabel lblName;;
		private JTextField txfName;
		private JButton btnSignUp;
		
		private JPanel pnlAdd;
		private JLabel[] txfLabel = new JLabel[8];
		private JTextField[] txfField = new JTextField[8];
		private JButton btnAddJob;
		
		private JPanel pnlDelete;
		private JLabel[] deleteLabel = new JLabel[3];
		private JTextField[] deleteField = new JTextField[3];
		private JButton btnDeleteJob;
				
		private JPanel pnlCancel;
		private JLabel lbblName;;
		private JTextField txxfName;
		private JButton btnCancel;		
		private JButton btnLogout;
		
//		private JPanel pnlAddVol;
//		private JLabel[] txfLabel = new JLabel[8];
//		private JTextField[] txfField = new JTextField[8];
//		private JButton btnAddVolunteer;
		
		/**
		 * Creates the frame and components and launches the GUI.
		 */
		public VolunteerView(VolunteerUser theVolunteer) {
			super("Volunteer");			
			db = new JobUserDB();
			myVol = new VolunteerUser();
			myVol = theVolunteer;
			theVOL = new VolunteerUserDB();
			try
			{
				list = db.getJobs();
				// jobId, parkId, pUserName, name, description, status
				data = new Object[list.size()][columnNames.length];
				for (int i=0; i<list.size(); i++) {
					data[i][0] = list.get(i).getMyJobId();
					data[i][1] = list.get(i).getParkId();
					data[i][2] = list.get(i).getPUserName();
					data[i][3] = list.get(i).getMyName();
					data[i][4] = list.get(i).getMyDescription();
					data[i][5] = list.get(i).getMyStatus();			
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
			pnlButtons = new JPanel();
			btnView = new JButton("View Jobs");
			btnView.addActionListener(this);
			
			btnSearch = new JButton("Search Jobs");
			btnSearch.addActionListener(this);
			
			btnAdd = new JButton("Add Jobs");
			btnAdd.addActionListener(this);
			
			btnDelete = new JButton("View/Cancel Job");
			btnDelete.addActionListener(this);

			
			btnLogout = new JButton("Logout");
			btnLogout.addActionListener(this);
			
			pnlButtons.add(btnView );
			pnlButtons.add(btnDelete);
			pnlButtons.add(btnLogout);
			add(pnlButtons, BorderLayout.NORTH);
			
			//List Panel
			pnlContent = new JPanel();
			table = new JTable(data, columnNames);
			
			//Delete panel
			pnlDelete = new JPanel();
			String deleteName[] = {"Job ID: "};
			for(int i=0; i<deleteName.length; i++){
				JPanel panel = new JPanel();
				deleteLabel[i] = new JLabel(deleteName[i]);
				deleteField[i] = new JTextField(10);
				panel.add(deleteLabel[i]);
				panel.add(deleteField[i]);
				pnlDelete.add(panel);
			}
			JPanel dPanel = new JPanel();
			btnDeleteJob = new JButton("Cancel");
			btnDeleteJob.addActionListener(this);
			dPanel.add(btnDeleteJob);
			pnlDelete.add(dPanel);
			
			add(pnlContent, BorderLayout.CENTER);
			
			//Search Panel
			pnlSearch = new JPanel();
			lblName = new JLabel("Enter Job ID: ");
			txfName = new JTextField(25);
			btnSignUp = new JButton("Sign Up");
			btnSignUp.addActionListener(this);
			pnlSearch.add(lblName);
			pnlSearch.add(txfName);
			pnlSearch.add(btnSignUp);
			
			//Cancel Job Panel
			pnlCancel = new JPanel();
			lbblName = new JLabel("Enter Job ID: ");
			txxfName = new JTextField(5);
			btnCancel = new JButton("Cancel Job");
			btnCancel.addActionListener(this);
			pnlCancel.add(lbblName);
			pnlCancel.add(txxfName);
			pnlCancel.add(btnCancel);
			
			//Add Panel
			pnlAdd = new JPanel();
			pnlAdd.setLayout(new GridLayout(9, 0));
			String labelNames[] = {"Enter Job ID: ", "Enter Park ID: ", "Enter ParkUser Name: ", "Enter Job Name: ",
					"Enter Job Description: ", "Enter Job Status: "};
			for (int i=0; i<labelNames.length; i++) {
				JPanel panel = new JPanel();
				txfLabel[i] = new JLabel(labelNames[i]);
				txfField[i] = new JTextField(10);
				panel.add(txfLabel[i]);
				panel.add(txfField[i]);
				pnlAdd.add(panel);
			}
			JPanel panel = new JPanel();
			btnAddJob = new JButton("Add");
			btnAddJob.addActionListener(this);
			panel.add(btnAddJob);
			pnlAdd.add(panel);			
			add(pnlContent, BorderLayout.CENTER);			
		}
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			LoginUIMain.loginUI();		}

		/**
		 * Event handling to change the panels when different tabs are clicked,
		 * add and search buttons are clicked on the corresponding add and search panels.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnView) {
				try {
					list = db.getJobs();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
				data = new Object[list.size()][columnNames.length];
				for (int i=0; i<list.size(); i++) {
					data[i][0] = list.get(i).getMyJobId();
					data[i][1] = list.get(i).getParkId();
					data[i][2] = list.get(i).getPUserName();
					data[i][3] = list.get(i).getMyName();
					data[i][4] = list.get(i).getMyDescription();
					data[i][5] = list.get(i).getMyStatus();	
				}
				pnlContent.removeAll();
				table = new JTable(data, columnNames);
				table.getModel().addTableModelListener(this);
				scrollPane = new JScrollPane(table);
				pnlContent.removeAll();
				pnlContent.add(pnlSearch);
				pnlContent.revalidate();
				this.repaint();
				pnlContent.add(scrollPane);
				pnlContent.revalidate();
				this.repaint();
			} else if (e.getSource() == btnAdd) {
				pnlContent.removeAll();
				pnlContent.add(pnlAdd);
				pnlContent.revalidate();
				this.repaint();				
			} else if (e.getSource() == btnSignUp) {
				boolean canSign = false;
				String name = txfName.getText();
				if (name.length() > 0) {
					int newInt = Integer.parseInt(txfName.getText());
					// SQL QUERY to check if already signed up for that job
					try {
						canSign = theVOL.canSignUp(myVol, newInt);						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(canSign) {
						db.addJob(newInt, myVol);
						JOptionPane.showMessageDialog(null, "Signed Up Successfully!");
					} else{
						JOptionPane.showMessageDialog(null, "You have already Signed Up For This Job");
					}
					pnlContent.removeAll();
					table = new JTable(data, columnNames);
					table.getModel().addTableModelListener(this);
					scrollPane = new JScrollPane(table);
					pnlContent.add(scrollPane);
					pnlContent.revalidate();
					this.repaint();
				}
			} else if (e.getSource() == btnAddJob) {
				int jobId = Integer.parseInt(txfField[0].getText());						
				db.addJob(jobId, myVol);
				JOptionPane.showMessageDialog(null, "Added Successfully!");
				for (int i=0; i<txfField.length; i++) {
					txfField[i].setText("");
				}
			} else if (e.getSource() == btnDelete) {
				try {
					list = db.getAllJobsByVol(myVol.getMyUserId());
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
				data = new Object[list.size()][columnNames.length];
				for (int i=0; i<list.size(); i++) {
					data[i][0] = list.get(i).getMyJobId();
					data[i][1] = list.get(i).getParkId();
					data[i][2] = list.get(i).getPUserName();
					data[i][3] = list.get(i).getMyName();
					data[i][4] = list.get(i).getMyDescription();
				}
				pnlContent.removeAll();
				table = new JTable(data, columnNames);
				table.getModel().addTableModelListener(this);
				scrollPane = new JScrollPane(table);
				pnlContent.removeAll();
				pnlContent.add(pnlCancel);
				pnlContent.revalidate();
				this.repaint();
				pnlContent.add(scrollPane);
				pnlContent.revalidate();
				this.repaint();		
			
			} else if (e.getSource() == btnCancel)  {
				System.out.println("Delete");
				int num = (Integer.parseInt(txxfName.getText()));
				db.deleteMyJob(myVol.getMyUserId(), num);
				JOptionPane.showMessageDialog(null, "Job "+num+ " Successfully Deleted!");
				this.repaint();
				pnlContent.add(pnlDelete);
				pnlContent.revalidate();				
				this.repaint();
			} else if (e.getSource() == btnLogout) {
				this.dispose();
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
	        db.updateJobs(row, columnName, data);
			
		}
	}