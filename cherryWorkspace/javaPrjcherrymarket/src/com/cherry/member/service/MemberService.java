package com.cherry.member.service;

import java.sql.*;
import java.util.*;

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

	public int quit(String no) throws Exception{
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.quit(conn,no);
		if(result == 1 ) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int changePwd(HashMap<String, String> map) throws Exception{
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.changePwd(conn,map);
		if(result == 1 ) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		
		return result;
	}

	public int changeNick(MemberVo vo) throws Exception{
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.changeNick(conn,vo);
		if(result == 1 ) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int changeAddress(MemberVo vo) throws Exception{
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.changeAddress(conn,vo);
		if(result == 1 ) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int changePhone(MemberVo vo) throws Exception{
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.changePhone(conn,vo);
		if(result == 1 ) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

}
