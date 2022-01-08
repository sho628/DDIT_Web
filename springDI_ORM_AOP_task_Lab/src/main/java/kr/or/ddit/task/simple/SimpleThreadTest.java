package kr.or.ddit.task.simple;

import java.util.Timer;

public class SimpleThreadTest {
	public static void main(String[] args) {
		PrintNumberTask job = new PrintNumberTask();
		
		Timer timer = new Timer();
		timer.schedule(job, 0, 1000);
	}
}
