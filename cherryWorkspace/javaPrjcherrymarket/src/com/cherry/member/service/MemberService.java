package com.cherry.member.service;

import java.sql.*;

import com.cherry.jdbc.JDBCTemplate;
import com.cherry.member.dao.MemberDao;
import com.cherry.member.vo.MemberVo;

public class MemberService {
	
	private final MemberDao dao;
	
	public MemberService() {
		dao = new MemberDao();
	}
	
public int join(MemberVo vo) throws Exception{
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		String code = dao.codeMake(conn,vo.getAddress());
		if(code == null) {
			throw new Exception("주소를 잘못 입력하셨습니다. 다시 입력해주세요");
		}
		vo.setAreasCode(code);
		int result = dao.join(conn,vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}


	public MemberVo login(MemberVo vo) throws Exception{
		Connection conn = JDBCTemplate.getConnection();
		MemberVo userdb = dao.login(conn,vo);
		
		JDBCTemplate.close(conn);
		return userdb;
	}
}
