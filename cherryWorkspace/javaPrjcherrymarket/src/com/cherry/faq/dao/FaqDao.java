package com.cherry.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cherry.faq.vo.FaqVo;
import com.cherry.jdbc.JDBCTemplate;

public class FaqDao {

	//게시판 작성
	public int write(Connection conn, FaqVo vo) throws Exception{
		
		//sql
		String sql = "INSERT INTO FAQ(FAQ_NO, MANAGER_NO, TITLE, CONTENT) VALUES(SEQ_FAQ_NO.NEXTVAL, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getManagerNo());
		pstmt.setString(2, vo.getTitle());
		pstmt.setString(3, vo.getContent());
		int result = pstmt.executeUpdate();
		
		
		//close
		JDBCTemplate.close(pstmt);

		return result;
	}

	//게시판 조회
	public static FaqVo boardPrintByNo(Connection conn, String num) throws Exception {
		
		//SQL
		String sql = "SELECT * FROM FAQ WHERE FAQ_NO = ? AND SECRET_YN = 'N' ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		FaqVo vo = null;
		if(rs.next()) {
			
			String faqNo = rs.getString("FAQ_NO");
			String managerNo= rs.getString("MANAGER_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String secretYn = rs.getString("SECRET_YN");
			String editDate = rs.getString("EDIT_DATE");
			String hit = rs.getString("HIT");
			
			
			vo = new FaqVo();
			vo.setFaqNo(faqNo);
			vo.setManagerNo(managerNo);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setEnrollDate(enrollDate);
			vo.setSecretYn(secretYn);
			vo.setContent(editDate);
			vo.setHit(hit);
		}
		
		//close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return vo;
	}
	
	

}
