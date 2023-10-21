package com.cherry.notice.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

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
	
	//공지글 조회(최신순)
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
	
	//공지사항 상세 조회
	public NoticeVo noticeDetailByNo(String num) throws Exception {
		//conn
		Connection conn=JDBCTemplate.getConnection();
		
		//dao
		int result=dao.Hit(conn,num);
		NoticeVo vo=dao.noticeDetailByNo(conn,num);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		return vo;
	}
	
	//공지글 검색(제목)
	public ArrayList<NoticeVo> searchNoticeByTitle(String search) throws Exception {
		//conn
		Connection conn=JDBCTemplate.getConnection();
		
		//dao
		ArrayList<NoticeVo> voList= dao.searchNoticeByTitle(conn,search);
		//tx
		
		//close
		JDBCTemplate.close(conn);
		return voList;
	}
	//공지글 감추기
	public int secret(HashMap<String, String> map) throws Exception {
		//conn
		Connection conn=JDBCTemplate.getConnection();
		
		//dao
		int result=dao.secret(conn,map);
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
	
	//공지글 수정
	public int modify(NoticeVo vo) throws Exception {
		//conn
		Connection conn=JDBCTemplate.getConnection();
		
		//dao
		int result=dao.modify(conn,vo);
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
	

}
