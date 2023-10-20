package com.cherry.qna.controller;

import java.util.List;

import com.cherry.main.Main;
import com.cherry.qna.service.QnaService;
import com.cherry.qna.vo.QnaVo;

public class QnaController {
	
	private final QnaService service;

	//기본 생성자
	public QnaController() {
		service = new QnaService();
	}
	
	// 메뉴선택
	public void selectMenu() {
		System.out.println("======= QnA Menu =======");
		
		System.out.println("1. 문의글 작성");
		System.out.println("2. 문의글 목록 (최신순)");
		System.out.println("3. 문의글 상세조회 (번호)");
		System.out.println("4. 문의글 내가 쓴것만 보기");
		System.out.println("5. 문의글 목록 (관리자용)");
		System.out.println("6. 문의글 검색 (제목)");
		System.out.println("7. 관리자 답변작성");
		System.out.println("8. 관리자 답변수정");
		
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : write();break;
		case "2" : qnaList();break;
		case "3" : qnaDetailByNo();break;
//		case "4" : qnaMyList();break;
//		case "5" : 
//		case "6" : 
//		case "7" : 
//		case "8" : 
		}
		
	}//selectMenu end
	
	// 문의글 작성
	public void write() {
		
		try {
			System.out.println("------- 문의글 작성 -------");
			
			//로그인 여부
			if(Main.loginMember == null) {
				throw new Exception("회원만 문의글 작성이 가능합니다.");
			}
			
			// 데이터
			System.out.print("제목: ");
			String title = Main.SC.nextLine();
			System.out.print("내용: ");
			String content = Main.SC.nextLine();
			
			QnaVo vo = new QnaVo();
			vo.setTitle(title);
			vo.setContent(content);
			
			// 서비스
			int result = service.write(vo);
			
			// 결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("문의글 작성 완료 !!!");
		}catch(Exception e) {
			System.out.println("문의글 작성 실패 ...");
			e.printStackTrace();
		}
			
	}//write end
	
	// 문의글 목록 (최신순)
	public void qnaList() {
		
		try {
			System.out.println("------- 문의글 목록 -------");
			
			// 데이터
			
			// 서비스
			List<QnaVo> voList = service.qnaList();
			
			// 결과
			System.out.print("번호");
			System.out.print(" / ");
			System.out.print("제목");
			System.out.print(" / ");
			System.out.print("닉네임");
			System.out.print(" / ");
			System.out.print("조회수");
			System.out.print(" / ");
			System.out.print("작성일자");
			System.out.println();
			
			for(QnaVo vo : voList) {
				System.out.print(vo.getQnaNo());
				System.out.print(" / ");
				System.out.print(vo.getTitle());
				System.out.print(" / ");
				System.out.print(vo.getWriterNick());
				System.out.print(" / ");
				System.out.print(vo.getHit());
				System.out.print(" / ");
				System.out.print(vo.getMemberEnrollDate());
				System.out.println();
			}
			
		}catch(Exception e) {
			System.out.println("문의글 목록 조회 실패 ...");
			e.printStackTrace();
		}
		
	}//qnaList end
	
	//문의글 상세조회 (번호)
	public void qnaDetailByNo() {
		
		try {
			System.out.println("------- 문의글 상세 조회 (번호) -------");
			
			// 데이터
			System.out.print("조회할 문의글 번호: ");
			String num = Main.SC.nextLine();
			
			// 서비스
			QnaVo vo = service.qnaDetailByNo(num);
			
			// 결과
			if(vo == null) {
				throw new Exception();
			}
			System.out.println("---------------------------------------");
			System.out.println("글번호: " + vo.getQnaNo());
			System.out.println("제목: " + vo.getTitle());
			System.out.println("작성자: " + vo.getWriterNick());
			System.out.println("조회수: " + vo.getHit());
			System.out.println("작성일자: " + vo.getMemberEnrollDate());
			System.out.println("내용: " + vo.getContent());
			System.out.println("---------------------------------------");
			
		}catch(Exception e) {
			System.out.println("문의글 상세 조회 실패 ...");
			e.printStackTrace();
		}
		
	}//qnaDetailByNo end
	
	//문의글 내가 쓴것만 보기
//	public void qnaMyList() {
//
//		try {
//			System.out.println("------- 내가 쓴 문의글 조회 -------");
//			
//			//데이터
//			
//			//서비스
//			List<QnaVo> voList = service.qnaMyList();
//			
//			//결과
//			System.out.print("번호");
//			System.out.print(" / ");
//			System.out.print("제목");
//			System.out.print(" / ");
//			System.out.print("닉네임");
//			System.out.print(" / ");
//			System.out.print("조회수");
//			System.out.print(" / ");
//			System.out.print("작성일자");
//			System.out.println();
//			
//			for(QnaVo vo : voList) {
//				System.out.print(vo.getQnaNo());
//				System.out.print(" / ");
//				
//			}
//			
//			
//		}catch(Exception e) {
//			
//		}
//		
//		
//		
//	}//qnaMyList
//	
	
}
