package com.cherry.manager.controller;

import com.cherry.main.Main;

public class ManagerController {
	
	public void managerMenu() {
		System.out.println("===== 메뉴선택 =====");
		
		/* 관리자 기능 */
		System.out.println("1: 관리자 회원전체 조회");
		System.out.println("2: 관리자 회원 상세 조회(번호/아이디/닉네임)");
		System.out.println("3: 관리자 회원 강제 탈퇴");
		System.out.println("4: 관리자 회원 비번 초기화");

		String num = Main.SC.nextLine();
		
		switch(num) {
//		case "1" :userList(); break;
//		case "2" :userDetile(); break;
//		case "3" :userkick(); break;
//		case "4" :userPwdReset(); break;
		}
	}

}
