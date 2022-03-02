package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.MemberDTO;

public interface MemberService {
	//회원가입
	public void register(MemberDTO dto) throws Exception;
	
	// 로그인
	public MemberDTO login(MemberDTO dto) throws Exception;	
	
	// 아이디 중복체크
	public int idCheck(String id) throws Exception;
}
