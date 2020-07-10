package com.will.herb.member.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOMybatis implements MemberDAO {
	
	private static final String namespace = "config.mybatos.mapper.oracle.member.";
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int insertMember(MemberVO vo) {
		return sqlSession.insert(namespace+"insertMember",vo);
	}
	
	public int dupUserid(String userid) {
		return sqlSession.selectOne(namespace+"dupUserid", userid);
	}
	
	public String selectPwd(String userid) {
		return sqlSession.selectOne(namespace+"selectPwd", userid);
	}
	

	public MemberVO selectMember(String userid) {
		return sqlSession.selectOne(namespace+"selectMember", userid);
	}
	
	public int updateMember(MemberVO vo){
		return sqlSession.update(namespace+"updateMember",vo);
	}

	public int memberOut(String userid) {
		return sqlSession.update(namespace+"memberOut",userid);
	}
	
}
