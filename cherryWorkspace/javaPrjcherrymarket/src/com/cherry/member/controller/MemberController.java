package com.cherry.member.controller;

import com.cherry.main.Main;
import com.cherry.member.service.MemberService;
import com.cherry.member.vo.MemberVo;

public class MemberController {
	
private final MemberService service;
	
	public MemberController() {
		service = new MemberService();
	}
	
	public void selectMemu() {
		System.out.println("===== 메뉴선택 =====");
		
		System.out.println("1: 회원가입 2: 로그인 3: 로그아웃 4: 회원탈퇴");
		String num = Main.SC.nextLine();
		
		switch(num) {
		case "1" :join(); break;
		case "2" :login(); break;
		case "3" :logout(); break;
		case "4" :quit(); break;
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
		
		
	}
}
