package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;


public class Temp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	Socket socket;

	public Temp() {
		
		new Client(socket);
		System.out.println("클라이언트 접속");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();  // 메세지 입력창
		textField.setBounds(12, 218, 292, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();  // 메세지 출력창
		textArea.setBounds(12, 10, 292, 198);
		contentPane.add(textArea);
		
		JTextArea listOfUser = new JTextArea();  // 참가자 리스트 출력창
		listOfUser.setBounds(316, 44, 106, 125);
		contentPane.add(listOfUser);
		
		JTextArea numberOfUser = new JTextArea();  // 참가자 인원수 출력창
		numberOfUser.setBounds(316, 10, 106, 24);
		contentPane.add(numberOfUser);
		
		JButton exitBtn = new JButton("나가기");
		exitBtn.setBounds(326, 179, 97, 23);
		contentPane.add(exitBtn);
		
		JButton sendBtn = new JButton("전송");
		sendBtn.setBounds(325, 217, 97, 23);
		contentPane.add(sendBtn);
		
		setVisible(true);
		setSize(450, 300);
	}
}
