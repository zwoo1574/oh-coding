package com.cherry.main;

import java.util.Scanner;

import com.cherry.main.controller.MainController;
import com.cherry.manager.vo.ManagerVo;
import com.cherry.member.vo.MemberVo;
import com.cherry.util.Color;

public class Main {

	   public static final Scanner SC = new Scanner(System.in);
	   public static MemberVo loginMember;
	   public static ManagerVo loginManager;
	   
	   public static void main(String[] args) {

	      MainController controller = new MainController();
	      
			System.out.println(""
					+ ""
					+ "\r\n"
					+ Color.RED +" █████████╗ "+Color.EXIT+Color.GREEN+"██╗     ██╗"+Color.BLUE+" ████████╗ ████████╗  ████████╗ ██╗     ██╗ "+Color.RED+"  ███╗      ███╗"+Color.YELLOW+"   █████╗   ████████╗  ██╗    ██╗ ████████╗██████████╗\r\n"
					+ Color.RED +"██╔═══════╝ "+Color.EXIT+Color.GREEN+"██║     ██║ "+Color.BLUE+"██╔═════╝ ██╔════██╗ ██╔════██╗╚██    ██╔╝ "+Color.RED+"  ████╗    ████║"+Color.YELLOW+" ██╔════██╗ ██╔════██╗ ██║   ██╔╝ ██╔═════╝╚═══██╔═══╝\r\n"
					+ Color.RED +"██║         "+Color.EXIT+Color.GREEN+"██████████║ "+Color.BLUE+"████████╗ ████████╔╝ ████████╔╝  ╚████╔═╝  "+Color.RED+"  ██╔══████╔═██║"+Color.YELLOW+" █████████║ ████████╔╝ ██████╔╝   ███████═╗    ██║   \r\n"
					+ Color.RED +"██║         "+Color.EXIT+Color.GREEN+"██ ╔════██║ "+Color.BLUE+"██╔═════╝ ██╔════██╗ ██╔════██╗   ╚██╔╝    "+Color.RED+"  ██║  ╚██╔╝ ██║"+Color.YELLOW+" ██╔════██║ ██╔════██╗ ██╔═══██╗  ██╔═════╝    ██║   \r\n"
					+ Color.RED +"╚██████████╗"+Color.EXIT+Color.GREEN+"██ ║    ██║ "+Color.BLUE+"████████╗ ██║    ██║ ██║    ██║    ██║     "+Color.RED+"  ██║   ╚═╝  ██║"+Color.YELLOW+" ██║    ██║ ██║    ██║ ██║    ██╗ ████████╗    ██║   \r\n"
					+ Color.RED +" ╚═════════╝"+Color.EXIT+Color.GREEN+"╚══╝    ╚═╝ "+Color.BLUE+"╚═══════╝ ╚═╝    ╚═╝ ╚═╝    ╚═╝    ╚═╝     "+Color.RED+"  ╚═╝        ╚═╝"+Color.YELLOW+" ╚═╝    ╚═╝ ╚═╝    ╚═╝ ╚═╝    ╚═╝ ╚═══════╝    ╚═╝"+Color.RED+"🍒 "+Color.EXIT+"  \r\n"
					+ "                                                                                                         \r\n"
					+ "" );
	      
			 System.out.println("                                             "+Color.RED +"▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃"+Color.EXIT + "​​​​​                                           ");
			 System.out.println();
	         System.out.println("                                                         " +Color.YELLOW +"➊" + Color.EXIT + "유저   "+  Color.RED +"➋" + Color.EXIT + "관리자");
	         System.out.println("                                                        번호를 입력해주세요 " + Color.CYAN);
	         System.out.print("                                                                "); 
	         String num = Main.SC.nextLine();
	         System.out.println(Color.EXIT);
	         System.out.println("                                             "+Color.RED +"▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃​​​​​▃▃​​​​​▃▃▃"+Color.EXIT + "​​​​​                                           ");
	         
	         switch(num) {
	         case "1" : while(true) {controller.loginMenu();}
	         case "2" : while(true) {controller.manager.loginManager(); if(Main.loginManager != null) {controller.managerLoginMenu();}}
	         default : System.out.println("잘못 입력하였습니다.");
	         }
	      
	   }

}
