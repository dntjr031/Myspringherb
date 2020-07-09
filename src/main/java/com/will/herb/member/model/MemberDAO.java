package com.will.herb.member.model;

public interface MemberDAO {
	public int insertMember(MemberVO vo);
	
	public int dupUserid(String userid);
	
	public String selectPwd(String userid);
	
	public MemberVO selectMember(String userid);
}
