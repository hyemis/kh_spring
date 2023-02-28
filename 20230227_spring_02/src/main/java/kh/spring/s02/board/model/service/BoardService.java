package kh.spring.s02.board.model.service;

import java.util.List;

import kh.spring.s02.board.model.vo.BoardVo;

public interface BoardService {

	public int insert(BoardVo vo);
//	public int updateForReply(int boardNum);

	public int update(BoardVo vo);
	
	public int delete(int boardNum/* BoardVo vo 또는 List<PK> */);

	public BoardVo selectOne(int boardNum, String wirter);
//	public int updateReadCount(int boardNum);
	
	public List<BoardVo> selectList();
	
	public int selectOneCount();
	
	
}
