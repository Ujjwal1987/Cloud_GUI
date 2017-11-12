package Cloud_GUI;

import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class User_Registration extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private Socket login;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_Registration frame = new User_Registration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public User_Registration(Socket sock) {
		this.login = sock;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(75, 76, 65, 25);
		contentPane.add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(75, 134, 65, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblAadharNumber = new JLabel("Aadhar Number");
		lblAadharNumber.setBounds(75, 251, 93, 25);
		contentPane.add(lblAadharNumber);
		
		textField = new JTextField();
		textField.setBounds(210, 78, 168, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(210, 253, 168, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(210, 136, 168, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Retype Password");
		lblNewLabel_2.setBounds(75, 198, 84, 14);
		contentPane.add(lblNewLabel_2);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(210, 195, 168, 20);
		contentPane.add(passwordField_1);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField.getPassword());
				String repeat_password = new String(passwordField_1.getPassword());
				if(password.equals(repeat_password)){
					String username = textField.getText();
					String UID = textField_2.getText();
					Boolean Admin = true;
					String user_data = username + ":" + password + ":" + UID + ":" + Admin;
					BufferedWriter bw=null;
					
					try {
						bw = new BufferedWriter(new OutputStreamWriter(login.getOutputStream()));
						bw.write(user_data);
						bw.newLine();
						bw.flush();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					BufferedReader br = null;
					String success = null;
					try {
						br = new BufferedReader(new InputStreamReader(login.getInputStream()));
						success = br.readLine();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(success.equals("1")){
						Entry_success es = new Entry_success();
						es.setVisible(true);
					}
					else{
						Entry_unsuccess eu = new Entry_unsuccess();
						eu.setVisible(true);
					}
					
				}
			}
		});
		btnNewButton.setBounds(110, 337, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window dialog = SwingUtilities.windowForComponent( btnNewButton_1 );
				dialog.dispose();
			}
		});
		btnNewButton_1.setBounds(256, 337, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
