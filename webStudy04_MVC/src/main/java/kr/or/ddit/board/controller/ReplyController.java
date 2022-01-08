package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.ReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

// /reply/replyList.do
// /reply/replyInsert.do
// /reply/replyUpdate.do
// /reply/replyDelete.do
@Controller
public class ReplyController {
	private ReplyService service = new ReplyServiceImpl();
	
	@RequestMapping(value="/reply/replyInsert.do", method=RequestMethod.POST)
	public String replyInsert(
		@ModelAttribute("reply") ReplyVO reply
		, HttpServletResponse resp
	)throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		ServiceResult result = service.createReply(reply);
		if(ServiceResult.FAILED.equals(result)) {
			resultMap.put("message", "서버 오류");
		}
		resultMap.put("result", result.name());
		resultMap.put("reply", reply);
		
		resp.setContentType("application/json;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();	
		){
			new ObjectMapper().writeValue(out, resultMap);
		}
		
		return null;
	}
	
	@RequestMapping("/reply/replyList.do")
	public String replyList(
		@RequestParam("boNo") int boNo 
		, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, HttpServletResponse resp
	) throws IOException {
		PagingVO<ReplyVO> pagingVO = new PagingVO<>(5, 5);
		pagingVO.setCurrentPage(currentPage);
		
		ReplyVO detailSearch = new ReplyVO();
		detailSearch.setBoNo(boNo);
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrieveReplyList(pagingVO);
		
		resp.setContentType("application/json;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();	
		){
			new ObjectMapper().writeValue(out, pagingVO);
		}
		
		return null;
	}
}
