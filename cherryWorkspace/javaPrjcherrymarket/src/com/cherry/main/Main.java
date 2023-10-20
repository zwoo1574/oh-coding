package com.cherry.main;

import java.util.Scanner;

import com.cherry.faq.controller.FaqController;
import com.cherry.member.vo.MemberVo;
import com.cherry.member.vo.ManagerVo;

public class Main {

	public static final Scanner SC = new Scanner(System.in);
	public static MemberVo loginMember;
	public static ManagerVo loginManager;
	
	public static void main(String[] args) {

		FaqController fc = new FaqController();
		fc.selectMenu();
	}
	
}
