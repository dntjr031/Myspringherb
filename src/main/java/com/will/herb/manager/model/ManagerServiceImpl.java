package com.will.herb.manager.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.will.herb.member.model.MemberService;


@Service
public class ManagerServiceImpl implements ManagerService{
	
	@Autowired
	ManagerDAO dao;
	
	public int selectDup(String userid) {
		int result = 0;
		int check = dao.selectDup(userid);
		if(check == 1) {
			result = MemberService.EXIST_ID;
		}else {
			result = MemberService.NON_EXIST_ID;
		}
		
		return result;
	}
	
	public int insertMgr(ManagerVO vo) {
		return dao.insertMgr(vo);
	}
	
	public int selectPwd(String userid, String pwd) {
		String userPwd = dao.selectPwd(userid);
		
		int result = MemberService.ID_NONE;
		if(userPwd != null && !userPwd.isEmpty()) {
			if(userPwd.equals(pwd)) {
				result = MemberService.LOGIN_OK;
			}else {
				result = MemberService.PWD_DISAGREE;
			}
		}
		
		return result;
	}
	
	public ManagerVO selectMgr(String userid) {
		return dao.selectMgr(userid);
	}
}	
