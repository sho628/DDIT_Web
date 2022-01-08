package kr.or.ddit.sample.service;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAOFactory;

@Service
public class SampleServiceImpl implements SampleService {
//	1. new 키워드로 의존객체의 인스턴스를 직접 생성. 결합력 최상
//	SampleDAO dao = new SampleDAO_Oracle();
//	SampleDAO dao = new SampleDAO_Mysql();
//	2. Factory object pattern
//	SampleDAO dao = SampleDAOFactory.getSampleDAO();
//	3. Strategy pattern -> DI(Dependency Injection - setter injection)
//	4. DI 컨테이너 활용
	private SampleDAO dao;
	
	public SampleServiceImpl() {
		System.out.println(getClass().getSimpleName()+" 객체 생성, 기본 생성자");
	}

	
	public SampleServiceImpl(SampleDAO dao) {
		super();
		this.dao = dao;
		System.out.println(String.format("%s 객체 생성, %s 주입\n", 
							getClass().getSimpleName(), dao.getClass().getSimpleName()));
	}

	@Required
//	@Autowired
//	@Resource(name="sampleDAO_Mysql")
	@Inject
	public void setDaoaa(SampleDAO dao) {
		this.dao = dao;
		System.out.printf("%s setter injection\n", dao.getClass().getSimpleName());
	}
	
	public void init() {
		System.out.println(getClass().getSimpleName()+" 초기화 종료");
	}
	
	public void destroy() {
		System.out.println(getClass().getSimpleName()+" 소멸");
	}

	@Override
	public StringBuffer retrieveSampleData(String pk) {
		String rawData = dao.selectSampleData(pk);
		StringBuffer info = new StringBuffer(rawData);
		info.append(String.format(", 조회된 시점 : %tc", Calendar.getInstance()));
		return info;
	}

}
