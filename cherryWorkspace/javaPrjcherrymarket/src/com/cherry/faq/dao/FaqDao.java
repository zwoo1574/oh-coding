package com.cherry.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class FaqDao {

	public int write(Connection conn) throws Exception{
		
		//sql
		String sql = "INSERT INTO FAQ(FAQ_NO, MANAGER_NO, TITLE, CONTENT) VALUES(SEQ_FAQ_NO.NEXTVAL, 1, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "자주묻는질문1");
		pstmt.setString(2, "내용1");
		int result = pstmt.executeUpdate();
		
		
		//close
		return result;
		
	}

}
