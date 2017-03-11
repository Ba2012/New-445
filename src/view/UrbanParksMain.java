package view;

import java.awt.FlowLayout;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;

import authentication.Login;
import authentication.LoginDialog;
//import job.ParkJobsUI;


public class UrbanParksMain {

    /**
     * Instantiating power paint GUI.
     */
    private static JFrame f;
    private static JFrame frame;
    private static JFrame jobFrame;
    static {
        f = new JFrame();
        frame = new JFrame("Welcome to Urban Parks");
        jobFrame = new JFrame("Jobs");
    }
    
	/**
     * Private constructor, to prevent instantiation of this class.
     */
    private UrbanParksMain() {
        throw new IllegalStateException();
    }
    
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {  
		loginUI();
    }
    
	/**
     * Displays a simple database frame.
     * 
     * @throws IOException Throws IOEXception.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
     */
    public static void start() throws IOException, ClassNotFoundException, SQLException {

        Login l = new Login();

    	if (l.isManager()) {
    		f.setTitle("Park Manager");
    	} else if (l.isVolunteer()) {
    		f.setTitle("Volunteer");
    	}    
    	
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		f.getContentPane().add(new UrbanParksUI());
		f.setSize(600, 280);
		f.pack();
		f.setVisible(true);
    }
    

    public static void loginUI() {
//        final JButton btnLogin = new JButton("Click to login");
// 
//        btnLogin.addActionListener(
//                new ActionListener(){
                   // public void actionPerformed(ActionEvent e) {
                        LoginDialog loginDlg = new LoginDialog(frame);
                        loginDlg.setVisible(true);
                        // if log-on successfully
                        if(loginDlg.isSucceeded()){
//                        	loginDlg.setTitle("WELCOME " +loginDlg.getUsername() );
                        	loginDlg.setVisible(false);
//                            btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
                        }
                    
                /*})*/;
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());
        //frame.getContentPane();
        frame.setVisible(true);
        closeFrame();
    }
    
    public static void closeFrame() {
    	frame.dispose();
    }
    
    public static void closeF() {
    	f.dispose();
    }
    
    public static void jobFrameDisplay() throws ClassNotFoundException, SQLException {
    	jobFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	jobFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
//    	jobFrame.getContentPane().add(new ParkJobsUI());
    	jobFrame.setSize(600, 280);
    	jobFrame.pack();
    	jobFrame.setVisible(true);
    }

}