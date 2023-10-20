package com.cherry.trade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cherry.jdbc.JDBCTemplate;
import com.cherry.main.Main;
import com.cherry.trade.vo.TradeVo;

import oracle.jdbc.proxy.annotation.Pre;

public class TradeDao {

	// 게시글 목록 (중고거래 메인)
	public List<TradeVo> printPost(Connection conn) throws Exception {

		String sql = "SELECT RPAD(T.BOARD_NO, 20, ' ') BOARD_NO ,RPAD(T.TITLE, 60, ' ') TITLE ,RPAD(T.PRODUCT, 40, ' ') PRODUCT ,RPAD(T.PRICE, 20, ' ') PRICE ,RPAD(M.NICK, 20, ' ') NICK ,RPAD(TO_CHAR(T.ENROLL_DATE, 'YYYY-MM-DD'), 20, ' ') ENROLL_DATE ,T.PRODUCT ,T.PRICE FROM TRADE T JOIN MEMBER M ON T.MEMBER_NO = M.MEMBER_NO WHERE DEL_YN = 'N' ORDER BY ENROLL_DATE DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<TradeVo> voList = new ArrayList<TradeVo>();
		while(rs.next()) {
			String no = rs.getString("BOARD_NO");
			String title = rs.getString("TITLE");
			String product = rs.getString("PRODUCT");
			String price = rs.getString("PRICE");
			String nick = rs.getString("NICK");
			String date = rs.getString("ENROLL_DATE");
			
			TradeVo vo = new TradeVo();
			vo.setBoardNo(no);
			vo.setTitle(title);
			vo.setProduct(product);
			vo.setPrice(price);
			vo.setMemberNick(nick);
			vo.setEnrollDate(date);
			
			voList.add(vo);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return voList;
	}

	// 게시글 조회   -- 조회수 추가해야함
	public TradeVo showContent(String select, Connection conn) throws Exception {
		
		String sql = "SELECT T.BOARD_NO, T.TITLE, T.CONTENT, T.TRADE_AREAS, T.PRODUCT, T.PRICE, T.ENROLL_DATE, T.EDIT_DATE, M.NICK, A.AREAS_NAME FROM TRADE T JOIN MEMBER M ON T.MEMBER_NO = M.MEMBER_NO JOIN AREAS A ON M.AREAS_CODE = A.AREAS_CODE WHERE T.BOARD_NO = ? AND T.COMPLETE_YN = 'N' AND T.DEL_YN = 'N'";
		String hit = "UPDATE TRADE SET HIT = HIT +1 WHERE BOARD_NO = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		PreparedStatement pstmt2 = conn.prepareStatement(hit);		
		pstmt.setString(1, select);
		pstmt2.setString(1, select);
		pstmt2.executeUpdate();
		ResultSet rs = pstmt.executeQuery();
		
		TradeVo vo = null;
		
		if(rs.next()) {
			String boardNo = rs.getString("BOARD_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String nick = rs.getString("NICK");
			String tradeArea = rs.getString("TRADE_AREAS");
			String areaName = rs.getString("AREAS_NAME");
			String product = rs.getString("PRODUCT");
			String price = rs.getString("PRICE");
			String enrollDate = rs.getString("ENROLL_DATE");
			String editDate = rs.getString("EDIT_DATE");
			
			vo = new TradeVo();
			vo.setBoardNo(boardNo);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setMemberNick(nick);
			vo.setTradeAreas(tradeArea);
			vo.setAreasName(areaName);
			vo.setProduct(product);
			vo.setPrice(price);
			vo.setEnrollDate(enrollDate);
			vo.setEditDate(editDate);
		}
			
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return vo;
	}

	// 조회수 증가
	public int increaseHit(String select, Connection conn) throws Exception {
		
		String sql = "UPDATE TRADE SET HIT = HIT + 1 WHERE BOARD_NO = ? AND MEMBER_NO != ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, select);
		if(Main.loginMember != null) {
			pstmt.setString(2, Main.loginMember.getMemberNo());
		} else {
			pstmt.setString(2, "0");
		}
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	// 게시글 수정
	public int editPost(TradeVo vo, Connection conn) throws Exception {
		
		String sql = "UPDATE TRADE SET TITLE = ? ,PRODUCT = ? ,PRICE = ? ,TRADE_AREAS = ? ,CONTENT = ? ,EDIT_DATE = SYSDATE WHERE BOARD_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getProduct());
		pstmt.setString(3, vo.getPrice());
		pstmt.setString(4, vo.getTradeAreas());
		pstmt.setString(5, vo.getContent());
		pstmt.setString(6, vo.getBoardNo());

		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 게시글 작성
	public int writePost(TradeVo vo, Connection conn) throws Exception {
		
		String sql = "INSERT INTO TRADE(BOARD_NO, MEMBER_NO, AREAS_CODE, TITLE, PRODUCT, PRICE, TRADE_AREAS, CONTENT) VALUES(SEQ_BOARD_NO.NEXTVAL,?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);	
		pstmt.setString(1, Main.loginMember.getMemberNo());
		pstmt.setString(2, Main.loginMember.getAreasCode());
		pstmt.setString(3, vo.getTitle());
		pstmt.setString(4, vo.getProduct());
		pstmt.setString(5, vo.getPrice());
		pstmt.setString(6, vo.getTradeAreas());
		
        String content = vo.getContent();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            sb.append(ch);

            if ((i + 1) % 20 == 0 && i != 0) {
                sb.append("#");
            }
        }
        
        content = sb.toString();
  
        pstmt.setString(7, content);
		
		int result = pstmt.executeUpdate();

		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 게시글 삭제     -- 미완성
	public int delPost(String del, Connection conn) {
		
		
		
		return 0;
	}

	// 관심목록 추가 
	public int addWishList(String x, Connection conn) throws Exception {
		
		String sql = "INSERT INTO WISHLIST (WISHLIST_NO, BOARD_NO, MEMBER_NO) SELECT SEQ_WISHLIST_NO.NEXTVAL, ?, ? FROM WISHLIST WHERE NOT EXISTS ( SELECT 1 FROM WISHLIST WHERE BOARD_NO = ? AND MEMBER_NO = ? )";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, x);
		pstmt.setString(2, Main.loginMember.getMemberNo());
		pstmt.setString(3, x);
		pstmt.setString(4, Main.loginMember.getMemberNo());
		int result = pstmt.executeUpdate();
		
		
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
		
	}

	// 게시글 정보 조회
	public TradeVo searchPostInfo(String boardNo, Connection conn) throws Exception {
		
		String sql = "SELECT T.* ,M.NICK ,A.AREAS_NAME FROM TRADE T JOIN MEMBER M ON T.MEMBER_NO = M.MEMBER_NO JOIN AREAS A ON M.AREAS_CODE = A.AREAS_CODE WHERE BOARD_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardNo);
		ResultSet rs = pstmt.executeQuery();
	
		TradeVo vo = null;
		if(rs.next()) {
			String memberNo = rs.getString("MEMBER_NO");
			String nick = rs.getString("NICK");
			String areaName = rs.getString("AREAS_NAME");
			String boardNum = rs.getString("BOARD_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String tradeArea = rs.getString("TRADE_AREAS");
			String product = rs.getString("PRODUCT");
			String price = rs.getString("PRICE");
			String enrollDate = rs.getString("ENROLL_DATE");
			String editDate = rs.getString("EDIT_DATE");
			
			vo = new TradeVo();
			vo.setMemberNo(memberNo);
			vo.setMemberNick(nick);
			vo.setAreasName(areaName);
			vo.setBoardNo(boardNum);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setTradeAreas(tradeArea);
			vo.setProduct(product);
			vo.setPrice(price);
			vo.setEnrollDate(enrollDate);
			vo.setEditDate(editDate);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}

	// 구매 확정 - 히스토리 남기기
	public int confirmPurchase(TradeVo vo, Connection conn) throws Exception {
		
		String sql = "INSERT INTO PURCHASE_HISTORY(PURCHASE_NO, BUYER_NO, BOARD_NO) VALUES (SEQ_PURCHASE_NO.NEXTVAL, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Main.loginMember.getMemberNo());
		pstmt.setString(2, vo.getBoardNo());
		
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			String sql2 = "UPDATE TRADE SET COMPLETE_YN = ? WHERE BOARD_NO = ?";
			PreparedStatement pstmt2= conn.prepareStatement(sql2);
			pstmt2.setString(1, "Y");
			pstmt2.setString(2, vo.getBoardNo());
			
			pstmt2.executeUpdate();
		}
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	
	// 구매 후기 작성  (매너온도 포함)  -- 미완성
	public int writeReview(String content, Connection conn) throws Exception {
		String sql = "INSERT INTO REVIEW(REVIEW_NO, CONTENT) VALUES (SEQ_REVIEW_NO.NEXTVAL, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, content);
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

}
