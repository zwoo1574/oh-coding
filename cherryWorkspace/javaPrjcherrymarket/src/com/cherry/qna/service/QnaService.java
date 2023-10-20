package com.cherry.qna.service;

import java.sql.Connection;
import java.util.List;

import com.cherry.jdbc.JDBCTemplate;
import com.cherry.qna.dao.QnaDao;
import com.cherry.qna.vo.QnaVo;

public class QnaService {

	private final QnaDao dao;
	
	//기본 생성자
	public QnaService() {
		dao = new QnaDao();
	}
	
	//문의글 작성
	public int write(QnaVo vo) throws Exception {
		
		// conn
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

	//문의글 목록 (최신순)
	public List<QnaVo> qnaList() throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		List<QnaVo> voList = dao.qnaList(conn);
		
		//close
		JDBCTemplate.close(conn);
		return voList;
		
	}//qnaList end
	
	//문의글 상세조회 (번호)
	public QnaVo qnaDetailByNo(String num) throws Exception{
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = dao.increaseHit(conn,num);
		QnaVo vo = dao.qnaDetailByNo(conn, num);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		return vo;
		
	}//qnaDetailByNo end
	
}
