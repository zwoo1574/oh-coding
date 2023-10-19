package com.cherry.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cherry.jdbc.JDBCTemplate;
import com.cherry.main.Main;
import com.cherry.qna.vo.QnaVo;

public class QnaDao {

	// 문의글 작성
	public int write(Connection conn, QnaVo vo) throws Exception {
		
		//sql
		String sql = "INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO, SECRET_YN) VALUES(SEQ_BOARD_NO.NEXTVAL,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
//		pstmt.setString(3, Main.loginMember.getNo());
		pstmt.setString(4, vo.getSecretYn());
		int result = pstmt.executeUpdate();
		
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		return result;
		
	}//write end
	
}
