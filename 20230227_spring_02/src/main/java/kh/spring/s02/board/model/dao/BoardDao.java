package kh.spring.s02.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.s02.board.controller.BoardController;
import kh.spring.s02.board.model.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession aa;
	
	public int insert(BoardVo vo) {
		return aa.insert("boardns.insertid", vo);		
	}
	public int update(BoardVo vo) {
		return aa.update("boardns.updateid", vo);		
	}
	public int updateReadCount(int boardNum) {
		return aa.update("boardns.updateReadCount", boardNum);		
	}
	public int updateForReply(int boardNum) {
		return aa.update("boardns.updateForReply", boardNum);		
	}
	public int delete(int boardNum  /* BoardVo vo 또는 PK 또는 List<PK>*/) {
		return aa.delete("boardns.deleteid", boardNum);		
	}
	public BoardVo selectOne(int boardNum /* PK */) {
		return aa.selectOne("boardns.selectOneid", boardNum);		
	}
	public List<BoardVo> selectList() {
		return aa.selectList("boardns.selectListid");		
	}
	public List<BoardVo> selectList(int currentPage, int limit) {
//		int offset =  (currentPage-1)*limit;		
//		RowBounds rb = new RowBounds(offset, limit);
//		return aa.selectList("boardns.selectListid", null, rb);		
		return aa.selectList("boardns.selectListid", null, new RowBounds((currentPage-1)*limit,limit));		
	}
	
	
	public List<BoardVo> selectList(int currentPage, int limit, String searchWord) {
		return aa.selectList("boardns.selectListid", searchWord, new RowBounds((currentPage-1)*limit,limit));		
	}
	
	public List<BoardVo> selectReplyList(int boardNum) {
		return  aa.selectList("boardns.selectReplyList", boardNum);	
	}
	
	public int selectOneCount() {
		return aa.selectOne("boardns.selectOneCount");
	}
	
	public int selectOneCount(String searchWord) {
		return aa.selectOne("boardns.selectOneCount", searchWord);
	}
//	public List<HashMap<String, Object>> tempSelect() {
	public List<BoardVo> tempSelect() {
//		return aa.selectList("boardns.tempSelect");
//		List<HashMap<String, Object>> listmap = aa.selectList("boardns.tempSelect");
//		for(HashMap<String, Object> map : listmap) {
//			System.out.println((String)map.get("boardDate"));
//		}
		// property = key = attribute = column = field
		List<BoardVo> volist = aa.selectList("boardns.tempSelect");
		for(BoardVo vo : volist) {
			System.out.println(vo.getBoardDate());
		}
		return volist;
	}
	/*
	 * public BoardVo selectList2() { return
	 * sqlSession.selectOne("boardns.selectListid2"); }
	 */

}
