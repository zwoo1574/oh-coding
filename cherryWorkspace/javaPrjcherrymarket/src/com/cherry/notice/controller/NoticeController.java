package com.cherry.notice.controller;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;

import com.cherry.main.Main;
import com.cherry.notice.service.NoticeService;
import com.cherry.notice.vo.NoticeVo;

import oracle.sql.ARRAY;

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
//		Main.loginManager.setManagerNo("1");
		System.out.println("1. 공지글 작성");
		System.out.println("2. 공지글 공개여부");
		System.out.println("3. 공지글 수정");
		System.out.println("4. 최신순으로 공지글 조회");
		System.out.println("5. 조회순으로 공지글 조회");
		System.out.println("6. 제목으로 공지글 검색");
		System.out.println("7. 내용으로 공지글 검색");
		System.out.println("8. 공지 번호로 검색해서 글 상세조회");

		String num = Main.SC.nextLine();
		switch (num) {
		case "1":write(); break;
		case "2": secret(); break;
		case "3": modify(); break;
		case "4":noticeList(); break;
		case "5":noticeList2(); break;
		case "6":searchNoticeByTitle(); break;		
		case "7":searchNoticeByContent(); break;		
		case "8": noticeDetailByNo(); break;
		default: System.out.println("잘 못 입력하셨습니다.");
		}
	}

	// 공지글 작성(관리자만)
	public void write() {
		try {
//			if(Main.loginManager==null) {
//			throw new Exception("관리자 로그인 하고 진행하세요");
//			}
			System.out.println("===공지글 작성===");
			// 데이터
			System.out.print("제목: ");
			String title = Main.SC.nextLine();
			System.out.print("내용: ");
			String content = Main.SC.nextLine();
			System.out.print("어떤 관리자가 작성할지: "); 
			String managerNo = Main.SC.nextLine();

			NoticeVo vo = new NoticeVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setManagerNo(managerNo);

			// 서비스
			int result = service.write(vo);

			// 결과
			if (result != 1) {
				throw new Exception();
			}
			System.out.println("공지글 작성에 성공하셨습니다.");
		} catch (Exception e) {
			System.out.println("공지글 작성에 실패하셨습니다.");
			e.printStackTrace();
		}

	}//write() end

	// 공지글 조회(최신순)
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
				System.out.print(vo.getManagerName());
				System.out.print(" / ");
				System.out.print(vo.getHit());
				System.out.print(" / ");
				System.out.println(vo.getEnrolldate());
				}
			
		}catch(Exception e) {
			System.out.println("공지글 조회를 실패하였습니다.");
			e.printStackTrace();
			}
	}//noticeList() end
		
	//공지글 상세 조회(no기본키로)
	public void noticeDetailByNo() {
		try{
			System.out.println("==공지글 상세 조회==");
			//데이터
			System.out.print("조회할 공지글 번호: ");
			String num= Main.SC.nextLine();
			
			//서비스
			NoticeVo vo =service.noticeDetailByNo(num);
			
			//결과
			if(vo==null) {
				throw new Exception("비공개 공지글 입니다.");
			}
			System.out.println("------------------------------");
			System.out.println("글번호: "+vo.getNo());
			System.out.println("제목: "+vo.getTitle());
			System.out.println("내용: "+vo.getContent());
			System.out.println("관리자 닉네임: "+vo.getManagerName());
			System.out.println("조회수: "+vo.getHit());
			System.out.println("작성일시: "+vo.getEnrolldate());
			System.out.println("------------------------------");
			
		}catch(Exception e) {
			System.out.println("공지글 상세 조회 실패");
			e.printStackTrace();
		}
		
	}//noticeDetailByNo() end
	
	//공지글 검색(제목)
	public void searchNoticeByTitle() {
		try{
			System.out.println("==제목으로 공지글 검색==");
			//데이터
			System.out.print("제목 검색: ");
			String search=Main.SC.nextLine();
			
			//서비스
			ArrayList<NoticeVo> voList=service.searchNoticeByTitle(search);
			
			//결과
			if(voList.size()==0) {
				System.out.println("검색 결과가 없습니다.");
			}
			
			for(NoticeVo vo: voList) {
				System.out.println("------------------------------");
				System.out.println("글번호: "+vo.getNo());
				System.out.println("제목: "+vo.getTitle());
				System.out.println("닉네임: "+vo.getManagerName());
				System.out.println("조회수: "+vo.getHit());
				System.out.println("작성일시: "+vo.getEnrolldate());
				System.out.println("------------------------------");
			}
		}catch(Exception e) {
			System.out.println("검색에 실패 하셨습니다.");
			e.printStackTrace();
		}
		
		
	}//searchNoticeByTitle() end
	
	//공지글 수정(관리자만,비공개 된 계시판만)
	public void modify() {
		
		try{
//			if(Main.loginManager==null) {
//				throw new Exception("관리자 로그인 하고 진행하세요");
			System.out.println("==공지글 수정==");
			//데이터
			System.out.print("수정할 공지글 번호: ");
			String num=Main.SC.nextLine();
			System.out.print("수정할 공지글 제목: ");
			String title=Main.SC.nextLine();
			System.out.print("수정할 공지글 내용: ");
			String content=Main.SC.nextLine();
			
			
			NoticeVo vo= new NoticeVo();
			vo.setNo(num);
			vo.setTitle(title);
			vo.setContent(content);
			
	
			//서비스
			int result=service.modify(vo);
			//결과
			if(result!=1) {
				throw new Exception("비공개 공지글이 아닙니다.");
			}
			System.out.println("공지글 수정을 성공하셨습니다.");
		}catch(Exception e) {
			System.out.println("공지글 수정을 실패하셨습니다.");
			e.printStackTrace();
		}
	}//modify() end
	
	//공지글 공개 여부(관리자만)
	public void secret() {
		
		try {
//			if(Main.loginManager==null) {
//				throw new Exception("관리자 로그인 하고 진행하세요");
//			}
			System.out.println("==공지글 공개 여부==");
			//데이터
			System.out.print("공지글 번호: ");
			String num=Main.SC.nextLine();
			System.out.print("공지글 공개 여부: ");
			String choice=Main.SC.nextLine();
		
			HashMap<String, String> map= new HashMap<String,String>();
			map.put("noticeNum", num);
			map.put("secretChoice", choice);
			
			//서비스
			int result=service.secret(map);
			
			//결과
			if(result!=1) {
				throw new Exception();
			}
			System.out.println("공지글 공개 여부를 완료하셨습니다.");

		}catch(Exception e) {
			System.out.println("공지글 공개 여부를 실패하셨습니다.");
			e.printStackTrace();
		}
	}//secret() end
	
	//공지글 조회(조회수순) 
	public void noticeList2() {
		//데이터
		
		try {
			System.out.println("==공지글 목록 조회==");
			//서비스
			ArrayList<NoticeVo> voList =service.noticeList2();
			
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
				System.out.print(vo.getManagerName());
				System.out.print(" / ");
				System.out.print(vo.getHit());
				System.out.print(" / ");
				System.out.println(vo.getEnrolldate());
				}
		}catch(Exception e) {
			System.out.println("공지글 조회를 실패하였습니다.");
			e.printStackTrace();
		}
	}//noticeList2 end
	
	public void searchNoticeByContent() {
		
		try {
			System.out.println("==내용으로 공지글 검색==");
			//데이터
			System.out.print("내용 검색: ");
			String content=Main.SC.nextLine();
			
			//서비스
			ArrayList<NoticeVo> voList= service.searchNoticeByContent(content);
			
			System.out.println();
			
			//결과
			for(NoticeVo vo: voList) {
				System.out.println("------------------------------");
				System.out.println("글번호: "+vo.getNo());
				System.out.println("제목: "+vo.getTitle());
				System.out.println("닉네임: "+vo.getManagerName());
				System.out.println("조회수: "+vo.getHit());
				System.out.println("작성일시: "+vo.getEnrolldate());
				System.out.println("------------------------------");
			}
		}catch (Exception e) {
			System.out.println("검색에 실패 하셨습니다.");
			e.printStackTrace();
		}
	
		
	}
		
		
	}//class

