package com.cherry.faq.service;

import java.sql.Connection;

import com.cherry.faq.dao.FaqDao;
import com.cherry.jdbc.JDBCTemplate;

public class FaqService {

	//필드
	FaqDao fd;
	
	//기본 생성자
	public FaqService() {
		fd = new FaqDao();
	}
	
	//게시판 생성
	public int write() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		int result = fd.write(conn);
		
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

	//게시판 수정
	
	//게시판 삭제
	
	//게시판 조회
	
}
