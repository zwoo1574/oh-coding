package com.cherry.notice.controller;

import com.cherry.notice.service.NoticeService;
import com.cherry.notice.vo.NoticeVo;

public class NoticeController {
//	//필드
//	private final NoticeService service;
//	
//	//기본 생성자
//		public NoticeController() {
//			service=new NoticeService();
//		}
//	//메뉴선택
//		public void selectMenu()  {
//			System.out.println("===NOTICE===");
//			
//			System.out.println("1. 공지글 작성");
//			System.out.println("2. 공지글 수정");
//			System.out.println("2. 공지글 조회(최신순)");
//			System.out.println("3. 공지글 상세조회(번호)");
//			System.out.println("4. 공지글 삭제(관리자만)");
//			
//			
//			String num=Main.SC.nextLine();
//			switch(num) {
//			case "1": write(); break;
////			case "2": modify(); break;
////			case "3": noticeList(); break;
////			case "4": noticeDetailByNo(); break;
////			case "5": delete(); break;
//			default:System.out.println("잘못입력"); 
//			}
//		}
//	public void write() {
//		System.out.println("===게시글 작성===");
//		try {
//		
//		//로그인 여부 체크
//		if(Main.loginMember==null) {
//			throw new Exception("회원만 게시글 작성이 가능합니다.");
//		}
//		
//			데이터
//			System.out.print("제목: ");
//			String title=Main.SC.nextLine();
//			System.out.print("내용: ");
//			String content=Main.SC.nextLine();
//			
//			NoticeVo vo= new NoticeVo();
//			vo.setTitle(title);
//			vo.setContent(content);
//			
//			//서비스
//			int result=service.write(vo);
//			
//			//결과
//			if(result!=1){
//				throw new Exception();
//			}
//			System.out.println("게시글 작성");
//		}catch(Exception e) {
//			System.out.println("작성 실패");
//			e.printStackTrace();
//		}
//		
//	}
//	

}
