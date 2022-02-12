package server;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerFrame extends JFrame {
	
	String IP = "127.0.0.1";
	int port = 9555;

	public ServerFrame() {
		
		Server server = new Server();

		setTitle("[채팅서버]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JTextArea textArea = new JTextArea(10, 22);
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		contentPane.add(scrollPane);

		JButton bt = new JButton("시작하기");
		contentPane.add(bt);

		bt.addActionListener(event -> {
			if (bt.getText().equals("시작하기")) {
				server.startServer(IP, port);
				String message = String.format("[서버시작]\nIP: " + IP + "\tport: " + port + "\n");
				textArea.append(message);
				bt.setText("종료하기");
			} else {
				server.stopServer();
				String message = String.format("[서버종료]\nIP: " + IP + "\tport: " + port + "\n");
				textArea.append(message);
				bt.setText("시작하기");
			}
		});

		setSize(300, 250);
		setVisible(true);
	}

}
