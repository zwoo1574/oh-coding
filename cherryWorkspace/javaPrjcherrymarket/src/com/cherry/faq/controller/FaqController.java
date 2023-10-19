package com.cherry.faq.controller;

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
	
	//게시판 등록
	public void write() {
		try {
			System.out.println("-----게시판 등록-----");
			//데이터
			System.out.print("제목: ");
			String title = Main.SC.nextLine();
			System.out.println("내용: ");
			String content = Main.SC.nextLine();
			
			FaqVo vo = new FaqVo();
			vo.setTitle(title);
			vo.setContent(content);
			
			//서비스
			int result = fs.write();
			
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
	
	//게시판 조회(no기본키로)
	
	//게시판 전체 조회(관리자만)
	
}//class
