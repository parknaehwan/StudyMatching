package com.mycompany.myapp.domain;

import java.util.Date;

public class ArticleVO {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private MemberDTO memberDTO;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public MemberDTO getMemberDto() {
		return memberDTO;
	}

	public void setMemberDto(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}
}
