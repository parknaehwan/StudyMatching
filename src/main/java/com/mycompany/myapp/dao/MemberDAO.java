package com.mycompany.myapp.dao;

import com.mycompany.myapp.domain.MemberDTO;

public interface MemberDAO {
	// 회원가입
	public abstract void register(MemberDTO dto) throws Exception;
		
	// 로그인
	public abstract MemberDTO login(MemberDTO dto) throws Exception;
	
	// 아이디 중복체크
	public abstract int idCheck(String id) throws Exception;
}
