package com.cherry.trade.service;

import java.sql.Connection;
import java.util.List;

import com.cherry.jdbc.JDBCTemplate;
import com.cherry.trade.dao.TradeDao;
import com.cherry.trade.vo.TradeVo;

public class TradeService {
	
	private final TradeDao dao;

	public TradeService() {
		dao = new TradeDao();
	}

	public List<TradeVo> printPost() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<TradeVo> voList = dao.printPost(conn);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}

	public TradeVo showContent(String select) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		TradeVo vo = dao.showContent(select, conn);
		
		JDBCTemplate.close(conn);
		return vo;
	}

	public int editPost(TradeVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.editPost(vo, conn);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int writePost(TradeVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.writePost(vo, conn);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int delPost(String del) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.delPost(del, conn);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
}
