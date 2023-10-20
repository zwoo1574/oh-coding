package com.cherry.town.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cherry.jdbc.JDBCTemplate;
import com.cherry.main.Main;
import com.cherry.town.vo.TownVo;

public class TownDao {

	//-----------------------게시글 작성----------------------
	public int write(Connection conn, TownVo vo) throws Exception {
		
		//SQL
		String sql = "INSERT INTO TOWN(TOWN_NO, TITLE, CATEGORY, CONTENT, MEMBER_NO) VALUES(SEQ_TOWN_NO.NEXTVAL, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getCategory());
		pstmt.setString(3, vo.getContent());
		pstmt.setString(4, Main.loginMember.getMemberNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	
	//-----------------------게시글 삭제----------------------
	public int towndelete(Connection conn, HashMap<String, String>map) throws Exception {
		
		String sql = "UPDATE TOWN SET DELETE_YN = 'Y', ENROLL_DATE = SYSDATE WHERE TOWN_NO = ? AND MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, map.get("TOWN_NO"));
		pstmt.setString(2, map.get("loginMemberNo"));
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}
	
	
	
	//-----------------------게시글 목록----------------------
	public List<TownVo> townList(Connection conn) throws Exception {
		
		//SQL
		String sql = "SELECT T.TOWN_NO, T.CATEGORY, T.TITLE, M.MEMBER_NO AS WRITER_NICK, T.HIT , T.ENROLL_DATE  FROM TOWN T JOIN MEMBER M ON T.MEMBER_NO = M.MEMBER_NO WHERE T.DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<TownVo> voList = new ArrayList<TownVo>();
		while(rs.next()) {
			String no = rs.getString("TOWN_NO");
			String category = rs.getString("CATEGORY");
			String title = rs.getString("TITLE");
			String writerNick = rs.getString("WRITER_NICK");
			String hit = rs.getString("HIT");
			String enrollDate = rs.getString("ENROLL_DATE");
			
			TownVo vo = new TownVo();
			vo.setTownNO(no);
			vo.setCategory(category);
			vo.setTitle(title);
			vo.setWirterNick(writerNick);
			vo.setHit(hit);
			vo.setEnrollDate(enrollDate);
			
			voList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}

	
	
	//-----------------------게시글 조회----------------------
	public TownVo townDetailByNo(Connection conn, String searchValue) throws Exception {
		
		//SQL
		String sql = "SELECT T.TOWN_NO, T.TITLE, T.CONTENT, M.MEMBER_NO AS WRITER_NICK, T.HIT , TO_CHAR(T.ENROLL_DATE , 'MM/DD') AS ENROLL_DATE FROM TOWN T JOIN MEMBER M ON T.MEMBER_NO = M.MEMBER_NO WHERE T.TOWN_NO = ? AND T.DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		ResultSet rs = pstmt.executeQuery();
		
		TownVo vo = null;
		if(rs.next()) {
			String no = rs.getString("TOWN_NO");
			String title = rs.getString("TITLE");
			String contnet = rs.getString("CONTENT");
			String writerNick = rs.getString("WRITER_NICK");
			String hit = rs.getString("HIT");
			String enrollDate = rs.getString("ENROLL_DATE");
			
			vo = new TownVo();
			vo.setTownNO(no);
			vo.setTitle(title);
			vo.setContent(contnet);
			vo.setWirterNick(writerNick);
			vo.setHit(hit);
			vo.setEnrollDate(enrollDate);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
		
	}
	
	//------------------------조회수----------------------
	public int increaseHit(Connection conn, String num)throws Exception {
		
		//SQL
		String sql = "UPDATE TOWN SET HIT = HIT+1 WHERE TOWN_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	
}
