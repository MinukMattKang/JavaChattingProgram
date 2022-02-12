package client;

import java.awt.event.*;
import java.io.*;
import java.net.*;

public class SendThread extends Thread {

	Socket socket;
	String name;
	ChatFrame chat;

	SendThread(Socket socket, String name, ChatFrame chat) {
		this.socket = socket;
		this.name = name;
		this.chat = chat;
	}

	public void run() {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println(name);
			writer.flush();
			chat.sendButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String text = chat.send.getText();
					if (text.equalsIgnoreCase("/out")) {
						try {
							socket.close();
							System.exit(0);
						} catch (IOException e1) {}
					} else {}
					writer.println(text);
					writer.flush();
					chat.send.setText(null);
					chat.scroll.getVerticalScrollBar().setValue(chat.scroll.getVerticalScrollBar().getMaximum());
				}
			});
			chat.send.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String text = chat.send.getText();
					if (text.equalsIgnoreCase("/out")) {
						try {
							socket.close();
							System.exit(0);
						} catch (IOException e1) {}
					} else {}
					writer.println(text);
					writer.flush();
					chat.send.setText(null);
					chat.scroll.getVerticalScrollBar().setValue(chat.scroll.getVerticalScrollBar().getMaximum());
				}
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}