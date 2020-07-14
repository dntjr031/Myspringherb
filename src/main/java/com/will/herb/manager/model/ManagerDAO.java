package com.will.herb.manager.model;

public interface ManagerDAO {
	public int selectDup(String userid);
	
	public int insertMgr(ManagerVO vo);
	
	public String selectPwd(String userid);
	
	public ManagerVO selectMgr(String userid);
}
