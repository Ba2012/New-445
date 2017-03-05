package view;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

import authentication.Login;
import authentication.LoginDialog;

public class LoginUIMain {

	/**
     * Instantiating login GUI.
     */
    private static JFrame loginframe;
    
    static {
    	loginframe = new JFrame("Welcome to Urban parks!");
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		loginUI();
	}
	
	/**
	 * Displays the Park manager GUI.
	 */
	public static void displayDatabase() {
		TestGUI testGUI = new TestGUI();
		testGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/** 
	 * Displays the volunteer GUI.
	 */
	public static void displayVolDatabase() {
		VolunteerView vv = new VolunteerView();
		vv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
     * Displays a simple database frame by logging in.
     * 
     * @throws IOException Throws IOEXception.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
     */
    public static void start() throws IOException, ClassNotFoundException, SQLException {

        Login l = new Login();

    	if (l.isManager()) {
    		// display specific type of database
    		displayDatabase();
    		
    	} else if (l.isVolunteer()) {
    		// display specific type of database
    		displayVolDatabase();
    	}       			
    }
	
	
    public static void loginUI() {
        final JButton btnLogin = new JButton("Click to login");
 
        btnLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        LoginDialog loginDlg = new LoginDialog(loginframe);
                        loginDlg.setVisible(true);
                        // if log-on successfully
                        if(loginDlg.isSucceeded()){
                            btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
                        }
                    }
                });
 
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginframe.setSize(300, 100);
        loginframe.setLayout(new FlowLayout());
        loginframe.getContentPane().add(btnLogin);
        loginframe.setVisible(true);
    }
    
    /**
     * Closes the login frame.
     */
    public static void closeLoginFrame() {
    	loginframe.dispose();
    }
}