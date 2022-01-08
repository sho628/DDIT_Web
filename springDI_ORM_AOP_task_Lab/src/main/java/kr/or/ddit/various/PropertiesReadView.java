package kr.or.ddit.various;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.vo.DBInfoVO;

public class PropertiesReadView {
	public static void main(String[] args) {
		Properties props = System.getProperties();
		for(Entry<Object, Object> entry : props.entrySet()) {
			System.out.printf("%s : %s \n", entry.getKey(), entry.getValue());
		}
		Map<String, String> env = System.getenv();
		for(Entry<String,String> entry : env.entrySet()) {
			System.err.printf("%s : %s \n", entry.getKey(), entry.getValue());
		}
		
		ConfigurableApplicationContext context =
				new GenericXmlApplicationContext("classpath:kr/or.ddit/various/conf/PropertiesRead.xml");
		context.registerShutdownHook();
		DBInfoVO dbInfoVO1 =  context.getBean("info1", DBInfoVO.class);
		System.out.println(dbInfoVO1);
		DBInfoVO dbInfoVO2 =  context.getBean("info2", DBInfoVO.class);
		System.out.println(dbInfoVO2);
		DBInfoVO dbInfoVO3 =  context.getBean("info3", DBInfoVO.class);
		System.out.println(dbInfoVO3);
	}
}
