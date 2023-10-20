package com.cherry.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cherry.jdbc.JDBCTemplate;
import com.cherry.main.Main;
import com.cherry.qna.vo.QnaVo;

public class QnaDao {

	// 1.문의글 작성
	public int write(Connection conn, QnaVo vo) throws Exception {
		
		//sql
		String sql = "INSERT INTO QNA(QNA_NO, TITLE, CONTENT, MEMBER_NO, SECRET_YN) VALUES(SEQ_QNA_NO.NEXTVAL,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, Main.loginMember.getMemberNo());
		pstmt.setString(4, vo.getSecretYn());
		int result = pstmt.executeUpdate();
		
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		return result;
		
	}//write end
	
	
	/**
	 * 2. 문의글 목록 (최신순) 
	 * 
	 * SELECT *
	 * FROM QNA
	 * ORDER BY QNA_NO DESC
	 */
	public List<QnaVo> qnaList(Connection conn) throws Exception {
		
		//sql
		String sql = "SELECT Q.QNA_NO , Q.TITLE , M.NICK AS WRITER_NICK , Q.HIT , TO_CHAR(Q.MEMBER_ENROLL_DATE, 'YYYY\"년\"MM\"월\"DD\"일\"') AS MEMBER_ENROLL_DATE FROM QNA Q JOIN MEMBER M ON Q.MEMBER_NO = M.MEMBER_NO WHERE SECRET_YN = 'N' ORDER BY QNA_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<QnaVo> voList = new ArrayList<QnaVo>();
		while(rs.next()) {
			String qnaNo = rs.getString("QNA_NO");
			String title = rs.getString("TITLE");
			String writerNick = rs.getString("WRITER_NICK");
			String hit = rs.getString("HIT");
			String enrollDate = rs.getString("MEMBER_ENROLL_DATE");
			
			QnaVo vo = new QnaVo();
			vo.setQnaNo(qnaNo);
			vo.setTitle(title);
			vo.setWriterNick(writerNick);
			vo.setHit(hit);
			vo.setMemberEnrollDate(enrollDate);
			
			voList.add(vo);
			
		}
		
		//close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		return voList;
		
	}//qnaList end
	
	/**
	 * 3. 문의글 상세조회 (번호) 
	 * 
	 * SELECT
	 * FROM QNA
	 * WHERE QNA_NO = ?
	 */
	public QnaVo qnaDetailByNo(Connection conn, String num) throws Exception {
		
		//sql
		String sql = "SELECT Q.QNA_NO , Q.TITLE , Q.CONTENT , M.NICK AS WRITER_NICK , Q.HIT , TO_CHAR(Q.MEMBER_ENROLL_DATE, 'YYYY\"년\"MM\"월\"DD\"일\"') AS MEMBER_ENROLL_DATE FROM QNA Q JOIN MEMBER M ON Q.MEMBER_NO = M.MEMBER_NO WHERE QNA_NO = ? AND SECRET_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		QnaVo vo = null;
		if(rs.next()) {
			String qnaNo = rs.getString("QNA_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String writerNick = rs.getString("WRITER_NICK");
			String hit = rs.getString("HIT");
			String enrollDate = rs.getString("MEMBER_ENROLL_DATE");
			
			vo = new QnaVo();
			vo.setQnaNo(qnaNo);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWriterNick(writerNick);
			vo.setHit(hit);
			vo.setMemberEnrollDate(enrollDate);
			
		}
		
		//close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		return vo;

	}//qnaDetailByNo end
	
	// 3-2.조회수 증가
	public int increaseHit(Connection conn, String num) throws Exception {
		
		//sql
		String sql = "UPDATE QNA SET HIT = HIT + 1 WHERE QNA_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		int result = pstmt.executeUpdate();
		
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		return result;
		
	}//increaseHit end
	
	// 4.문의글 검색 (제목)
	public List<QnaVo> searchQnaByTitle(Connection conn, String searchValue) throws Exception {
		
		//sql
		String sql = "SELECT Q.QNA_NO , Q.TITLE , M.NICK AS WRITER_NICK , Q.HIT , TO_CHAR(Q.MEMBER_ENROLL_DATE, 'YYYY\"년\"MM\"월\"DD\"일\"') AS ENROLL_DATE FROM QNA Q JOIN MEMBER M ON Q.MEMBER_NO = M.MEMBER_NO WHERE Q.TITLE LIKE '%'||?||'%' AND Q.SECRET_YN = 'N' ORDER BY Q.QNA_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<QnaVo> voList = new ArrayList<QnaVo> ();
		while(rs.next()) {
			String no = rs.getString("QNA_NO");
			String title = rs.getString("TITLE");
			String writerNick = rs.getString("WRITER_NICK");
			String hit = rs.getString("HIT");
			String enrollDate = rs.getString("ENROLL_DATE");
			
			QnaVo vo = new QnaVo();
			vo.setQnaNo(no);
			vo.setTitle(title);
			vo.setWriterNick(writerNick);
			vo.setHit(hit);
			vo.setMemberEnrollDate(enrollDate);
			
			voList.add(vo);
			
		}
		
		//close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		return voList;
		
	}//searchQnaByTitle end

	// 5. 내가 작성한 문의글
	public List<QnaVo> qnaMyList(Connection conn, String loginMember) throws Exception {
		
		// sql
		String sql = "SELECT Q.QNA_NO , Q.TITLE , M.NICK AS WRITER_NICK , Q.HIT , TO_CHAR(Q.MEMBER_ENROLL_DATE, 'YYYY\"년\"MM\"월\"DD\"일\"') AS ENROLL_DATE FROM QNA Q JOIN MEMBER M ON Q.MEMBER_NO = M.MEMBER_NO WHERE M.MEMBER_NO LIKE ? ORDER BY Q.QNA_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginMember);
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		List<QnaVo> voList = new ArrayList<QnaVo>();
		while(rs.next()) {
			String no = rs.getString("QNA_NO");
			String title = rs.getString("TITLE");
			String nick = rs.getString("WRITER_NICK");
			String hit = rs.getString("HIT");
			String enrollDate = rs.getString("ENROLL_DATE");
			
			QnaVo vo = new QnaVo();
			vo.setQnaNo(no);
			vo.setTitle(title);
			vo.setWriterNick(nick);
			vo.setHit(hit);
			vo.setMemberEnrollDate(enrollDate);
			
			voList.add(vo);
			
		}
		
		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		return voList;
		
	}//qnaMyList end
	
	
}
