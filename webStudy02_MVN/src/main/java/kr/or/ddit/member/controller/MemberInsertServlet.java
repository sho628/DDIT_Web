package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert.do")
public class MemberInsertServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String viewName = "member/memberForm";
		
		String prefix = "/WEB-INF/views/";
		String suffix = ".jsp";
		req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		//		1. 파라미터 확보 -> MemberVO
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
//		String memId = req.getParameter("memId");
//		member.setMemId(memId);
		
		Map<String, String[]> paramterMap = req.getParameterMap();
		try {
			BeanUtils.populate(member, paramterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
//		2. 검증 : DB스키마에 따른 검증 룰
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member, errors);
		
		String viewName = null;
		String message = null;
		if(valid) {
//		3-1. 통과
//			로직 사용
			ServiceResult result = service.createMember(member);
			switch(result) {
			case PKDUPLICATED:
//			PK중복 : memberForm 으로 이동(기존 데이터 + 메시지 전달).
				viewName = "member/memberForm";
				message = "아이디 중복";
				break;
			case OK:
//			OK : 웰컴 페이지로 이동
				viewName = "redirect:/";
				break;
			default:
//			FAIL : memberForm 으로 이동(기존 데이터 + 메시지 전달).
				viewName = "member/memberForm";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
			
		}else {
//		3-2. 불통
//			: memberForm으로 이동 (기존데이터 + 검증 결과 메시지 전달)
			viewName = "member/memberForm";
			
		}
		
		req.setAttribute("message", message);
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
		}
		
 	}

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(member.getMemId())) {
			valid = false;
			errors.put("memId", "회원아이디가(이) 누락되었음.");
		}
		if (StringUtils.isBlank(member.getMemPass())) {
			valid = false;
			errors.put("memPass", "비밀번호가(이) 누락되었음.");
		}
		if (StringUtils.isBlank(member.getMemName())) {
			valid = false;
			errors.put("memName", "회원명가(이) 누락되었음.");
		}
		if (StringUtils.isBlank(member.getMemRegno1())) {
			valid = false;
			errors.put("memRegno1", "주민번호1가(이) 누락되었음.");
		}
		if (StringUtils.isBlank(member.getMemRegno2())) {
			valid = false;
			errors.put("memRegno2", "주민번호2가(이) 누락되었음.");
		}
		if (StringUtils.isBlank(member.getMemZip())) {
			valid = false;
			errors.put("memZip", "우편번호가(이) 누락되었음.");
		}
		if (StringUtils.isBlank(member.getMemAdd1())) {
			valid = false;
			errors.put("memAdd1", "주소1가(이) 누락되었음.");
		}
		if (StringUtils.isBlank(member.getMemAdd2())) {
			valid = false;
			errors.put("memAdd2", "주소2가(이) 누락되었음.");
		}
		if (StringUtils.isBlank(member.getMemHometel())) {
			valid = false;
			errors.put("memHometel", "집전번가(이) 누락되었음.");
		}
		if (StringUtils.isBlank(member.getMemComtel())) {
			valid = false;
			errors.put("memComtel", "회사전번가(이) 누락되었음.");
		}
		if (StringUtils.isBlank(member.getMemMail())) {
			valid = false;
			errors.put("memMail", "이메일가(이) 누락되었음.");
		}
		return valid;
	}
}














