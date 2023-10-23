package com.cherry.town_comment.controller;

import java.util.HashMap;

import com.cherry.main.Main;
import com.cherry.town.controller.TownController;
import com.cherry.town_comment.service.TowncommentService;
import com.cherry.town_comment.vo.TowncommentVo;

public class TowncommentController {

	
	private TowncommentService service;
	TownController tc = new TownController();
	//
	public void TowncommentController() {
		service = new TowncommentService();
	}
	
	public void towncommentselect() {
		
	
		System.out.println("--------------");
		
		System.out.println("1. 댓글작성");
		System.out.println("2. 게시판 보기");
		
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : commentwrite(); break;
		case "2" : tc.boardList(); break;
		default : System.out.println("잘못입력하셨습니다");
		}
	}

//  ---------------------댓글작성---------------------------------------
	public void commentwrite() {
		try {
			System.out.println("--------댓글작성-------");
			
			System.out.println("내용 : ");
			String content = Main.SC.nextLine();
			
			TowncommentVo vo = new TowncommentVo();
			vo.setContent(content);
			
			int result = service.commentwrite(vo);
			
			if(result != 1) {
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("댓글작성실패");
			e.printStackTrace();
		}		
	}
	
}
