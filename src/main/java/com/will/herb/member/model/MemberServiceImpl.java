package com.will.herb.member.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memberDao;

	public int insertMember(MemberVO vo) {
		return memberDao.insertMember(vo);
	}

	public int dupUserid(String userid) {
		int cnt = memberDao.dupUserid(userid);

		int result = 0;
		if (cnt > 0) { // 해당 아이디가 이미존재
			result = EXIST_ID;
		} else {
			result = NON_EXIST_ID;
		}
		return result;
	}

	public int loginCheck(String userid, String pwd) {
		String dbPwd = memberDao.selectPwd(userid);

		int result = 0;
		if (dbPwd != null && !dbPwd.isEmpty()) {
			if (dbPwd.equals(pwd)) {
				result = LOGIN_OK;
			} else {
				result = PWD_DISAGREE;
			}
		} else {
			result = ID_NONE;
		}
		
		return result;
	}
	
	public MemberVO selectMember(String userid) {
		return memberDao.selectMember(userid);
	}

	/*
	 * public MemberVO selectMember(String userid) throws SQLException { return
	 * memberDao.selectMember(userid); }
	 * 
	 * public int updateMember(MemberVO vo) throws SQLException { return
	 * memberDao.updateMember(vo); }
	 * 
	 * public int memberOut(String userid) throws SQLException { return
	 * memberDao.memberOut(userid); }
	 */
}
