package com.cherry.trade.controller;

import java.util.List;
import java.util.Scanner;

import com.cherry.main.Main;
import com.cherry.trade.service.TradeService;
import com.cherry.trade.vo.TradeVo;
import com.cherry.util.Color;
import com.cherry.util.Util;

public class TradeController {

	private final Scanner sc;
	private final TradeService ts;
	
	public TradeController() {
		sc = new Scanner(System.in);
		ts = new TradeService();
	}

	public void mainMenu() {
		System.out.println("<메뉴> 1. 게시글 작성 2. 게시글 조회 3. 관심목록에 추가 4. 메인으로 돌아가기 ");
		System.out.print("선택할 메뉴 : " + Color.CYAN);
		String select = sc.nextLine();
		System.out.print(Color.EXIT);
		switch(select) {
			case "1" : writePost(); break;
			case "2" : showContent(); break;
			case "3" : addWishList(); break;
			case "4" : System.out.println("메인으로 돌아가기"); break;
			default : System.out.println("잘못 입력하셨습니다.");
		}
	}
	
	public void addWishList() {
		
	}

	public void postMenu(String boardNo) {
		
		if(Main.loginMember != null) {               // 로그인중인 유저 넘버 불러와서 메뉴 선택창 다르게 만들기
			System.out.println("<메뉴> 1. 게시글 수정 2. 게시글 삭제 3. 목록으로 돌아가기");
			System.out.print("선택할 메뉴 : " + Color.CYAN);
			String select = sc.nextLine();
			switch(select) {
				case "1" : editPost(boardNo); break;
				case "2" : delPost(boardNo); break;
				case "3" : Util.clearConsole(); tradeMain(); break;
				default : System.out.println("잘못 입력하셨습니다");
			}
		}
	}

	public void writePost() {
		try {
			System.out.printf("%-120s\n", "접속중인 회원 : " + Color.YELLOW + Main.loginMember + Color.EXIT);
			System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			System.out.printf("제목 : ");
			String title = sc.nextLine();
			System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​▃▃​​​​​▃▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			System.out.print("물품명 : " );
			String product = sc.nextLine();
			System.out.print("가격 : ");
			String price = sc.nextLine();
			System.out.print("거래 지역 : ");
			String tradeArea = sc.nextLine();
	//		System.out.println("접속 지역 : " +); db에서 받아오기
			System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			System.out.print("내용 : ");
			String content = sc.nextLine();
			
			TradeVo vo = new TradeVo();
			vo.setTitle(title);
			vo.setProduct(product);
			vo.setPrice(price);
			vo.setTradeAreas(tradeArea);
			vo.setContent(content);
			
			int result = ts.writePost(vo);
			
			if(result == 1) {
				System.out.println("글 작성 완료");
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("글 작성 실패");
		}
	}
	
	public void editPost(String boardNo) {
		try {
			System.out.printf("%-120s\n", "접속중인 회원 : " + Color.YELLOW + Main.loginMember + Color.EXIT);
			System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			System.out.printf("제목 : ");
			String title = sc.nextLine();
			System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​▃▃​​​​​▃▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			System.out.print("물품명 : " );
			String product = sc.nextLine();
			System.out.print("가격 : ");
			String price = sc.nextLine();
			System.out.print("거래 지역 : ");
			String tradeArea = sc.nextLine();
//			System.out.println("접속 지역 : " + 로그인멤버.어쩌구); db에서 받아오기
			System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			System.out.print("내용 : ");
			String content = sc.nextLine();
			
			TradeVo vo = new TradeVo();
			vo.setTitle(title);
			vo.setProduct(product);
			vo.setPrice(price);
			vo.setTradeAreas(tradeArea);
			vo.setContent(content);
			vo.setBoardNo(boardNo);
			
			int result = ts.editPost(vo);
			
			if(result != 1) {
				System.out.println("게시글 수정 실패");
				return;
			}
			System.out.println("게시글 수정 완료");
		}  catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	// 게시글 삭제
	public void delPost(String boardNo) {
		try {
			System.out.println("비밀번호를 입력해주세요");
			System.out.print("비밀번호 : ");
			String pwd = sc.nextLine();
			
			int result = ts.delPost(pwd);
			
			if(result == 1) {
				System.out.println("게시글을 삭제했습니다.");
			} else {
				throw new Exception();
			}
			
		} catch(Exception e) {
			System.out.println("게시글 삭제 실패");
			e.printStackTrace();
		}
		
	}

	
	
	public void tradeMain() {
		try {
			Util.clearConsole();
			System.out.printf("%-120s\n", "접속중인 회원 : " + Color.YELLOW + Main.loginMember + Color.EXIT);
			System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			System.out.printf("%-30s%-50s%-30s%-30s%-30s%-30s\n", "번호", "제목", "물품명", "가격", "작성자", "날짜");
			System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​▃▃​​​​​▃▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			List<TradeVo> voList = ts.printPost();
			for(TradeVo vo : voList) {
				System.out.println(vo.getBoardNo() + vo.getTitle() + vo.getProduct() + vo.getPrice() + vo.getMemberNick() + vo.getEnrollDate());
			}
			System.out.println("▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃​​​​​▃▃▃​​​​​▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​");
			mainMenu();
		} catch (Exception e) {
			System.out.println("목록 조회 실패");
			e.printStackTrace();
		}
	}
	
	public void showContent() {
		try {
			System.out.print("조회할 게시글 번호 : " + Color.CYAN);
			String select = sc.nextLine();
			System.out.print(Color.EXIT);
			TradeVo vo = ts.showContent(select);
			if(vo != null) {
				Util.clearConsole();
				System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
				System.out.println(vo.getTitle());
				System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
				System.out.println("물품명 : " + vo.getProduct());
				System.out.println("가격 : " + vo.getPrice());
				System.out.println("거래지역 : " + vo.getTradeAreas());
				System.out.println("판매자 동네 : " + vo.getAreasName());
				System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
				System.out.println(vo.getContent() );
				String content = vo.getContent();
//				int x = (vo.getContent().length()) / 15 + 1;
//				for(int i = 15; x < 5; i++) {
//					System.out.println(content.substring(i));
//				}
				System.out.println(content.substring(0));
				
				System.out.println("작성일자 : " + vo.getEnrollDate());
				System.out.println("수정일자 : " + vo.getEditDate());
				System.out.println("▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​");
				postMenu(vo.getBoardNo());
				
			} else {
				System.out.println("게시글 없음");
				return;
			}
		} catch(Exception e) {
			System.out.println("게시글 조회 실패");
			e.printStackTrace();
		}
	}
	
	
}
