package com.mycompany.myapp.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mycompany.myapp.dao.MemberDAO;
import com.mycompany.myapp.domain.MemberDTO;


@Service
public class MemberServiceImpl implements MemberService{
	@Inject
	private MemberDAO dao;
	
	// 로그인
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		return dao.login(dto);
	}
		
	// 회원가입
	@Override
	public void register(MemberDTO dto) throws Exception {
		dao.register(dto);
	}
	
	//아이디 중복체크
	@Override
	public int idCheck(String id) throws Exception {
		return dao.idCheck(id);
	}
}
