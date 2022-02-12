package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ConnectThread extends Thread {
	
	static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());
	static List<String> userList = Collections.synchronizedList(new ArrayList<String>());
	private Socket socket;
	private PrintWriter writer;
	
	public ConnectThread(Socket socket) {
		this.socket = socket;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			list.add(writer); // list에 사용자 추가
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void sendAll(String str) {
		// 각 소켓에 데이터 보내기
		for (PrintWriter writer : list) {
			writer.println(str);
			writer.flush();
		}
	}
	
	public void run() {
		String name = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			name = reader.readLine();
			userList.add(name); // 배열에 접속자 이름 저장
			sendAll("@" + name + " 님 입장");
			sendAll("/c" + list.size()); // list에 저장된 소켓 수 = 인원 수
			for (int i = 0; i < userList.size(); i++) {
				sendAll("/u" + userList.get(i));
			} // userList에 저장된 접속자 이름
			while (true) {
				String str = reader.readLine();
				if (str == null) {
					break;
				} else {}
				sendAll(name + ": " + str);
			} // 소켓 연결 중에는 계속 유저로부터 메세지를 받아서 출력
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			list.remove(writer); // 소켓 제거
			for (int i = 0; i<userList.size(); i++) {
				if (userList.get(i).equals(name)) {
					userList.remove(i);
					break;
				}
			} // 제거된 소켓과 매치되는 접속자 이름 제거
			sendAll("@" + name + " 님 퇴장");
			sendAll("/c" + list.size());
			for (int i = 0; i < userList.size(); i++) {
				sendAll("/d" + userList.get(i));
			} // 접속자 명단 재출력 데이터
			try {
				socket.close();
			} catch (Exception ignored) {}
		}
	} // 받아온 데이터를 sendAll을 통해 ReceiveThread로 보낸다
}