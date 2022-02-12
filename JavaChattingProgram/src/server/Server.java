package server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.Client;

public class Server {

	public static ExecutorService threadPool;
	public static Vector<Client> clients = new Vector<Client>();
	private Socket socket;
	private ServerSocket serverSocket;
	
	public Server() {
		
	}
	
	// 서버 시작
	public void startServer(String IP, int port) {
		try {
			serverSocket = new ServerSocket();
			System.out.println("서버 정상 실행");
			serverSocket.bind(new InetSocketAddress(IP, port));
		} catch (Exception e) {
			e.printStackTrace();
			if (!serverSocket.isClosed()) {
				stopServer();
				System.out.println("오류로 인해 서버 종료");
			}
		}
		
		// 클라이언트가 접속할 때까지 기다리는 스레드
		Runnable thread = new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("클라이언트 접속 대기");
						socket = serverSocket.accept();
						Client user = new Client(socket);
						clients.add(user);
						System.out.println("[클라이언트 연결]" + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName());
					} catch (Exception e) {
						if (!serverSocket.isClosed()) {
							stopServer();
						}
						break;
					}
				}
				
			}
		};
		threadPool = Executors.newCachedThreadPool();
		threadPool.submit(thread);
	}
	
	// 서버 종료
	public void stopServer() {
		try {
			// 현재 작동 중인 모든 소켓 닫기
				Iterator<Client> iterator = clients.iterator();
				while (iterator.hasNext()) {
					Client client = iterator.next();
					client.socket.close();
					iterator.remove();
					System.out.println("클라이언트 정보 삭제");
				}
				// 서버 소켓 객체 닫기
				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
					System.out.println("서버 소켓 종료");
				}
				// 스레드 풀 종료하기
				if (threadPool != null && !threadPool.isShutdown()) {
					threadPool.shutdown();
					System.out.println("서버 스레드풀 종료");
					System.out.println("서버 정상 종료");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
