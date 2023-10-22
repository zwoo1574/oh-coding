package com.cherry.town_comment.service;

import java.sql.Connection;
import java.util.HashMap;

import com.cherry.jdbc.JDBCTemplate;
import com.cherry.town_comment.dao.TowncommentDao;
import com.cherry.town_comment.vo.TowncommentVo;

public class TowncommentService {

	private final TowncommentDao dao;
	
	public TowncommentService() {
		dao = new TowncommentDao();
	}
	
	public int commentwrite(TowncommentVo vo) throws Exception{
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.commentwrite(conn, vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
