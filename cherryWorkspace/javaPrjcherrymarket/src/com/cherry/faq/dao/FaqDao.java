package com.cherry.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

	//게시판 검색(게시판번호)
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

	//게시판 전체 조회
	public List<FaqVo> boardList(Connection conn) throws Exception {
		
		//SQL
		String sql = "SELECT * FROM FAQ ORDER BY FAQ_NO DESC";	
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<FaqVo> voList = new ArrayList<FaqVo>();
		while(rs.next()) {
			
			String faqNo = rs.getString("FAQ_NO");
			String managerNo= rs.getString("MANAGER_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String secretYn = rs.getString("SECRET_YN");
			String editDate = rs.getString("EDIT_DATE");
			String hit = rs.getString("HIT"); 
			
			FaqVo vo = new FaqVo();
			
			vo.setFaqNo(faqNo);
			vo.setManagerNo(managerNo);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setEnrollDate(enrollDate);
			vo.setSecretYn(secretYn);
			vo.setEditDate(editDate);
			vo.setHit(hit);
			
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
		
	}

	//게시판 상세 조회(게시판 번호)
	public FaqVo boardDetailByNo(Connection conn, String no) throws Exception {
		
		//SQL
		String sql = "SELECT * FROM FAQ WHERE FAQ_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		
		
		FaqVo vo = null;
		if(rs.next()) {
			String faqNo = rs.getString("FAQ_NO");
			String managerNo = rs.getString("MANAGER_NO");
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
			vo.setEditDate(editDate);
			vo.setHit(hit);
			
		}
			
		//close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(conn);
		
		return vo;	
		
	}

	public int increaseHit(Connection conn, String no) throws Exception{
		//SQL
		String sql = "UPDATE FAQ SET HIT = HIT + 1 WHERE FAQ_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
			
}//class
		
		

	
	
