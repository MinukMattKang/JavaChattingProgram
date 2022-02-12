package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageThread {

	Socket socket;
	String message;
	
	public MessageThread(Socket socket) {
		this.socket = socket;
		sendMessage(message);
		receiveMessage();
	}
	
	// 메세지 송신
	public void sendMessage(String message) {
		try {
			PrintWriter write = new PrintWriter(socket.getOutputStream());
			while (true) {
				write.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("메제지 송신 실패");
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// 메세지 수신
	public void receiveMessage() {
		try {
			BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
			while (true) {
				String message = read.readLine();
				if (message == null) {
					break;
				}
				System.out.println(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("메세지 수신 실패");
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
