package kr.or.ddit.task.quartz;

public class PrintNumberQuartzJob {
	private int number;
	
	public void execute() {
		System.out.printf("%d - %s\n", ++number, Thread.currentThread().getName());
	}
}
