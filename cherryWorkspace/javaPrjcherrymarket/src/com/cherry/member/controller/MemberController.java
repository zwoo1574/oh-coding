package com.cherry.member.controller;

import java.util.*;

import com.cherry.main.Main;
import com.cherry.member.service.MemberService;
import com.cherry.member.vo.MemberVo;
import com.cherry.trade.vo.TradeVo;

	
public class MemberController {
	
	private final MemberService service;
	
	public MemberController() {
		service = new MemberService();
	}
 	
	public void memberMenu() {
		System.out.println("===== 메뉴선택 =====");
		
		System.out.print(" 1: 회원가입");
		System.out.print(" 2: 로그인");
		System.out.print(" 3: 로그아웃");
		System.out.print(" 4: 내 정보 보기");	//+닉네임,비밀번호,전화번호,주소 변경 + 회원탈퇴[Dath 구조]
		System.out.print(" 5: 구매내역");		//유저가 구매한 내역 확인 
		System.out.print(" 6: 관심 목록 조회"); //+관심 목록 제거
		System.out.println(" 7: 매너온도");
		
		/*사용자 기능 - 뺴 먹은 거*/
		System.out.println("10: 아이디 찾기");
		System.out.println("11: 비밀번호 찾기");
		
		String num = Main.SC.nextLine();
		
		switch(num) {
		case "1" :join(); break;
		case "2" :login(); break;
		case "3" :logout(); break;
		case "4" :Info(); break;
		case "5" :purchaseList(); break;
		case "6" :wishList(); break;
		case "7" :score(); break;
		}
	}
	//매너온도 (추천수) 확인
	private void score() {
		System.out.println("===== 매너 온도 ======");
		try {
			//로그인 검사
			if(Main.loginMember == null) {
				throw new Exception("로그인부터 진행해주세요");
			}
			
			String no = Main.loginMember.getMemberNo();
			String score = service.score(no);
			
			if(score == null) {
				throw new Exception();
			}
			
			System.out.println("매너 온도 : "+score+"점");
			
		}catch(Exception e) {
			System.out.println("매너 온도 확인 실패");
			e.printStackTrace();
		}
		
	}

	// 내정보 보기 (마이페이지)
	private void Info() {
		System.out.println("===== 마이페이지 ======");
		try {
			//로그인 검사
			if(Main.loginMember == null) {
				throw new Exception("로그인부터 진행해주세요");
			}
			
			//결과
			System.out.println("========== 마이페이지 ==========");
			System.out.println("아이디 : "+Main.loginMember.getId());
			System.out.println("닉네임 : "+Main.loginMember.getNick());
			System.out.println("이름 : "+Main.loginMember.getName());
			System.out.println("전화번호 : "+Main.loginMember.getPhone());
			System.out.println("이메일 : "+Main.loginMember.getEmail());
			System.out.println("동네명 : "+Main.loginMember.getAreasName());
			System.out.println("가입일시 : "+Main.loginMember.getJoinDate());
			System.out.println("==============================");
			
			//정보변경
			System.out.println("1. 비밀번호 변경 2: 닉네임 변경 3: 주소 변경 4:전화번호 변경 5: 회원 탈퇴 9:이전으로 돌아가기");
			String num = Main.SC.nextLine();
			
			switch(num) {
			case "1" : changePwd(); break;
			case "2" : changeNick(); break;
			case "3" : changeAddress(); break;
			case "4" : changePhone(); break;
			case "5" : quit(); break;
			case "9" : return; 
			default : System.out.println("잘못 입력하였습니다.");
			}
			
			
		}catch(Exception e) {
			System.out.println("마이페이지 조회 실패");
			e.printStackTrace();
		}
		
	}

