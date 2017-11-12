package Cloud_GUI;

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
import java.awt.Window;
import java.awt.event.ActionEvent;

public class agreegatoradd extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					agreegatoradd frame = new agreegatoradd();
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
	public agreegatoradd(Socket sock) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(210, 50, 182, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(210, 110, 182, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(210, 174, 182, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAgreegatorId = new JLabel("Agreegator ID");
		lblAgreegatorId.setBounds(31, 53, 106, 14);
		contentPane.add(lblAgreegatorId);
		
		JLabel lblAadharNumber = new JLabel("Aadhar number");
		lblAadharNumber.setBounds(31, 113, 93, 14);
		contentPane.add(lblAadharNumber);
		
		JLabel lblGroupId = new JLabel("Group ID");
		lblGroupId.setBounds(31, 177, 46, 14);
		contentPane.add(lblGroupId);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Agreegator_ID = textField.getText();
				String UID = textField_1.getText();
				String GID = textField_2.getText();
				String Agreegator_data = Agreegator_ID + ":"+ UID + ":" + GID;
				BufferedWriter bw=null;
				try {
					bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
					bw.write(Agreegator_data);
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
				else
				{
					Entry_unsuccess us = new Entry_unsuccess();
					us.setVisible(true);
				}
				
			}
		});
		btnSubmit.setBounds(93, 227, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window dialog = SwingUtilities.windowForComponent( btnCancel );
				dialog.dispose();
			}
		});
		btnCancel.setBounds(239, 227, 89, 23);
		contentPane.add(btnCancel);
	}

}
