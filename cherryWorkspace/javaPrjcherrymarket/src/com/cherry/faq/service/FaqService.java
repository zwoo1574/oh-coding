package com.cherry.faq.service;

import java.sql.Connection;

import com.cherry.faq.dao.FaqDao;
import com.cherry.faq.vo.FaqVo;
import com.cherry.jdbc.JDBCTemplate;

public class FaqService {

	//필드
	FaqDao fd;
	
	//기본 생성자
	public FaqService() {
		fd = new FaqDao();
	}
	
	//게시판 생성
	public int write(FaqVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		int result = fd.write(conn, vo);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//게시판 조회
	public static FaqVo boardPrintByNo(String num) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		FaqVo vo = FaqDao.boardPrintByNo(conn, num);
		
		//close
		JDBCTemplate.close(conn);
		
		return vo;
	}

	//게시판 수정
	
	//게시판 삭제
	
	//게시판 조회
	
}
