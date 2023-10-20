package com.cherry.faq.service;

import java.sql.Connection;
import java.util.List;

import com.cherry.faq.dao.FaqDao;
import com.cherry.faq.vo.FaqVo;
import com.cherry.jdbc.JDBCTemplate;

public class FaqService {

	//필드
	FaqDao dao;
	
	//기본 생성자
	public FaqService() {
		dao = new FaqDao();
	}
	
	//게시판 생성
	public int write(FaqVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		int result = dao.write(conn, vo);
		
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
	
	
	
	//게시판 검색(게시판번호)
	public static FaqVo boardPrintByNo(String num) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		FaqVo vo = FaqDao.boardPrintByNo(conn, num);
		
		//close
		JDBCTemplate.close(conn);
		
		return vo;
	}

	//게시판 전체 조회
	public List<FaqVo> boardList() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		List<FaqVo> voList = dao.boardList(conn);
		
		//close 
		JDBCTemplate.close(conn);
		
		return voList;
	}
	
	//게시판 상세 조회(게시판 번호)
		public FaqVo boardDetailByNo(String no) throws Exception {
			
			//conn
			Connection conn = JDBCTemplate.getConnection();
			
			//DAO
			int result = dao.increaseHit(conn, no);
			FaqVo vo = dao.boardDetailByNo(conn, no);
			
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
	//게시판 수정
	
	//게시판 삭제
	
	//게시판 조회
	
}
