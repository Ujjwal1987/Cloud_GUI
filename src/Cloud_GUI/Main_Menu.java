package Cloud_GUI;

import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Main_Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Menu frame = new Main_Menu();
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
	public Main_Menu(Socket sock) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add New User");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BufferedWriter bw;
				try {
					bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
					bw.write("user_reg");
					bw.newLine();
					bw.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				User_Registration user = new User_Registration(sock);
				user.setVisible(true);				
			}
		});
		btnNewButton.setBounds(80, 61, 126, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add new Group");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
					bw.write("grp_reg");
					bw.newLine();
					bw.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Groupadd group = new Groupadd(sock);
				group.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(80, 126, 126, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Edit Records");
		btnNewButton_2.setBounds(80, 194, 126, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cancel");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedWriter bw;
				try {
					bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
					bw.write("exit");
					bw.newLine();
					bw.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Window dialog = SwingUtilities.windowForComponent( btnNewButton_3 );
				dialog.dispose();
			}
		});
		btnNewButton_3.setBounds(174, 259, 126, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnRegisterDevice = new JButton("Register Device");
		btnRegisterDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
					bw.write("device_reg");
					bw.newLine();
					bw.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Deviceadd da = new Deviceadd(sock);
				da.setVisible(true);
			}
		});
		btnRegisterDevice.setBounds(248, 93, 138, 23);
		contentPane.add(btnRegisterDevice);
		
		JButton btnNewButton_4 = new JButton("Register Agreegator");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
					bw.write("ag_reg");
					bw.newLine();
					bw.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				agreegatoradd da = new agreegatoradd(sock);
				da.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(248, 165, 138, 23);
		contentPane.add(btnNewButton_4);
	}
}
