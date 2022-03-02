package com.mycompany.myapp.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.domain.MemberDTO;


@Repository
public class MemberDAOImpl implements MemberDAO{
	@Inject
	private SqlSession sqlSession;
	
	// 로그인
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		return sqlSession.selectOne("memberMapper.login", dto);
	}
		
	// 회원가입
	@Override
	public void register(MemberDTO dto) throws Exception {
		sqlSession.insert("memberMapper.register", dto);	
	}
	
	@Override
	public int idCheck(String id) throws Exception {
		int result = sqlSession.selectOne("memberMapper.idCheck", id);
		return result;
	}
}
