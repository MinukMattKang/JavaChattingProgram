package client;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import server.TimeThread;

public class ChatFrame extends JFrame {

	private String userName;
	private JPanel contentPane;
	private JButton listButton;
	private JButton count;
	private JLabel todayLabel;
	private JLabel lblNewLabel;
	private JList<String> userList;
	private JTextArea view;
	JTextField send;
	JButton sendButton;
	JScrollPane scroll;

	/**
	 * Create the frame.
	 */
	public ChatFrame(String name) {
		
		super("Chat");
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 530);
		setLocation(800, 250);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		sendButton = new JButton("전송");
		sendButton.setFont(new Font("돋움", Font.PLAIN, 12));
		sendButton.setBackground(SystemColor.inactiveCaptionBorder);
		sendButton.setBounds(302, 418, 70, 25);
		contentPane.add(sendButton);

		listButton = new JButton("접속자");
		listButton.setBackground(SystemColor.inactiveCaptionBorder);
		listButton.setForeground(SystemColor.desktop);
		listButton.setFont(new Font("돋움", Font.PLAIN, 12));
		listButton.setHorizontalAlignment(SwingConstants.CENTER);
		listButton.setBounds(302, 20, 70, 25);
		contentPane.add(listButton);

		send = new JTextField();
		send.setFont(new Font("돋움", Font.PLAIN, 12));
		send.setBounds(12, 411, 278, 40);
		contentPane.add(send);
		send.setColumns(10);
		send.setForeground(SystemColor.GRAY);
		send.setText("/ 로 시작하는 문구는 입력하지 마시오.");
		send.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (send.getText().equals("/ 로 시작하는 문구는 입력하지 마시오.")) {
					send.setText("");
					send.setForeground(Color.BLACK);
				} else {}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (send.getText().isEmpty()) {
					send.setText("/ 로 시작하는 문구는 입력하지 마시오.");
					send.setForeground(Color.GRAY);
				} else {}
			}
		});

		view = new JTextArea();
		view.setForeground(SystemColor.desktop);
		view.setFont(new Font("돋움", Font.PLAIN, 13));
		scroll = new JScrollPane(view);
		scroll.setBounds(12, 50, 278, 351);
		contentPane.add(scroll);
//		view.setBounds(12, 50, 278, 351);
//		contentPane.add(view);

		userList = new JList<String>();
		userList.setForeground(SystemColor.desktop);
		userList.setFont(new Font("돋움", Font.PLAIN, 12));
		JScrollPane scroll2 = new JScrollPane(userList);
		scroll2.setBounds(302, 50, 70, 351);
		contentPane.add(scroll2);
//		userList.setBounds(302, 50, 70, 351);
//		contentPane.add(userList);

		count = new JButton();
		count.setForeground(SystemColor.desktop);
		count.setFont(new Font("돋움", Font.PLAIN, 12));
		count.setBackground(SystemColor.inactiveCaptionBorder);
		count.setBounds(12, 20, 278, 25);
		contentPane.add(count);

		todayLabel = new JLabel("");
		todayLabel.setForeground(SystemColor.activeCaption);
		todayLabel.setFont(new Font("돋움", Font.PLAIN, 12));
		todayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		todayLabel.setBounds(187, 466, 185, 15);
		contentPane.add(todayLabel);
		todayLabel.requestFocus();

		lblNewLabel = new JLabel("※ '/out' 입력시 퇴장");
		lblNewLabel.setFont(new Font("돋움", Font.PLAIN, 12));
		lblNewLabel.setForeground(SystemColor.activeCaption);
		lblNewLabel.setBounds(15, 466, 120, 15);
		contentPane.add(lblNewLabel);
		// 프레임 생성, 배치

		this.userName = name;

		try {
			Socket socket = new Socket("127.0.0.1", 2515);
			Thread sdThr = new SendThread(socket, userName, this);
			Thread rcThr = new ReceiveThread(socket, view, count, userList);
			sdThr.start();
			rcThr.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} // 프레임 오픈과 동시에 클라이언트 실행

		// 현재 시각 표시 스레드
		new TimeThread(todayLabel).start();
	}
}