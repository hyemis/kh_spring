package kh.spring.s02.member.model.service;

import java.util.List;

import kh.spring.s02.member.model.vo.MemberVo;

public interface MemberService {
	public int insert(MemberVo vo) throws Exception;
	public int update(MemberVo vo) ;
	public int delete(String id);
	public MemberVo selectOne(String id);
	public List<MemberVo> selectList();
}