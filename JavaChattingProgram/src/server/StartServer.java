package server;

import java.io.IOException;
import java.net.*;

public class StartServer extends Thread {
	
	private ServerSocket serverSocket = null;

	public StartServer() {

		try {
			serverSocket = new ServerSocket(2515);
			System.out.println("연결 대기");
			this.run();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	
	public void run() {
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("연결 성공");
				Thread thread = new ConnectThread(socket);
				thread.start();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} // 소켓 생성
		}
	}
	
	public static void main(String[] args) {
		new StartServer();
	}
}