	//회원가입
	public void join() {
		System.out.println("===== 회원가입 ======");
		try {
			System.out.println("ID : ");
			String id = Main.SC.nextLine();
			System.out.println("PWD : ");
			String pwd = Main.SC.nextLine();
			System.out.println("NICK : ");
			String nick = Main.SC.nextLine();
			System.out.println("NAME : ");
			String name = Main.SC.nextLine();
			System.out.println("EMAIL : ");
			String email = Main.SC.nextLine();
			System.out.println("PHONE : ");
			String match = "[^0-9]";	// 0~9까지 숫자만
			String phone = Main.SC.nextLine().replaceAll(match,"");
			System.out.println("ADRESS : ");
			String adress = Main.SC.nextLine().replace(" ","");
			
			MemberVo vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			vo.setNick(nick);
			vo.setName(name);
			vo.setEmail(email);
			vo.setPhone(phone);
			vo.setAddress(adress);
			
			int result = service.join(vo); 
			
			if(result != 1) {
				throw new Exception();
			}
				System.out.println("회원가입 성공");
			
		}catch(Exception e) {
			System.out.println("회원가입 실패");
			e.printStackTrace();
		}
	}
	//로그인
	public void login() {
		System.out.println("===== 로그인 ======");
		try {
			if(Main.loginMember != null) {
				System.out.println("이미 로그인 상태입니다.");
				return;
			}
			
			System.out.println("ID : ");
			String id = Main.SC.nextLine();
			System.out.println("PWD : ");
			String pwd = Main.SC.nextLine();
			
			MemberVo vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			
			Main.loginMember = service.login(vo); 
			
			if(Main.loginMember == null) {
				throw new Exception();
			}
			System.out.println("로그인 성공");
			System.out.println(Main.loginMember.getName()+" 님 환영합니다.");
		}catch(Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
	}
	//로그아웃
	public void logout() {
		System.out.println("===== 로그아웃 ======");
		if(Main.loginMember == null) {
			System.out.println("로그인 상태가 아닙니다");
			return;
		}
		Main.loginMember = null;
		System.out.println("로그아웃 되었습니다.");
	}
	//회원탈퇴
	public void quit() {
		System.out.println("===== 회원탈퇴 ======");
		try {
			
			String no = Main.loginMember.getMemberNo();
			
			int result = service.quit(no);
			
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("회원탈퇴 성공");	
			logout();
			
		}catch(Exception e) {
			System.out.println("회원탈퇴 실패");
			e.printStackTrace();
		}
	}

	//비밀번호 변경
	private void changePwd() {
		System.out.println("===== 비밀번호 변경 ======");
		try {
			
			System.out.println("현재 비밀번호 입력 : ");
			String oldPwd = Main.SC.nextLine();
			
			System.out.print("새로운 비밀번호를 입력하세요 : ");
			String newPwd = Main.SC.nextLine();
			
			String memberNo = Main.loginMember.getMemberNo();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("oldPwd",oldPwd);
			map.put("newPwd",newPwd);
			map.put("memberNo",memberNo);
			
			int result = service.changePwd(map);
			
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("비밀번호 변경 완료");
			logout();
		}catch(Exception e) {
			System.out.println("비밀번호 변경 실패");
			e.printStackTrace();
		}
	}

	//닉네임 변경
	private void changeNick() {
		System.out.println("===== 닉네임 변경 ======");
		try {
			
			System.out.print("새로운 닉네임을 입력하세요 : ");
			String newNick = Main.SC.nextLine();
			MemberVo vo = new MemberVo();
			
			vo.setNick(newNick);
			vo.setMemberNo(Main.loginMember.getMemberNo());
			
			//서비스
			int result = service.changeNick(vo);
			
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("닉네임 변경 완료");
			logout();
		}catch(Exception e) {
			System.out.println("닉네임 변경 실패");
			e.printStackTrace();
		}	
	}
	//주소 변경
	private void changeAddress() {
		System.out.println("===== 주소 변경 ======");
		try {
			
			System.out.print("새로운 주소를 입력하세요 : ");
			String newAddress = Main.SC.nextLine().replace(" ","");
			MemberVo vo = new MemberVo();
			
			vo.setAddress(newAddress);
			vo.setMemberNo(Main.loginMember.getMemberNo());
			
			//서비스
			int result = service.changeAddress(vo);
			
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("주소 변경 완료");
			logout();
		}catch(Exception e) {
			System.out.println("주소 변경 실패");
			e.printStackTrace();
		}	
		
	}
	//전화번호 변경
	private void changePhone() {
		System.out.println("===== 전화번호 변경 ======");
		try {
			
			System.out.print("새로운 전화번호를 입력하세요 : ");
			String match = "[^0-9]";	// 0~9까지 숫자만
			String newPhone = Main.SC.nextLine().replaceAll(match,"");
			MemberVo vo = new MemberVo();
			
			vo.setPhone(newPhone);
			vo.setMemberNo(Main.loginMember.getMemberNo());
			
			//서비스
			int result = service.changePhone(vo);
			
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("전화번호 변경 완료");
			logout();
		}catch(Exception e) {
			System.out.println("전화번호 변경 실패");
			e.printStackTrace();
		}	
	}
	
	//구매 내역 리스트
	private void purchaseList() {
		System.out.println("===== 구매 내역 목록 ======");
		try {
			//로그인 검사
			if(Main.loginMember == null) {
				throw new Exception("로그인부터 진행해주세요");
			}
			String no = Main.loginMember.getMemberNo();
			List<TradeVo> voList = service.purchaseList(no);
			
			for(TradeVo vo : voList) {
				System.out.println("====================");
				System.out.println("구매일시 | 판매자닉네임 | 거래 장소 | 상품명 | 가격");
				System.out.println(vo.getEnrollDate()+" | "+vo.getMemberNick()+" | "+vo.getTradeAreas()+" | "+vo.getProduct()+" | "+vo.getPrice());
			}
			
		}catch(Exception e) {
			System.out.println("구매 내역 조회 실패");
			e.printStackTrace();
		}
	}
	//관심목록 리스트
	private void wishList() {
		System.out.println("===== 관심 목록 리스트======");
		try {
			//로그인 검사
			if(Main.loginMember == null) {
				throw new Exception("로그인부터 진행해주세요");
			}
			String no = Main.loginMember.getMemberNo();
			List<TradeVo> voList = service.wishList(no);
			
			for(TradeVo vo : voList) {
				System.out.println("====================");
				System.out.println("글번호 | 거래상태 | 게시글명 | 상품 | 가격");
				System.out.println(vo.getBoardNo()+" | "+vo.getCompleteYn()+" | "+vo.getTitle()+" | "+vo.getProduct()+" | "+vo.getPrice());
			}
			
			//정보변경
			System.out.println("1.관심목록 삭제 9:이전으로 돌아가기");
			String num = Main.SC.nextLine();
			
			switch(num) {
			case "1" : wishDelete(); break;
			case "9" : return; 
			default : System.out.println("잘못 입력하였습니다.");
			}
			
		}catch(Exception e) {
			System.out.println("관심 목록 조회 실패");
			e.printStackTrace();
		}
	}
	//관심목록 제거
	private void wishDelete() {
		System.out.println("===== 관심 목록 삭제======");
		try {
			
			System.out.println("삭제할 게시글 번호를 입력해주세요 : ");
			String BoardNo = Main.SC.nextLine();
			String memberNo = Main.loginMember.getMemberNo();
			
			int result = service.wishDelete(memberNo,BoardNo);
			
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("관심 목록 삭제 완료");
			return;
		}catch(Exception e) {
			System.out.println("관심 목록 삭제 실패");
			e.printStackTrace();
		}
	}
}