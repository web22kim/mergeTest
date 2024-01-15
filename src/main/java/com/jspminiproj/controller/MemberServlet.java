package com.jspminiproj.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspminiproj.service.MemberService;

@WebServlet("*.mem")  // .mem으로 끝나는 모든 매핑에 대해..... 현재 서블릿에서 처리함
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		System.out.println("doGet");
		doService(request, response);
	}
	
	// GET, POST어떤 방식으로 호출되던지, 아래의 메서드가 호출됨.
	private void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청한 페이지 : " + request.getRequestURL());
		System.out.println("요청한 URI : "  + request.getRequestURI());
		System.out.println("요청한 통신방식 : "  + request.getMethod());
		System.out.println("컨텍스트 패스 : " + request.getContextPath());
		
		// 요청된 서블릿 매핑 주소를 통해 기능을 분류 
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("최종 요청된 서비스 : " + command);
		
		// MemberFactory.java를 만들고 와서...
		MemberFactory mf = MemberFactory.getInstance();
		MemberService service =  mf.getService(command);
		
		// MemberService 객체가 가지고 있는 공통의 메서드 호출
		// 실질적으로 executeService() 메서드에서 request / response 처리를 한다
		if (service != null) {
			mf = service.executeService(request, response);
		}
		
//		if (mf != null) { // 아직 reponse처리가 완료되지 않았음)
//			
//		}

		// response를 처리해 보면
		if (mf != null && mf.isRedirect()) {
			response.sendRedirect(mf.getWhereToGo());
		}
		// mf == null인 경우가 있었다. ajax->json으로 반환할 경우에는 mf = null
		return ;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

}
