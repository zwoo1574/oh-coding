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
	
	// 1.문의글 작성
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

	//2.문의글 목록 (최신순)
	public List<QnaVo> qnaList() throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		List<QnaVo> voList = dao.qnaList(conn);
		
		//close
		JDBCTemplate.close(conn);
		return voList;
		
	}//qnaList end
	
	// 3.문의글 상세조회 (번호)
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
	
	// 4.문의글 검색 (제목)
	public List<QnaVo> searchQnaByTitle(String searchValue) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		List<QnaVo> voList = dao.searchQnaByTitle(conn, searchValue);
		
		//tx
		
		//close
		JDBCTemplate.close(conn);
		return voList;
		
	}//searchQnaByTitle end
	
	// 5. 내가 작성한 문의글
	public List<QnaVo> qnaMyList(String loginMember) throws Exception{
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		List<QnaVo> voList = dao.qnaMyList(conn, loginMember);
		
		// tx
		
		// close
		JDBCTemplate.close(conn);
		return voList;
		
	}//qnaMyList end
	
}
