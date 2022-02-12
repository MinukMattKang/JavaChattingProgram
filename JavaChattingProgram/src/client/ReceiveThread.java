package client;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

public class ReceiveThread extends Thread {

	Socket socket;
	JButton count;
	JTextArea view;
	JList<String> userList;
	Vector<String> vector = new Vector<String>();

	ReceiveThread(Socket socket, JTextArea view, JButton count, JList<String> userList) {
		this.socket = socket;
		this.view = view;
		this.count = count;
		this.userList = userList;
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) {
				String str = reader.readLine();
				if (str == null) {
					break;
				} else if (str.charAt(0) == '/') {
					// '/c'로 시작하는 데이터는 인원수이므로 count에 출력
					if (str.charAt(1) == 'c') {
						count.setText("총 인원: " + str.substring(2) + "명");
						vector.removeAllElements(); // 인원수가 수정되면 명단 재출력
						// '/u'로 시작하는 데이터는 유저 참여 후 인원 명단
					} else if (str.charAt(1) == 'u') {
						vector.addElement(str.substring(2));
						userList.setListData(vector);
						// '/d'로 시작하는 데이터는 유저 퇴장 후 인원 명단
					} else if (str.charAt(1) == 'd') {
						vector.addElement(str.substring(2));
						userList.setListData(vector);
					} else {}
				} else {
					view.append(str + "\n");
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	} // ConnectThread에서 넘어온 데이터를 ChatFrame에 출력한다
}