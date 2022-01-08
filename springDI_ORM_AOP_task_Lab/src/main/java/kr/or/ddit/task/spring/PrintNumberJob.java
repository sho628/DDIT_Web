package kr.or.ddit.task.spring;

import org.springframework.scheduling.annotation.Scheduled;

public class PrintNumberJob{
	private int number;
	
	@Scheduled(cron="0 0 3 * * MON")
	public void execute() {
		System.out.printf("%d - %s\n", ++number, Thread.currentThread().getName());
	}
}
