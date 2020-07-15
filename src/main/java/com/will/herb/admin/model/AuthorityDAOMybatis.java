package com.will.herb.admin.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDAOMybatis implements AuthorityDAO{
	
	final String tablespace = "config.mybatis.mapper.oracle.authority.";
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public List<AuthorityVO> selectAuthority() {
		return sqlSession.selectList(tablespace+"selectAurhority");
	}
	
}
