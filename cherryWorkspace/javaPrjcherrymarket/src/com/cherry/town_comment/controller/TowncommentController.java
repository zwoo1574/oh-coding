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
		
	
		System.out.println("----TOWN---");
		
		System.out.println("1. 댓글작성");
		System.out.println("2. 게시판 보기");
		
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : commentwrite(); break;
		case "2" : tc.boardList(); break;
		default : System.out.println("잘못입력하셨습니다");
		}
	}

	//  ---------------------댓글삭제---------------------------------------
//	private void commentdelete() {
//		try {
//			System.out.println("------댓글삭제-----");
//			
//			if(Main.loginMember == null) {
//				throw new Exception("로그인 해주세요");
//			}
//			
//			System.out.println("댓글번호 : ");
//			String num = Main.SC.nextLine();
//			String memberNo = Main.loginMember.getMemberNo();
//			
//			HashMap<String, String> map = new HashMap<String,String>();
//			map.put("commentNo", memberNo);
//			map.put("loginMemberNo", memberNo);
//			int result = service.delete(map);
//			
//			if(result != 1) {
//				throw new Exception();
//			}
//			System.out.println("댓글이 삭제되었습니다.");
//			
//		}catch(Exception e) {
//			System.out.println("댓글삭제 실패");
//			e.printStackTrace();
//		}
//	}

//  ---------------------댓글작성---------------------------------------
	public void commentwrite() {
		try {
			System.out.println("--------댓글작성-------");
			
			System.out.println("내용 : ");
			String content = Main.SC.nextLine();
			
			TowncommentVo vo = new TowncommentVo();
			vo.setContent(content);
			
			int result = service.commentwrite(vo);
			
		}catch(Exception e) {
			System.out.println("댓글작성실패");
			e.printStackTrace();
		}
		

	}
	
}
