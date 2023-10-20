package com.cherry.town_comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cherry.jdbc.JDBCTemplate;
import com.cherry.town_comment.vo.TowncommentVo;

public class TowncommentDao {

	public int commentwrite(Connection conn, TowncommentVo vo) throws Exception {
	
		String sql = "INSERT INTO TOWN_COMMENT(COMMENT_NO, CONTENT, MEMBER_NO)VALUES(SEQ_TOWN_COMMENT_NO.NEXTVAL, ? ,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getContent());
		pstmt.setString(2, vo.getCommentNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}


}
