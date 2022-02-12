package chatting;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.Client;
import client.MessageThread;

public class ChattingFrame extends JFrame {

	JTextArea sendTA = new JTextArea(7, 23);
	JTextField nameTF = new JTextField(7);
	Socket socket;

	public ChattingFrame() {

		MessageThread mc = new MessageThread(socket);
		Client client = new Client(socket);

		setTitle("화면구현샘플");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		JPanel cardBox = new JPanel();
		CardLayout card = new CardLayout();
		contentPane.add(cardBox);
		cardBox.setLayout(card);
		cardBox.setBounds(5, 5, 325, 500);

		JPanel mainScreen = new JPanel(); // 메인 페이지
		mainScreen.setLayout(null);
		mainScreen.setBackground(Color.RED);
		mainScreen.setBounds(5, 5, 325, 500);

		JPanel signupScreen = new JPanel(); // 채팅 페이지
		signupScreen.setLayout(null);
		signupScreen.setBackground(Color.BLUE);
		signupScreen.setBounds(5, 5, 325, 500);

		JPanel chattingScreen = new JPanel(); // 계정생성 페이지
		chattingScreen.setLayout(null);
		chattingScreen.setBackground(Color.GREEN);
		chattingScreen.setBounds(5, 5, 325, 500);

		cardBox.add("mainScreen", mainScreen);
		cardBox.add("signupScreen", signupScreen);
		cardBox.add("chattingScreen", chattingScreen);

		// <메인 페이지>

		JButton loginButton = new JButton("로그인");
		mainScreen.add(loginButton);
		loginButton.setBounds(65, 400, 90, 30);

		JButton signupButton = new JButton("회원가입");
		mainScreen.add(signupButton);
		signupButton.setBounds(175, 400, 90, 30);

		JLabel jl1 = new JLabel(" 아 이 디  : ");
		mainScreen.add(jl1);
		jl1.setBounds(65, 300, 90, 30);

		JLabel jl2 = new JLabel("비밀번호 : ");
		mainScreen.add(jl2);
		jl2.setBounds(65, 340, 90, 30);

		JTextField tf1 = new JTextField("아무 내용이나 입력해주세요."); // 아이디 입력 창
		mainScreen.add(tf1);
		tf1.setBounds(135, 300, 130, 30);

		JPasswordField tf2 = new JPasswordField(); // 비밀번호 입력창
		mainScreen.add(tf2);
		tf2.setBounds(135, 340, 130, 30);

		// <계정생성 페이지>

		JButton makeButton = new JButton("계정생성");
		signupScreen.add(makeButton);
		makeButton.setBounds(65, 400, 90, 30);

		// 기능 미구현

		JButton backButton1 = new JButton("뒤로가기");
		signupScreen.add(backButton1);
		backButton1.setBounds(175, 400, 90, 30);

		// <채팅 페이지>

		// < 채팅 페이지 - 상단 >
		JPanel csTop = new JPanel();
		chattingScreen.add(csTop);
		csTop.setLayout(null);
		csTop.setBounds(5, 5, 315, 130);

		JLabel nameLabel = new JLabel("닉네임 : ");
		csTop.add(nameLabel);
		nameLabel.setBounds(7, 7, 70, 30);

//		JTextField nameTF = new JTextField(7);  // 닉네임 생성
		csTop.add(nameTF);
		nameTF.setBounds(55, 7, 70, 30);

		JButton nameButton = new JButton("저장");
		csTop.add(nameButton);
		nameButton.setBounds(130, 7, 70, 30);

		JButton logoutButton = new JButton("로그아웃");
		csTop.add(logoutButton);
		logoutButton.setBounds(218, 7, 90, 30);

		JTextField userTF = new JTextField("기능 미구현"); // 참가인원
		csTop.add(userTF);
		userTF.setBounds(70, 39, 70, 30);
		userTF.setEditable(false);

		JLabel listLabel = new JLabel("참가인원 :                           명");
		csTop.add(listLabel);
		listLabel.setBounds(7, 40, 300, 30);

		JTextArea listTA = new JTextArea("기능미구현");
		JScrollPane scrollPaneList = new JScrollPane(listTA);

//		JTextArea listTA = new JTextArea();
		csTop.add(scrollPaneList);
		scrollPaneList.setBounds(7, 70, 300, 52);
		listTA.setEditable(false);

		// < 채팅 페이지 - 하단 >
		JPanel csCenter = new JPanel();
		chattingScreen.add(csCenter);
		csCenter.setLayout(null);
		csCenter.setBounds(5, 140, 315, 355);

		JScrollPane scrollPane = new JScrollPane(sendTA); // 텍스트창
		sendTA.setEditable(false);
		sendTA.setLineWrap(true); // 자동 줄바꿈
		csCenter.add(scrollPane);
		scrollPane.setBounds(5, 5, 305, 300);

		JTextField sendTF = new JTextField(7);
		csCenter.add(sendTF);
		sendTF.setBounds(5, 310, 210, 30);
		sendTF.setEditable(false);

		JButton sendButton = new JButton("보내기");
		csCenter.add(sendButton);
		sendButton.setBounds(218, 310, 90, 30);

		JTextField IPText = new JTextField("127.0.0.1");
		JTextField portText = new JTextField("9876");
		csCenter.add(IPText);
		csCenter.add(portText);
		IPText.setEditable(false);
		portText.setEditable(false);

		// <기능 구현>
		// ① 페이지 이동
		// 메인 페이지 -> 채팅 페이지
		loginButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				card.show(cardBox, "chattingScreen");
				JOptionPane.showMessageDialog(null, "닉네입을 입력후 사용해주세요.", "알림", JOptionPane.QUESTION_MESSAGE);
				nameTF.setEditable(true);
			}
		});

		// 메인 페이지 -> 계정생성 페이지
		signupButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "구현되지 않은 기능입니다.", "오류", JOptionPane.ERROR_MESSAGE);
				card.show(cardBox, "signupScreen");
			}
		});

		// 계정생성 페이지 -> 메인 페이지
		backButton1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				card.show(cardBox, "mainScreen");
			}
		});
			
			
		
		logoutButton.addMouseListener(new MouseAdapter() {  // 로그아웃버튼 클릭
			public void mousePressed(MouseEvent e) {
				mc.sendMessageToServer("   ### " + nameTF.getText() + "님이 퇴장하였습니다. ###");
				card.show(cardBox, "mainScreen");
				sendTA.setText("");
				nameTF.setText("");
				nameButton.setText("저장");
				sendTF.setEditable(false);
				client.stopClient();
			}
		});
		
		nameButton.addMouseListener(new MouseAdapter() {  // 저장버튼 클릭
			public void mousePressed(MouseEvent e) {
				if (nameButton.getText().equals("저장")) {
					if(nameTF.getText().length() < 1) {
						JOptionPane.showMessageDialog(null, "사용할 이름을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
						nameTF.setEditable(true);
						sendTF.setEditable(false);
						client.stopClient();
					} else {
						int port = 9876;
						try {
							port = Integer.parseInt(portText.getText());
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						client.startClient(IPText.getText(), port);
						nameButton.setText("수정");
						nameTF.setEditable(false);
						sendTF.setEditable(true);
						

						
						
						listLabel.setText("참가인원 : " );
						mc.sendMessageToServer("   ### " + nameTF.getText() + "님이 입장하였습니다. ###");
						
						
						
						
						sendTF.requestFocus();
					}
					
					} else {
						nameButton.setText("저장");
						nameTF.setEditable(true);
						sendTF.setEditable(false);
						client.stopClient();
					}
			}

			
		});
		
		sendTF.addActionListener(new ActionListener() {  // 메세지 전송1
			public void actionPerformed(ActionEvent e) {
				mc.sendMessageToServer(nameTF.getText() + " >> " + sendTF.getText());
				sendTF.setText("");
				sendTF.requestFocus();
			}
		});
		
		sendButton.addMouseListener(new MouseAdapter() {  // 메세지 전송2
			public void mousePressed(MouseEvent e) {
				mc.sendMessageToServer(nameTF.getText() + " >> " + sendTF.getText());
				sendTF.setText("");
				sendTF.requestFocus();
			}
		});

		setLocation(700, 300);
		setSize(350, 550);
		setVisible(true);

	}

}
