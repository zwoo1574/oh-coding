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

public class TradeDao {

	public List<TradeVo> printPost(Connection conn) throws Exception {

		String sql = "SELECT RPAD(T.BOARD_NO, 20, ' ') BOARD_NO ,RPAD(T.TITLE, 60, ' ') TITLE ,RPAD(T.PRODUCT, 40, ' ') PRODUCT ,RPAD(T.PRICE, 20, ' ') PRICE ,RPAD(M.NICK, 20, ' ') NICK ,RPAD(TO_CHAR(T.ENROLL_DATE, 'YYYY-MM-DD'), 20, ' ') ENROLL_DATE ,T.PRODUCT ,T.PRICE FROM TRADE T JOIN MEMBER M ON T.MEMBER_NO = M.MEMBER_NO WHERE DEL_YN = 'N' ORDER BY BOARD_NO DESC";
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

	public TradeVo showContent(String select, Connection conn) throws Exception {
		
		String sql = "SELECT T.BOARD_NO, T.TITLE, T.CONTENT, T.TRADE_AREAS, T.PRODUCT, T.PRICE, T.ENROLL_DATE, T.EDIT_DATE, M.NICK, A.AREAS_NAME FROM TRADE T JOIN MEMBER M ON T.MEMBER_NO = M.MEMBER_NO JOIN AREAS A ON M.AREAS_CODE = A.AREAS_CODE WHERE T.BOARD_NO = ? AND T.COMPLETE_YN = 'N' AND T.DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, select);
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

	public int writePost(TradeVo vo, Connection conn) throws Exception {
		
//		String sql = "INSERT INTO TRADE(BOARD_NO, MEMBER_NO, AREAS_CODE, TITLE, PRODUCT, PRICE, TRADE_AREAS, CONTENT) VALUES(SEQ_BOARD_NO.NEXTVAL,?, ?, ?, ?, ?, ?, ?)";
		String sql = "INSERT INTO TRADE(BOARD_NO, TITLE, PRODUCT, PRICE, TRADE_AREAS, CONTENT) VALUES(SEQ_BOARD_NO.NEXTVAL, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);	
//		pstmt.setString(1, Main.loginUser.getMemberNo);
//		pstmt.setString(2, Main.loginUser.getAreaCode);
//		pstmt.setString(3, vo.getTitle());
//		pstmt.setString(4, vo.getProduct());
//		pstmt.setString(5, vo.getPrice());
//		pstmt.setString(6, vo.getTradeAreas());
//		pstmt.setString(7, vo.getContent());

		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getProduct());
		pstmt.setString(3, vo.getPrice());
		pstmt.setString(4, vo.getTradeAreas());
		
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
  
        pstmt.setString(5, content);
		
		int result = pstmt.executeUpdate();

		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int delPost(String del, Connection conn) {
		
		
		
		return 0;
	}
}
