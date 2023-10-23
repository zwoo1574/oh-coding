package com.cherry.main;

import java.util.Scanner;

import com.cherry.faq.controller.FaqController;
import com.cherry.manager.controller.ManagerController;
import com.cherry.manager.vo.ManagerVo;
import com.cherry.member.controller.MemberController;
import com.cherry.member.vo.MemberVo;

public class Main {

	   public static final Scanner SC = new Scanner(System.in);
	   public static MemberVo loginMember;
	   public static ManagerVo loginManager;
	   
	   public static void main(String[] args) {

	      MemberController memberController = new MemberController();
	      ManagerController managerController = new ManagerController();
	      
	      while(true) {
	         System.out.println("1: 유저 2: 관리자");
	         String num = Main.SC.nextLine();
	         
	         switch(num) {
	         case "1" : while(true) { memberController.memberMenu();} 
	         case "2" : while(true) { managerController.managerMenu();} 
	         default : System.out.println("잘못 입력하였습니다.");
	         }
	      }
	   }

}
