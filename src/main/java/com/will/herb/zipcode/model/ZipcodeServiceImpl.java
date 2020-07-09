package com.will.herb.zipcode.model;

import java.sql.SQLException;
import java.util.List;

public class ZipcodeServiceImpl {

	private ZipcodeDAO dao;
	
	
	public List<ZipcodeVO> searchByDong(String dong) throws SQLException {
		return dao.searchByDong(dong);
	}
}
