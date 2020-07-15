package com.will.herb.manager.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManagerDAOmybatis implements ManagerDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	final String tablespace = "config.mybatis.mapper.oracle.manager.";
	
	public int selectDup(String userid) {
		return sqlSession.selectOne(tablespace+"selectDup",userid);
	}
	
	public int insertMgr(ManagerVO vo) {
		return sqlSession.insert(tablespace+"insertMgr",vo);
	}
	
	public String selectPwd(String userid) {
		return sqlSession.selectOne(tablespace+"selectPwd", userid);
	}
	
	public ManagerVO selectMgr(String userid) {
		return sqlSession.selectOne(tablespace+"selectMgr",userid);
	}
}
