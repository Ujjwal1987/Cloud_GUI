package Cloud_GUI;

import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Groupadd extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton_1;
	private JLabel lblAadharNumberOf;
	private JTextField textField_2;
	private Socket login;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Groupadd frame = new Groupadd();
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
	public Groupadd(Socket sock) {
		this.login = sock;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGroupName = new JLabel("Group Name");
		lblGroupName.setBounds(81, 30, 71, 27);
		contentPane.add(lblGroupName);
		
		JLabel lblGroupId = new JLabel("Group ID");
		lblGroupId.setBounds(81, 89, 46, 14);
		contentPane.add(lblGroupId);
		
		textField = new JTextField();
		textField.setBounds(233, 33, 153, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(233, 86, 153, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(233, 148, 153, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Group_name = textField.getText();
				String Group_ID = textField_1.getText();
				String UID = textField_2.getText();
				String group_data = Group_name + ":" + Group_ID + ":" + UID;
				BufferedWriter bw=null;
				try {
					bw = new BufferedWriter(new OutputStreamWriter(login.getOutputStream()));
					bw.write(group_data);
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
				else if(success.equals("2")){
					User_notexist une = new User_notexist();
					une.setVisible(true);
				}
				else{
					Entry_unsuccess es = new Entry_unsuccess();
					es.setVisible(true);
				}
				
			}
		});
		
		
		
		btnNewButton.setBounds(81, 209, 89, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window dialog = SwingUtilities.windowForComponent( btnNewButton_1 );
				dialog.dispose();
			}
		});
		btnNewButton_1.setBounds(260, 209, 89, 23);
		contentPane.add(btnNewButton_1);
		
		lblAadharNumberOf = new JLabel("Aadhar number of Group Admin User");
		lblAadharNumberOf.setBounds(10, 140, 204, 37);
		contentPane.add(lblAadharNumberOf);
		

	}

}
