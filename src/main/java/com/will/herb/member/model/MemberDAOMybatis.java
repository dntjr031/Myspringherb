package com.will.herb.member.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOMybatis implements MemberDAO {
	
	private static final String namespace = "config.mybatos.mapper.oracle.member.";
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int insertMember(MemberVO vo) {
		return sqlSession.insert(namespace+"insertMember",vo);
	}
	
	public int dupUserid(String userid) {
		return sqlSession.selectOne(namespace+"dupUserid", userid);
	}
	
	public String selectPwd(String userid) {
		return sqlSession.selectOne(namespace+"selectPwd", userid);
	}
	

	public MemberVO selectMember(String userid) {
		return sqlSession.selectOne(namespace+"selectMember", userid);
	}
	
	
	/*
	
	
	public int updateMember(MemberVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = pool.getConnection();
			
			String sql = "update member"
					+ " set zipcode=?, address=?, addressdetail=?, hp=?, email=?"
					+ " where userid=?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getZipcode());
			ps.setString(2, vo.getAddress());
			ps.setString(3, vo.getAddressDetail());
			ps.setString(4, vo.getHp());
			ps.setString(5, vo.getEmail());
			ps.setString(6, vo.getUserid());
			
			int cnt = ps.executeUpdate();
			System.out.println("수정결과 cnt=" + cnt + ", 매개변수 vo=" + vo);
			return cnt;
		} finally {
			pool.dbClose(con, ps);
		}
	}
	
	public int memberOut(String userid) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = pool.getConnection();
			
			String sql = "update member\r\n" + 
					"set outdate = sysdate\r\n" + 
					"where userid = ?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, userid);
			
			int cnt = ps.executeUpdate();
			
			System.out.println("탈퇴결과 cnt=" + cnt + ", 매개변수 userid=" + userid);
			return cnt;
		} finally {
			pool.dbClose(con, ps);
		}
	}
	*/
}
