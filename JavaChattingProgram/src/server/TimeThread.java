package server;

import java.text.*;
import java.util.*;
import javax.swing.*;

public class TimeThread extends Thread {
	
	JLabel todayLabel;
	
	public TimeThread(JLabel todayLabel) {
		this.todayLabel = todayLabel;
	}
	
	@Override
	public void run() {
		
		while (true) {
			long time = System.currentTimeMillis();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
			String today = dateFormat.format(time);
			todayLabel.setText(today);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}