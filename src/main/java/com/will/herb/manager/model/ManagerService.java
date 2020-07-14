package com.will.herb.manager.model;

public interface ManagerService {
	public int selectDup(String userid);
	
	public int insertMgr(ManagerVO vo);
	
	public int selectPwd(String userid, String pwd);
	
	public ManagerVO selectMgr(String userid);
}
