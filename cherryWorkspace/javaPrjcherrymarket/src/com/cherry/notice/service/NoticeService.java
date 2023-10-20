package com.cherry.notice.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.cherry.jdbc.JDBCTemplate;
import com.cherry.member.dao.MemberDao;
import com.cherry.notice.dao.NoticeDao;
import com.cherry.notice.vo.NoticeVo;

public class NoticeService {
	//필드
	private final NoticeDao dao;
	
	//기본생성자
	public NoticeService() {
		dao=new NoticeDao();
	}
	
	//공지글 작성
	public int write(NoticeVo vo) throws Exception  {
		//conn
		Connection conn=JDBCTemplate.getConnection();
		
		//dao
		int result=dao.write(conn,vo);
		
		//tx
		if(result==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		//close
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<NoticeVo> noticeList() throws Exception  {
		//conn
		Connection conn=JDBCTemplate.getConnection();
		
		//dao
		ArrayList<NoticeVo> voList=dao.noticeList(conn);
		//tx
		
		//close
		JDBCTemplate.close(conn);
		return voList;
				
	}
	

}
