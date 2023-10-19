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
		
		System.out.println(" 1: 회원가입");
		System.out.println(" 2: 로그인");
		System.out.println(" 3: 로그아웃");
		System.out.println(" 4: 비밀번호 변경");
		System.out.println(" 5: 닉네임 변경");
		System.out.println(" 6: 내 정보 보기");
		System.out.println(" 7: 회원탈퇴");
		System.out.println(" 8: 구매내역");
		System.out.println(" 9: 관심목록");
		System.out.println("10: 매너온도");
		
		String num = Main.SC.nextLine();
		
		switch(num) {
		case "1" :join(); break;
		case "2" :login(); break;
		case "3" :logout(); break;
		case "4" :editPwd(); break;
//		case "5" :editNick(); break;
//		case "6" :Info(); break;
		case "7" :quit(); break;
//		case "8" :purchaseList(); break;
//		case "9" :wishList(); break;
//		case "10" :score(); break;
		}
	}
	//비밀번호 변경
	private void editPwd() {
		System.out.println("===== 비밀번호 변경 ======");
		try {
			if(Main.loginMember == null) {
				throw new Exception("로그인 후 이용해주세요");
			}
			System.out.print("새로운 비밀번호를 입력하세요 : ");
			String newPwd = Main.SC.nextLine();
			String oldPwd = Main.loginMember.getPwd();
			HashMap<String, String> map = new HashMap<String,String>();
			map.put("newPwd", newPwd);
			map.put("oldPwd",oldPwd);
			int result = service.editPwd(map);
			
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("비밀번호 변경 완료");
			
		}catch(Exception e) {
			System.out.println("비밀번호 변경 실패");
			e.printStackTrace();
		}
		
	}
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
			String phone = Main.SC.nextLine();
			System.out.println("ADRESS : ");
			String adress = Main.SC.nextLine();
			
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
	public void login() {
		System.out.println("===== 로그인 ======");
		try {
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
		}catch(Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
	}
	public void logout() {
		System.out.println("===== 로그아웃 ======");
		if(Main.loginMember == null) {
			System.out.println("로그인 상태가 아닙니다");
			return;
		}
		Main.loginMember = null;
		System.out.println("로그아웃 되었습니다.");
		
	}

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
				
			
		}catch(Exception e) {
			System.out.println("회원탈퇴 실패");
			e.printStackTrace();
		}
		
		
	}
}
