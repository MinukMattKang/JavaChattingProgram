package client;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class StartClient extends JFrame {
	
	private JPanel contentPane;
	ImageIcon loginImg = new ImageIcon("img/login.png");
	private JLabel imgLabel;

	/**
	 * Create the frame.
	 */
	public StartClient() {
		
		super("Start");
		setVisible(true); // 창 오픈
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 400);
		setLocation(800,250);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton startButton = new JButton("시작");
		startButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		startButton.setBackground(Color.WHITE);
		startButton.setBounds(85, 260, 70, 25);
		contentPane.add(startButton);
		
		JButton exitButton = new JButton("종료");
		exitButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		exitButton.setBackground(Color.WHITE);
		exitButton.setBounds(85, 300, 70, 25);
		contentPane.add(exitButton);
		
		imgLabel = new JLabel(loginImg);
		imgLabel.setBounds(12, 10, 210, 246);
		contentPane.add(imgLabel);
		// 프레임 생성, 배치
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserFrame();
                setVisible(false);
			}
		}); // 시작 클릭시 UserFrame On, StartFrame Off 
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}); // 종료 클릭시 프로그램 종료
	}
	
	public static void main(String[] args) {
		new StartClient();
	}
}