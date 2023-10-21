package com.cherry.member.controller;

import java.util.*;

import com.cherry.main.Main;
import com.cherry.member.service.MemberService;
import com.cherry.member.vo.MemberVo;

	
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
		System.out.print(" 4: 비밀번호 변경");
		System.out.print(" 5: 닉네임 변경");
		System.out.print(" 6: 내 정보 보기");	//+닉네임변경 +비밀번호 변경 [Dath 구조]
		System.out.println(" 7: 회원탈퇴");
		System.out.println(" 8: 구매내역");		//유저가 구매한 내역 확인
		System.out.println(" 9: 관심목록");
		System.out.println("10: 매너온도");
		
		/*사용자 기능 - 뺴 먹은 거*/
		System.out.println("15: 아이디 찾기");
		System.out.println("16: 비밀번호 찾기");
		
		String num = Main.SC.nextLine();
		
		switch(num) {
		case "1" :join(); break;
		case "2" :login(); break;
		case "3" :logout(); break;
		case "4" :changePwd(); break;
		case "5" :changeNick(); break;
		case "6" :Info(); break;
		case "7" :quit(); break;
		case "8" :purchaseList(); break;
//		case "9" :wishList(); break;
//		case "10" :score(); break;
		}
	}
	private void purchaseList() {
		System.out.println("===== 구매내역목록 ======");
		
		
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
			System.out.println("1. 비밀번호 변경 2: 닉네임 변경 3: 주소 변경 4:전화번호 변경 9:이전으로 돌아가기");
			String num = Main.SC.nextLine();
			
			switch(num) {
			case "1" : changePwd(); break;
			case "2" : changeNick(); break;
			case "3" : changeAddress(); break;
			case "4" : changePhone(); break;
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
			if(Main.loginMember == null) {
				System.out.println("로그인 후 회원 탈퇴를 시도하세요");
			}
			
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
			if(Main.loginMember == null) {
				throw new Exception("로그인 후 이용해주세요");
			}
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
			if(Main.loginMember == null) {
				throw new Exception("로그인 후 이용해주세요");
			}
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
			if(Main.loginMember == null) {
				throw new Exception("로그인 후 이용해주세요");
			}
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
			if(Main.loginMember == null) {
				throw new Exception("로그인 후 이용해주세요");
			}
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

}