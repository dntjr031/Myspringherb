package com.will.herb.zipcode.model;

import java.util.List;

public interface ZipcodeDAO {
	List<ZipcodeVO> searchByDong(String dong);
}
