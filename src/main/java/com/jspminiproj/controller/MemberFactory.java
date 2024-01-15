package com.jspminiproj.controller;

import com.jspminiproj.service.MemberService;
import com.jspminiproj.service.member.ConfirmMailCodeService;
import com.jspminiproj.service.member.DuplicateUserIdService;
import com.jspminiproj.service.member.LoginMemberService;
import com.jspminiproj.service.member.LogoutMemberService;
import com.jspminiproj.service.member.MyPageService;
import com.jspminiproj.service.member.RegisterMemberService;
import com.jspminiproj.service.member.SendMailService;

public class MemberFactory {
	// 여러명의 유저가 한꺼번에 접속하면 생성자로 객체가 계속 만들어지게 하지 않기 위해
	// 싱글톤으로 만든다. 
	private static MemberFactory instance = null;
	// 파일처리하면서 추가할 멤버 변수
	private boolean isRedirect; // redirect 할 것인지 말 것인지 (default는 false)
	private String whereToGo;  // 어느 view단으로 이동할것인지
	
	private MemberFactory () { }
	
	public static MemberFactory getInstance() {
		if (instance == null) {
			instance = new MemberFactory();
		}
		return instance;
	}
	
	
	// getter, setter 
	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getWhereToGo() {
		return whereToGo;
	}

	public void setWhereToGo(String whereToGo) {
		this.whereToGo = whereToGo;
	}

	/**
	 * 공통 서블릿에서 command를 받아 필요한 서비스단의 객체를 반환해 주는 메서드
	 * @param command: 공통서블릿단에서 주는 요청한 서비스 객체
	 * 반환값 타입 : MemberService 
	 */	
	public MemberService getService(String command) {
		MemberService result = null;
		
		if (command.equals("/member/duplicateUserId.mem")) {
			// 아이디 중복 검사를 할 수 있는 객체를 만들어서 반환
			result = new DuplicateUserIdService();
		} else if (command.equals("/member/registerMember.mem")) {
			// 회원 가입을 할 수 있는 객체를 만들어서 반환
			result = new RegisterMemberService();
		} else if (command.equals("/member/sendMail.mem")) {
			// 인증코드를 메일로 보내는 기능을 할 수 잇는 객체를 만들어서 반환
			result = new SendMailService();
		} else if (command.equals("/member/confirmCode.mem")) {
			result = new ConfirmMailCodeService();
		} else if (command.equals("/member/login.mem")) {
			result = new LoginMemberService();
		} else if (command.equals("/member/logout.mem")) {
			result = new LogoutMemberService();
		} else if (command.equals("/member/myPage.mem")) {
			result = new MyPageService();
		}
		
		return result;
	}
	
}
