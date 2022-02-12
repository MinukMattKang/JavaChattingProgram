package client;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

public class UserFrame extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JButton accessButton;
	private JLabel nameLabel;

	public UserFrame() {
		
		super("User");
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 130);
		setLocation(800,250);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name = new JTextField();
		contentPane.add(name);
		name.setBounds(50, 29, 165, 30);
		name.setColumns(10);
		name.requestFocus();
		
		accessButton = new JButton("접속");
		accessButton.setFont(new Font("돋움", Font.PLAIN, 12));
		accessButton.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(accessButton);
		accessButton.setBounds(227, 32, 65, 23);
		
		nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("돋움", Font.PLAIN, 12));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(nameLabel);
		nameLabel.setBounds(20, 36, 25, 15);
		// 프레임 생성, 배치
		
		accessButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = name.getText();
				if (str == null || str.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "이름을 입력하세요.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				new ChatFrame(name.getText());
				setVisible(false);
			}
		}); // 접속 클릭시 Client 실행, ChatFrame On, LoginFrame Off
		
		name.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = name.getText();
				if (str == null || str.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "이름을 입력하세요.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				new ChatFrame(name.getText());
				setVisible(false);
			}
		}); // Enter 입력시 Client 실행, ChatFrame On, LoginFrame Off
	}
}