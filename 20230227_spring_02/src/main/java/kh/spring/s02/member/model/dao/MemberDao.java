package kh.spring.s02.member.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.s02.member.model.vo.MemberVo;

@Repository
public class MemberDao {
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(MemberVo vo) throws Exception {
		int result = -1;
//		try {
			result = sqlSession.insert("memberMapper.insertId", vo);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
		return result;
	}
	public int update(MemberVo vo) {
		return sqlSession.update("memberMapper.updateId", vo);
	}
	public int delete(String id) {
		return sqlSession.delete("memberMapper.deleteId", id);
	}
	public MemberVo selectOne(String id) {
		return sqlSession.selectOne("memberMapper.selectOneId", id);
	}
	public List<MemberVo> selectList() {
		return sqlSession.selectList("memberMapper.selectListId");
	}
}