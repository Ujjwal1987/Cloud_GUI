package Cloud_GUI;

import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Deviceadd extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deviceadd frame = new Deviceadd();
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
	public Deviceadd(Socket sock) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(205, 54, 169, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(205, 118, 169, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDeviceId = new JLabel("Device ID");
		lblDeviceId.setBounds(28, 57, 46, 14);
		contentPane.add(lblDeviceId);
		
		JLabel lblNewLabel = new JLabel("Device Application ID");
		lblNewLabel.setBounds(28, 121, 101, 14);
		contentPane.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(205, 181, 169, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(205, 248, 169, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblAadharNumberNumber = new JLabel("Aadhar number number of User");
		lblAadharNumberNumber.setBounds(28, 184, 167, 14);
		contentPane.add(lblAadharNumberNumber);
		
		JLabel lblGroupId = new JLabel("Group ID");
		lblGroupId.setBounds(28, 254, 46, 14);
		contentPane.add(lblGroupId);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Device_ID = textField.getText();
				String Device_Application_ID = textField_1.getText();
				String UID = textField_2.getText();
				String GID = textField_3.getText();
				String device_data = Device_ID + ":"+ "null" + ":" + Device_Application_ID + ":" + UID + ":" + GID;
				BufferedWriter bw=null;
				try {
					bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
					bw.write(device_data);
					bw.newLine();
					bw.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				BufferedReader br = null;
				String success = null;
				try {
					br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
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
				else if(success.equals("3")){
					Group_notexist gne = new Group_notexist();
					gne.setVisible(true);
				}
				else{
					Entry_unsuccess us = new Entry_unsuccess();
					us.setVisible(true);
				}
			}
		});
		
		
		btnSubmit.setBounds(87, 308, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window dialog = SwingUtilities.windowForComponent( btnCancel );
				dialog.dispose();
			}
		});
		btnCancel.setBounds(273, 308, 89, 23);
		contentPane.add(btnCancel);
		

	}
}
