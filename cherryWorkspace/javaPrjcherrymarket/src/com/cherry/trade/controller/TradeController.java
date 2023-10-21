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
	// 게시글 조회 - 메뉴
	public void postMenu(String boardNo) {
		try {
			TradeVo vo = ts.searchPostInfo(boardNo);
			
			if(Main.loginMember.getMemberNo().equals(vo.getMemberNo())) {            
				
				System.out.println("<메뉴> 1. 게시글 수정 2. 게시글 삭제 3. 목록으로 돌아가기");
				
				System.out.print("선택할 메뉴 : " + Color.CYAN);
				String select = sc.nextLine();
				System.out.print(Color.EXIT);
				
				switch(select) {
				
					case "1" : editPost(boardNo); break;
					case "2" : delPost(boardNo); break;
					case "3" : return;
					default : System.out.println("잘못 입력하셨습니다");
				} 					
				
			} else if(!Main.loginMember.getMemberNo().equals(vo.getMemberNo())) {
				
				System.out.println("<메뉴> 1. 구매 신청 2. 목록으로 돌아가기");
				System.out.print("선택할 메뉴 : " + Color.CYAN);
				String select = sc.nextLine();
				System.out.print(Color.EXIT);
				
				switch(select) {
					case "1" : purchaseRequest(vo); break;
					case "2" : return;
					default : System.out.println("잘못 입력하셨습니다.");
					
				}
			} 
			// 관리자일때 목록 추가
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// 구매 신청
	private void purchaseRequest(TradeVo vo) {
		try {
			System.out.println("구매신청");
			System.out.println(vo);
			System.out.print("거래완료 (y/n) : ");
			String x = sc.nextLine();
			switch(x) {
				case "y" : 
					ts.confirmPurchase(vo);   // 구매확정 (히스토리 남기기) -- 미완성
					writeReview(vo); 				   // 후기 남기기 (매너온도 포함) -- 미완성
					break; 
				case "n" : return;
				default : System.out.println("잘못 입력했습니다");
			}
		} catch(Exception e) {
			System.out.println("구매 확정 실패");
			e.printStackTrace();
		}
	}
		
	

	// 구매후기 작성
	private void writeReview(TradeVo vo) {
		try {
			System.out.print("구매후기 : ");
			String content = sc.nextLine();
			
			int result = ts.writeReview(content, vo);
			
			if(result == 1) {
				System.out.println("구매후기 작성 완료");
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("구매후기 작성 실패");
			e.printStackTrace();
		}
		
	}

	
	// 게시글 수정
	public void editPost(String boardNo) {
		try {
			System.out.println(Color.RED + "                        게시글 수정 | 수정할 내용을 입력해주세요");
			System.out.println(Color.GREEN + "▃▃▃​​​​▃​​​​▃▃​​​​​​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			System.out.printf("제목 : ");
			String title = sc.nextLine();
			System.out.println(Color.GREEN + "▃▃▃​​​​​▃▃▃​​​​▃​​​​​▃​​​​​▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​▃▃​​​​​▃▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			System.out.print("물품명 : " );
			String product = sc.nextLine();
			System.out.print("가격 : ");
			String price = sc.nextLine();
			System.out.print("거래 장소 : ");
			String tradeArea = sc.nextLine();
			System.out.println("접속 지역 : " + Main.loginMember.getAreasName());
			System.out.println(Color.GREEN + "▃▃▃​​​​​▃▃▃​​​​​​​​​▃▃▃​​​​▃​​​​​▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			System.out.print("내용 : ");
			String content = sc.nextLine();
			System.out.println(Color.GREEN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​▃​​​​​▃​​​​​▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			System.out.println();
			
			TradeVo vo = new TradeVo();
			vo.setTitle(title);
			vo.setProduct(product);
			vo.setPrice(price);
			vo.setTradeAreas(tradeArea);
			vo.setContent(content);
			vo.setBoardNo(boardNo);
			
			int result = ts.editPost(vo);
			
			if(result != 1) {
				throw new Exception("게시글 수정 실패");
			}
			
			System.out.println("게시글 수정 완료");
			
		}  catch(Exception e) {
//			System.out.println("게시글 수정 실패");
			e.printStackTrace();
		}
		
	}

	// 게시글 삭제
	public void delPost(String boardNo) {
		try {
			System.out.println("비밀번호를 입력해주세요");
			System.out.print("비밀번호 : " + Color.CYAN);
			String pwd = sc.nextLine();
			System.out.print(Color.EXIT);
			
			int result = 0;
			if(pwd.equals(Main.loginMember.getPwd())) {
				result = ts.delPost(boardNo);
			} else {
				System.out.println("비밀번호가 올바르지 않습니다.");
			}
			
			if(result != 1) {
				throw new Exception();
			}
			
			System.out.println("게시글을 삭제했습니다.");
			
		} catch(Exception e) {
			System.out.println(Color.RED + "게시글 삭제 실패" + Color.EXIT);
			e.printStackTrace();
		}
		
	}

	
	// 게시글 목록 (중고거래 메인)
	public void tradeMain() {
		try {
			Util.clearConsole();
			
			System.out.println("접속중인 회원 : " + Color.YELLOW + Main.loginMember.getNick() + Color.EXIT);
			System.out.println("접속 지역 : " + Color.YELLOW + Main.loginMember.getAreasName() + Color.EXIT );
			System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			System.out.printf("%-30s%-50s%-30s%-30s%-30s%-30s%-30s\n", "번호", "제목", "물품명", "가격", "판매자", "작성일", "조회수");
			System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​▃▃​​​​​▃▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			List<TradeVo> voList = ts.printPost();
			for(TradeVo vo : voList) {
				
				if(vo.getMemberNo().equals(Main.loginMember.getMemberNo())) {
					System.out.printf("%-30s%-50s%-30s%-30s%-30s%-30s%-30s\n", Color.YELLOW  + vo.getBoardNo() + Color.EXIT, vo.getTitle(), vo.getProduct(), vo.getPrice(), vo.getMemberNick(), vo.getEnrollDate(), vo.getHit());
				} else {
					System.out.printf("%-30s%-50s%-30s%-30s%-30s%-30s%-30s\n", vo.getBoardNo(), vo.getTitle(), vo.getProduct(), vo.getPrice(), vo.getMemberNick(), vo.getEnrollDate(), vo.getHit());
				}
			}
			System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			mainMenu();
		} catch (Exception e) {
			System.out.println("목록 조회 실패");
			e.printStackTrace();
		}
	}
	
	// 게시글 목록 - 메뉴선택
	public void mainMenu() {
		System.out.println("<메뉴> 1. 게시글 작성 2. 게시글 조회 3. 관심목록에 추가 4. 메인으로 돌아가기");
		System.out.print("선택할 메뉴 : " + Color.CYAN);
		String select = sc.nextLine();
		System.out.print(Color.EXIT);
		
		switch(select) {
			case "1" : writePost(); break;
			case "2" : showContent(); break;
			case "3" : addWishList(); break;
			case "4" : return;
			default : System.out.println("잘못 입력하셨습니다.");
		}
	}
	
	// 게시글 목록(메인) -> 게시글 작성
		public void writePost() {
			try {
				Util.clearConsole();
				System.out.println("접속중인 회원 : " + Color.YELLOW + Main.loginMember.getNick() + Color.EXIT);
				System.out.println("접속 지역 : " + Color.YELLOW + Main.loginMember.getAreasName() + Color.EXIT );
				System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.println(Color.CYAN + "▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​▃▃​​​​​▃▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
				System.out.println("작성자 : " + Main.loginMember.getNick());
				System.out.print("물품명 : " );
				String product = sc.nextLine();
				System.out.print("가격 : ");
				String price = sc.nextLine();
				System.out.print("거래 장소 : ");
				String tradeArea = sc.nextLine();
				System.out.println("접속 지역 : " + Main.loginMember.getAreasName());
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
				
				if(result != 1) {
					throw new Exception();
				}
				
				System.out.println("게시글을 작성했습니다.");
				
			} catch(Exception e) {
				
				System.out.println(Color.RED + "게시글 작성 실패" + Color.EXIT);
				String ex = e.getMessage();
				
				if(ex.contains("TITLE")) {System.out.println("제목을 입력해주세요");} 
				else if(ex.contains("CONTENT")){System.out.println("내용을 입력해주세요");}
				else if(ex.contains("PRODUCT")){System.out.println("물품명을 입력해주세요");}
				else if(ex.contains("PRICE")){System.out.println("가격을 입력해주세요");}
				else if(ex.contains("TRADE_AREAS")){System.out.println("거래장소를 입력해주세요");}
				else if(ex.contains("ORA-01722")) {System.out.println("가격은 숫자로 입력해주세요");}			
				
//				e.printStackTrace();
			}
		}
	
	
	// 게시글 목록(메인) -> 게시글 조회
	public void showContent() {
		try {
			System.out.print("조회할 게시글 번호 : " + Color.CYAN);
			String select = sc.nextLine();
			System.out.print(Color.EXIT);
			
			TradeVo vo = ts.showContent(select);
			
			if(vo == null) {
				throw new Exception("존재하지 않는 게시글입니다");
			}
			
			Util.clearConsole();
			System.out.println("접속중인 회원 : " + Color.YELLOW + Main.loginMember.getNick() + Color.EXIT);
			System.out.println("접속 지역 : " + Color.YELLOW + Main.loginMember.getAreasName() + Color.EXIT );
			System.out.println(Color.CYAN + "▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			System.out.println(vo.getTitle());
			System.out.println(Color.CYAN + "▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			System.out.println("판매자 : " + vo.getMemberNick());
			System.out.println("물품명 : " + vo.getProduct());
			System.out.println("가격 : " + vo.getPrice());
			System.out.println("거래 장소 : " + vo.getTradeAreas());
			System.out.println("접속 지역 : " + vo.getAreasName());
			System.out.println(Color.CYAN + "▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			String content = vo.getContent();
			
	        String[] contents = content.split("#");
	       
	        for (String con : contents) {
	            System.out.println(con);
	        }
	        
			System.out.println(Color.YELLOW +"작성일자 : " +  vo.getEnrollDate() + Color.EXIT);
			
			if(vo.getEditDate() != null) {
				System.out.println(Color.YELLOW +"수정일자 : " + vo.getEditDate() + Color.EXIT);
			}
			System.out.println(Color.YELLOW + "조회수 : " + vo.getHit() + Color.EXIT);
			System.out.println(Color.CYAN + "▃▃▃​​​​​​​▃▃​​▃​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​▃▃▃​​​​​" + Color.EXIT);
			
			postMenu(vo.getBoardNo());
			
			
		} catch(Exception e) {
			System.out.println(Color.RED + "게시글 조회 실패" + Color.EXIT);
			String ex = e.getMessage();
			if(ex.contains("ORA-01722")) {System.out.println("숫자만 입력해주세요."); }
			
//			System.out.println(ex);
		}
	}
	
	// 게시글 목록(메인) -> 관심목록 추가
		public void addWishList() {
			try {
				System.out.print("관심목록에 추가할 게시글 번호 : " + Color.CYAN);
				String x = sc.nextLine();
				System.out.print(Color.EXIT);
				int result = ts.addWishList(x);
			
				if(result == 1) {
					System.out.println(Color.YELLOW+ x + "번 게시글을 관심목록에 추가했습니다." + Color.EXIT);
				} else {
					throw new Exception("이미 관심목록에 존재합니다.");
				}
			} catch(Exception e) {
				System.out.println(Color.RED + "관심목록 추가 실패" + Color.EXIT);
				String x = e.getMessage();
				if(x.contains("NULL")) {System.out.println("게시글 번호를 입력해주세요.");}
				else if(x.contains("ORA-02291")) {System.out.println("존재하지 않는 번호입니다.");}
				else {System.out.println(x);}
			}
		}
	
	
}
