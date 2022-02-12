package client;

import java.net.Socket;

public class Client {
	
	public Socket socket;
	
	public Client(Socket socket) {
		this.socket = socket;
	}
	
	// 클라이언트 시작
	public void startClient(String IP, int port) {
		Runnable thread = new Runnable() {
			
			@Override
			public void run() {
				try {
					socket = new Socket("127.0.0.1", 9555);
					System.out.println("클라이언트 소켓 생성");
				} catch (Exception e) {
					e.printStackTrace();
					if (!socket.isClosed()) {
						stopClient();
						System.out.println("오류로 인한 클라이언트 종료");
					}
				}
			}
		};
		thread.run();
		new MessageThread(socket);
	}
	
	// 클라이언트 종료
	public void stopClient() {
		try {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("클라이언트 종료에서 오류 발생");
		}
	}
}
