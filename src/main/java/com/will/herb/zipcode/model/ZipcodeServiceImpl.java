package com.will.herb.zipcode.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZipcodeServiceImpl implements ZipcodeService{

	@Autowired
	private ZipcodeDAO dao;
	
	
	public List<ZipcodeVO> searchByDong(String dong){
		return dao.searchByDong(dong);
	}
}
