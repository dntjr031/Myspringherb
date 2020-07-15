package com.will.herb.admin.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	
	@Autowired
	AuthorityDAO dao;
	
	public List<AuthorityVO> selectAuthority(){
		return dao.selectAuthority();
	}
}
