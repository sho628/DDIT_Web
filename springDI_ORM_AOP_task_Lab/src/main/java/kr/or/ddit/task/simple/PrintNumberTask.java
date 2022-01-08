package kr.or.ddit.task.simple;

import java.util.TimerTask;

public class PrintNumberTask extends TimerTask{

	private int number;
	
	@Override
	public void run() {
		System.out.println(++number);
	}

}
