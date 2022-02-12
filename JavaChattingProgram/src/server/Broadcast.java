package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Broadcast extends Thread {

	static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());
	Socket socket;
	PrintWriter writer;

	Broadcast(Socket socket) {
		this.socket = socket;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			list.add(writer);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void run() {
		String name = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			name = reader.readLine();
			sendAll("#" + name + "님이 들어오셨습니다.");

			while (true) {
				String str = reader.readLine();
				if (str == null) {
					break;
				}
				sendAll(name + ">" + str);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			list.remove(writer);
			sendAll("#" + name + "님이 나가셨습니다.");
			try {
				socket.close();
			} catch (Exception ignored) {
			}
		}
	}

	private void sendAll(String str) {
		for (PrintWriter writer : list) {
			writer.println(str);
			writer.flush();
		}
	}
}
