package kr.or.ddit.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

/**
 * FreeReply 테이블을 대상으로 한 CRUD
 *
 */
@Repository
public interface ReplyDAO {
	public int insertReply(ReplyVO reply);
	public int selectTotalRecord(PagingVO<ReplyVO> pagingVO);
	public List<ReplyVO> selectReplyList(PagingVO<ReplyVO> pagingVO);
	public int updateReply(ReplyVO reply);
	public int deleteReply(ReplyVO reply);
}









