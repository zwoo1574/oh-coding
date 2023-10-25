package com.cherry.main;

import java.util.Scanner;

import com.cherry.main.controller.MainController;
import com.cherry.manager.vo.ManagerVo;
import com.cherry.member.vo.MemberVo;

public class Main {

	   public static final Scanner SC = new Scanner(System.in);
	   public static MemberVo loginMember;
	   public static ManagerVo loginManager;
	   
	   public static void main(String[] args) {

	      MainController controller = new MainController();

	      
	         System.out.println("1.유저 2.관리자");
	         System.out.print("번호를 입력해주세요: ");
	         String num = Main.SC.nextLine();
	         
	         switch(num) {
	         case "1" : while(true) {controller.loginMenu();}
	         case "2" : while(true) {controller.manager.loginManager(); if(Main.loginManager != null) {controller.managerLoginMenu();}}
	         default : System.out.println("잘못 입력하였습니다.");
	         }
	      
	   }

}
