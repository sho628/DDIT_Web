package kr.or.ddit.admin.service;

import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl {
	public String readLegacyData() {
		return "어디서든 사용할 수 있는 레거시 데이터_"+getClass().getSimpleName();
	}
}
