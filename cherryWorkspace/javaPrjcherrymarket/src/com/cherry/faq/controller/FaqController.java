package com.cherry.faq.controller;

import java.util.List;

import com.cherry.faq.service.FaqService;
import com.cherry.faq.vo.FaqVo;
import com.cherry.main.Main;

public class FaqController {

	//필드
	private final FaqService fs;

	//기본생성자
	public FaqController() {
		fs = new FaqService();
	}

	//메뉴 선택
	public void selectMenu() {
		
		System.out.println("======메뉴 선택=====");
		System.out.println("1.게시판 등록");
//		System.out.println("2.게시판 수정");
//		System.out.println("3.게시판 삭제");
		System.out.println("4.게시판 조회");
		System.out.println("5.게시판 전체 조회");
		System.out.println("6.게시판 상세 조회");
		System.out.println("9.프로그램 종료");
		System.out.println("===================");
		String num = Main.SC.nextLine();
		
		switch(num) {
		case "1" : write(); break;
		case "4" : boardPrintByNo(); break;
		case "5" : boardList(); break;
		case "6" : boardDetailByNo(); break;
		case "9" : return; 
		default : System.out.println("올바른 번호를 입력하세요");
		}
		
	}
	
	//게시판 등록
	public void write() {
		try {
			System.out.println("-----게시판 등록-----");
			//데이터
			System.out.print("제목: ");
			String title = Main.SC.nextLine();
			System.out.print("내용: ");
			String content = Main.SC.nextLine();
			System.out.print("관리자 번호: ");
			String managerNo = Main.SC.nextLine();
			
			FaqVo vo = new FaqVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setManagerNo(managerNo);
			
			//서비스
			int result = fs.write(vo);
			
			//결과 처리
			if(result!= 1) {
				throw new Exception();
			}
			System.out.println("게시판 등록 성공!");
			
		}catch(Exception e) {
			System.out.println("게시판 등록 실패..");
			e.printStackTrace();
		}
		
	}
	
	//게시판 수정

	//게시판 삭제(update del_yn)
	
	//게시판 검색(게시판번호)
	public void boardPrintByNo() {
		try {
			//데이터
			System.out.println("-----게시판 검색-----");
			System.out.print("게시판 번호: ");
			String num = Main.SC.nextLine();
			
			
			//서비스
			FaqVo vo = FaqService.boardPrintByNo(num);
			
			//결과
			if(vo == null) {
				throw new Exception();
			}
			
			System.out.println("게시판 조회 성공!");
			System.out.println(vo);
			
		}catch(Exception e) {
			System.out.println("게시판 조회 실패..");
			e.printStackTrace();
		}
	}
	
	
	//게시판 전체 조회(관리자만)
	public void boardList() {
		System.out.println("-----전체 게시판 조회-----");
		
		try {
					
			//서비스
			List<FaqVo> voList = fs.boardList();
			
			//결과
			
			if(voList.size() == 0) {
				throw new Exception();
			}
			
			for(FaqVo vo : voList) {
				System.out.println(vo);
			}
		
		}catch(Exception e) {
			System.out.println("전체 게시판 조회 실패...");
			e.printStackTrace();
		}
	}
	
	//게시판 상세 조회(게시판 번호)
	public void boardDetailByNo() {
		
		try {
			System.out.println("-----게시판 상세 조회-----");
			//데이터
			System.out.print("게시판 번호: ");
			String no = Main.SC.nextLine();
			
			//서비스
			FaqVo vo = fs.boardDetailByNo(no);
			
			//결과
			if(vo == null) {
				throw new Exception("해당 번호의 게시판은 존재하지 않습니다.");
			}
			
			System.out.println("========================");
			System.out.println("글 번호 : " + vo.getFaqNo());
			System.out.println("매니저 번호 : " + vo.getManagerNo());
			System.out.println("글 제목 : " + vo.getTitle());
			System.out.println("글 내용 : " + vo.getContent());
			System.out.println("등록 일자 : " + vo.getEnrollDate());
			System.out.println("공개 여부 : " + vo.getSecretYn());
			System.out.println("수정 일자 : " + vo.getEditDate());
			System.out.println("조회수 : " + vo.getHit());
			System.out.println("=========================");
			
		}catch(Exception e) {
			System.out.println("게시판 상세 조회 실패..");
			e.printStackTrace();
		}
	}
	
	
	
	
}//class
