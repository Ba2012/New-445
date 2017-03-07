package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import Database.JobUserDB;
import model.JobUser;

public class VolunteerView extends JFrame implements ActionListener, TableModelListener {

	/**
	 * A user interface to view the movies, add a new movie and to update an existing movie.
	 * The list is a table with all the movie information in it. The TableModelListener listens to
	 * any changes to the cells to modify the values for reach movie.
	 * @author mmuppa
	 *
	 */
	
	// jobId, parkId, pUserName, name, description, status
	

		
		private static final long serialVersionUID = 1779520078061383929L;
		private JButton btnView , btnSearch, btnAdd, btnDelete;
		private JPanel pnlButtons, pnlContent;
		private JobUserDB db;
		private List<JobUser> list;
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
		private JButton btnFNameSearch;
		
		private JPanel pnlAdd;
		private JLabel[] txfLabel = new JLabel[8];
		private JTextField[] txfField = new JTextField[8];
		private JButton btnAddJob;
		
		private JPanel pnlDelete;
		private JLabel[] deleteLabel = new JLabel[3];
		private JTextField[] deleteField = new JTextField[3];
		private JButton btnDeleteJob;
		
		/**
		 * Creates the frame and components and launches the GUI.
		 */
		public VolunteerView() {
			super("Volunteer");
			
			db = new JobUserDB();
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
			
			btnDelete = new JButton("Cancel Job");
			btnDelete.addActionListener(this);
			
			pnlButtons.add(btnView );
			pnlButtons.add(btnSearch);
			pnlButtons.add(btnDelete);
			add(pnlButtons, BorderLayout.NORTH);
			
			//List Panel
			pnlContent = new JPanel();
			table = new JTable(data, columnNames);
//			scrollPane = new JScrollPane(table);
//			pnlContent.add(scrollPane);
//			table.getModel().addTableModelListener(this);
			
			//Delete panel
			pnlDelete = new JPanel();
//			pnlDelete.setLayout(new GridLayout(0, 3));
			String deleteName[] = {"Job ID: ", "Job Name: ", "Park ID: "};
			for(int i=0; i<deleteName.length; i++){
				JPanel panel = new JPanel();
				deleteLabel[i] = new JLabel(deleteName[i]);
				deleteField[i] = new JTextField(10);
				panel.add(deleteLabel[i]);
				panel.add(deleteField[i]);
				pnlDelete.add(panel);
			}
			JPanel dPanel = new JPanel();
			btnDeleteJob = new JButton("Delete");
			btnDeleteJob.addActionListener(this);
			dPanel.add(btnDeleteJob);
			pnlDelete.add(dPanel);
//			pnlDelete.add(dPanel);
			
			add(pnlContent, BorderLayout.CENTER);
			
			
			//Search Panel
			pnlSearch = new JPanel();
			lblName = new JLabel("Enter Job ID: ");
			txfName = new JTextField(25);
			btnFNameSearch = new JButton("Sign Up");
			btnFNameSearch.addActionListener(this);
			pnlSearch.add(lblName);
			pnlSearch.add(txfName);
			pnlSearch.add(btnFNameSearch);
			
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
			LoginUIMain.loginUI();
		}

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
//				btnSearch.setText("Sign Up");
				pnlContent.removeAll();
				pnlContent.add(pnlSearch);
				pnlContent.revalidate();
				this.repaint();
				pnlContent.add(scrollPane);
				pnlContent.revalidate();
				this.repaint();
				
			} else if (e.getSource() == btnSearch) {
				pnlContent.removeAll();
				pnlContent.add(pnlSearch);
				pnlContent.revalidate();
				this.repaint();
			} else if (e.getSource() == btnAdd) {
				pnlContent.removeAll();
				pnlContent.add(pnlAdd);
				pnlContent.revalidate();
				this.repaint();
				
			} else if (e.getSource() == btnFNameSearch) {
				String name = txfName.getText();
				if (name.length() > 0) {
					list = db.getJob(name);
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
					pnlContent.add(scrollPane);
					pnlContent.revalidate();
					this.repaint();
				}
			} else if (e.getSource() == btnAddJob) {
				Random rd = new Random();
				JobUser user = new JobUser(Math.abs(rd.nextInt(1000)), Integer.parseInt(txfField[0].getText())
						,txfField[1].getText(), txfField[2].getText(), txfField[3].getText(), txfField[4].getText());
				db.addJob(user);
				JOptionPane.showMessageDialog(null, "Added Successfully!");
				for (int i=0; i<txfField.length; i++) {
					txfField[i].setText("");
				}
			} else if (e.getSource() == btnDelete) {
				pnlContent.removeAll();
				pnlContent.add(pnlDelete);
				pnlContent.revalidate();
				this.repaint();			
			} else if (e.getSource() == btnDeleteJob) {
				pnlContent.removeAll();
				pnlContent.add(pnlDelete);
				pnlContent.revalidate();
				
				this.repaint();
				String jobName = deleteField[0].getText();
				String jobDesc = deleteField[1].getText();
				int id = Integer.parseInt(deleteField[2].getText());
				try {
					db.deleteUser(id, jobName, jobDesc);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
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
	        
	        db.updateMovie(row, columnName, data);
			
		}
		
		

	}




