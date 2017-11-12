package Cloud_GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Interface {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
			
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(50, 71, 61, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(50, 112, 46, 14);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(148, 68, 179, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(148, 109, 179, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				char[] temp_password = passwordField.getPassword();
				String password = new String(temp_password);
				Socket login = null;
				
				if(username.equals("") || password.equals("")){
					Login_Error error = new Login_Error();
					error.setVisible(true);
				}
				else{
				try {
					login = new Socket("192.168.1.4",8886);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					BufferedWriter bf1 = new BufferedWriter(new OutputStreamWriter(login.getOutputStream()));
					String msg = username + ":" + password;
					bf1.write(msg);
					bf1.newLine();
					bf1.flush();
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String line = new String();
				try {
					BufferedReader br1 = new BufferedReader(new InputStreamReader(login.getInputStream()));
					line = br1.readLine();
					System.out.println(line);
					if(line.equals("1")){
						Main_Menu m1 = new Main_Menu(login);
						m1.setVisible(true);
					}
					
					else{
						Login_Error error = new Login_Error();
						error.setVisible(true);
						//login.close();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				
				}
			}
		});
		btnLogin.setBounds(188, 176, 89, 23);
		frame.getContentPane().add(btnLogin);
		

	}
}
