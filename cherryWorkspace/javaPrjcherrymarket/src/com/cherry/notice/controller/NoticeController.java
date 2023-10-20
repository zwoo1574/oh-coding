package com.cherry.notice.controller;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;

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
		System.out.println("3. 최신순으로 공지글 조회");
		System.out.println("4. 제목으로 공지글 검색");
		System.out.println("5. 공지 번호로 검색해서 글 상세조회");
		System.out.println("6. 공지글 공개여부(관리자만)");

		String num = Main.SC.nextLine();
		switch (num) {
		case "1":write(); break;
//		case "2": modify(); break;
		case "3":noticeList(); break;
		case "4":searchNoticeByTitle(); break;		
		case "5": noticeDetailByNo(); break;
//		case "6": secret(); break;
		default: System.out.println("잘못입력 하셨습니다.");
		}
	}

	// 공지글 작성
	public void write() {
		System.out.println("===공지글 작성===");
		try {
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
			System.out.println("작성에 실패 하셨습니다.");
			e.printStackTrace();
		}

	}

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
			System.out.println("공지글 목록 조회를 실패 하셨습니다.");
			e.printStackTrace();
			}
	}
		
	//공지글 상세 조회(no기본키로)
	public void noticeDetailByNo() {
		try{
			System.out.println("==공지글 상세 조회==");
			//데이터
			System.out.print("공지글 번호: ");
			String num= Main.SC.nextLine();
			
			//서비스
			NoticeVo vo =service.noticeDetailByNo(num);
			
			//결과
			if(vo==null) {
				throw new Exception();
			}
			System.out.println("------------------------------");
			System.out.println("글번호: "+vo.getNo());
			System.out.println("제목: "+vo.getTitle());
			System.out.println("내용: "+vo.getContent());
			System.out.println("닉네임: "+vo.getManagerName());
			System.out.println("조회수: "+vo.getHit());
			System.out.println("작성일시: "+vo.getEnrolldate());
			System.out.println("------------------------------");
			
		}catch(Exception e) {
			System.out.println("공지글 상세 조회 실패");
			e.printStackTrace();
		}
		
	}
	
	//공지글 검색(제목)
	public void searchNoticeByTitle() {
		try{
			System.out.println("==공지글 검색==");
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
				System.out.println("내용: "+vo.getContent());
				System.out.println("닉네임: "+vo.getManagerName());
				System.out.println("조회수: "+vo.getHit());
				System.out.println("작성일시: "+vo.getEnrolldate());
				System.out.println("------------------------------");
			}
		}catch(Exception e) {
			System.out.println("검색에 실패 하셨습니다.");
			e.printStackTrace();
		}
		
		
	}
	//공지글 수정
	
	//공지글 감추기(update del_yn)
	public void secret() {
		
		try {
			System.out.println("==공지글 비공개==");
			//데이터
			System.out.print("공지글 번호: ");
			String num=Main.SC.nextLine();
			System.out.println("관리자 번호");
			String mgNo=Main.SC.nextLine();
			
			//서비스
			HashMap<String, String> map= new HashMap<String,String>();
			map.put("noticeNum", num);
			map.put("managerNo", mgNo);
			int result=service.secret(map);
			
			//결과
			if(result!=1) {
				throw new Exception();
			}
			System.out.println("공지글 비공개 완료");

		}catch(Exception e) {
			System.out.println("공지글 비공개 실패");
			e.printStackTrace();
		}
	}
		
		
		
	}

