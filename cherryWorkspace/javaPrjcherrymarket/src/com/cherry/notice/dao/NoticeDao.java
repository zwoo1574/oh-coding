package com.cherry.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import com.cherry.jdbc.JDBCTemplate;
import com.cherry.notice.vo.NoticeVo;

public class NoticeDao {
	
	//공지글 작성
	public int write(Connection conn, NoticeVo vo) throws Exception  {
		//sql
		String sql="INSERT INTO NOTICE(NOTICE_NO,MANAGER_NO,TITLE,CONTENT) VALUES(SEQ_NOTICE_NO.NEXTVAL,?,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, vo.getManagerNo());
		pstmt.setString(2, vo.getTitle());
		pstmt.setString(3, vo.getContent());
		
		int result=pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		return result;
	
		
		
	}

	public ArrayList<NoticeVo> noticeList(Connection conn) throws Exception {
		//sql
		String sql="SELECT NOTICE_NO,TITLE,HIT,TO_CHAR(ENROLL_DATE,'YYYY\"년\"MM\"월\"DD\"일\"') AS ENROLL_DATE FROM NOTICE ORDER BY NOTICE_NO DESC"
				+ "";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rs= pstmt.executeQuery();
		
		//rs
		ArrayList<NoticeVo> voList=new ArrayList();
		while(rs.next()) {
			String noticeNo=rs.getString("NOTICE_NO");
			String title=rs.getString("TITLE");
			String hit=rs.getString("HIT");
			String enrollDate=rs.getString("ENROLL_DATE");
			
			NoticeVo vo=new NoticeVo();
			vo.setNo(noticeNo);
			vo.setTitle(title);
			vo.setHit(hit);
			vo.setEnrolldate(enrollDate);
			
			voList.add(vo);
	
		}
	
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return voList;
		
	}

}
