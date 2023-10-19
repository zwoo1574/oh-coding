package com.cherry.town.controller;

import java.util.List;
import java.util.Scanner;

import com.cherry.main.Main;
import com.cherry.town.service.TownService;
import com.cherry.town.vo.TownVo;

public class TownController {

	//필드
	private final TownService service;
	
	//기본생성자
	public TownController() {
		service = new TownService();
	}
	
	//메뉴선택
	public void selectMenu() throws Exception {
		System.out.println("=====동네생활=====");
		
		System.out.println("1.게시글 작성");
		System.out.println("2.게시글 수정");
		System.out.println("3.게시글 목록");
		System.out.println("4.게시글 상세 조회");
		
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : write(); break;
		case "2" : update(); break;
		case "3" : boardList(); break;
		case "4" : townDetailByNo(); break;
		default : System.out.println("잘못 입력하셨습니다.");
		}
	}
	
	//---------------------게시글작성---------------------------------
	public void write() throws Exception{
		try {
			System.out.println("--------게시글 작성--------");
			
			//로그인
			if(Main.loginMember == null) {
				throw new Exception("회원만 게시글 작성이 가능합니다.");
			}
			//데이터
			System.out.println("제목 : ");
			String title = Main.SC.nextLine();
			System.out.println("내용 : ");
			String content = Main.SC.nextLine();
			
			TownVo vo = new TownVo();
			vo.setTitle(title);
			vo.setContent(content);
			
			//서비스
			int result = service.write(vo);	
			
			//결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("게시글 작성이 완료 되었습니다.");
		}catch(Exception e) {
			System.out.println("게시글 작성이 완료 되지 않았습니다.");
			throw new Exception();
		}
	}
	
	//-----------------------게시글수정------------------------------
	public void update() {
		
	}
	
	//-------------------------게시글목록------------------------------
	public void boardList() {
		try {
			System.out.println("-----게시글 목록-----");
			
			//서비스
			List<TownVo> voList = service.townList();
			
			//결과
			System.out.print("NO");
			System.out.print(":");
			System.out.print("제목");
			System.out.print(":");
			System.out.print("닉네임");
			System.out.print(":");
			System.out.print("조회수");
			System.out.print(":");
			System.out.print("작성일자");
			System.out.println();
			for(TownVo vo : voList) {
				System.out.println(vo.getTownNO());
				System.out.println(":");
				System.out.println(vo.getTitle());
				System.out.println(":");
				System.out.println(vo.getWirterNick());
				System.out.println(":");
				System.out.println(vo.getHit());
				System.out.println(":");
				System.out.println(vo.getEnrollDate());
				
				System.out.println();
			}
			
		}catch(Exception e) {
			System.out.println("게시글 조회에 실패하였습니다.");
			e.printStackTrace();
		}
	}
	
	
	//-----------------------------게시글조회-------------------------
	public void townDetailByNo() {
		try {
			System.out.println("-----게시글 상세 조회------");
			
			//데이터
			System.out.println("게시글 번호 : ");
			String num = Main.SC.nextLine();
			
			//서비스
			TownVo vo = service.townDetailByNo(num);
			
			//결과
			if(vo == null) {
				throw new Exception();
			}
			System.out.println("----------------------");
			System.out.println("글 번호 : " + vo.getTownNO());
			System.out.println("제목 : " + vo.getTitle());
			System.out.println("작성자 :" + vo.getWirterNick());
			System.out.println("조회수 :" + vo.getHit());
			System.out.println("작성일자 :" + vo.getEnrollDate());
			System.out.println("내용 :" + vo.getContent());
			System.out.println("-----------------------");
		}catch(Exception e) {
			System.out.println("게시글 상세 조회 실패하였습니다.");
			e.printStackTrace();
		}
	}
}