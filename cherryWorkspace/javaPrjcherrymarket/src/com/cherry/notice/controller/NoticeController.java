package com.cherry.notice.controller;

import java.util.ArrayList;

import com.cherry.main.Main;
import com.cherry.notice.service.NoticeService;
import com.cherry.notice.vo.NoticeVo;

public class NoticeController {
	// 필드
	private final NoticeService service;

	// 기본 생성자
	public NoticeController() {
		service = new NoticeService();
	}

	// 메뉴선택
	public void selectMenu() {
		System.out.println("===NOTICE===");

		System.out.println("1. 공지글 작성");
		System.out.println("2. 공지글 수정");
		System.out.println("3. 공지글 조회(최신순)");
		System.out.println("4. 공지글 상세조회(번호)");
		System.out.println("5. 공지글 삭제(관리자만)");

		String num = Main.SC.nextLine();
		switch (num) {
		case "1": write(); break;
//		case "2": noticeDetailByNo(); break;
		case "3": noticeList(); break;
//		case "4": modify(); break;
//		case "5": delete(); break;
		default:
			System.out.println("잘못입력 하셨습니다.");
		}
	}

	// 공지글 작성
	public void write() {
		System.out.println("===공지글 작성===");
		try {
			//데이터
			System.out.print("제목: ");
			String title=Main.SC.nextLine();
			System.out.print("내용: ");
			String content=Main.SC.nextLine();
			System.out.print("어떤 관리자가 작성할지: "); //해줘야 되는지 
			String managerNo=Main.SC.nextLine();
			
			NoticeVo vo= new NoticeVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setManagerNo(managerNo);
			
			//서비스
			int result=service.write(vo);
			
			//결과
			if(result!=1){
				throw new Exception();
			}
			System.out.println("공지글 작성에 성공하셨습니다.");
		}catch(Exception e) {
			System.out.println("작성에 실패 하셨습니다.");
			e.printStackTrace();
		}
	
	}

	// 공지글 목록 조회(최신순)
	public void noticeList() {
		System.out.println("==공지글 목록 조회==");
		try {
			//서비스
			ArrayList<NoticeVo> voList =service.noticeList();
			
			//결과
			System.out.print("번호");
			System.out.print(" / ");
			System.out.print("제목");
			System.out.print(" / ");
			System.out.print("닉네임");
			System.out.print(" / ");
			System.out.print("조회수");
			System.out.print(" / ");
			System.out.println("작성일자");
			
			for(NoticeVo vo: voList) {
				System.out.print(vo.getNo());
				System.out.print(" / ");
				System.out.print(vo.getTitle());
				System.out.print(" / ");
				System.out.print(vo.getWriterNick());
				System.out.print(" / ");
				System.out.print(vo.getHit());
				System.out.print(" / ");
				System.out.println(vo.getEnrolldate());
				}
			
		}catch(Exception e) {
			System.out.println("공지글 목록 조회를 실패 하셨습니다.");
			e.printStackTrace();
			}
		
	
	//게시판 수정
	
	//게시판 삭제(update del_yn)
		
	//게시판 상세 조회(no기본키로)
		
		
		
	}	
}
