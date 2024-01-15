package com.jspminiproj.controller;

import com.clonemp.service.BoardService;
import com.jspminiproj.service.board.DeleteBoardService;
import com.jspminiproj.service.board.GetBoardByNoService;
import com.jspminiproj.service.board.GetEntireBoardService;
import com.jspminiproj.service.board.GetEntireBoardServiceSearch;
import com.jspminiproj.service.board.ReplyBoardService;
import com.jspminiproj.service.board.WriteBoardService;
import com.jspminiproj.service.board.testService;


public class BoardFactory {
	// 주석 추가
	private boolean isRedirect;
	private String whereToGo;
	
	private static BoardFactory instance = null;
	
	
	private BoardFactory() { }
	
	public static BoardFactory getInstance() {
		if (instance == null) {
			instance = new BoardFactory();
		}
		
		return instance;
	}
	
	
	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getWhereToGo() {
		return whereToGo;
	}

	public void setWhereToGo(String whereisGo) {
		this.whereToGo = whereisGo;
	}

	public BoardService getService(String command) {
		BoardService service = null;
		
		if (command.equals("/board/listAll.bo")) {
//			service = new GetEntireBoardService();
			service = new GetEntireBoardServiceSearch();
			
		} else if (command.equals("/board/writeBoard.bo")) {
			service = new WriteBoardService();
			
		} else if (command.equals("/board/viewBoard.bo")) {
			service = new GetBoardByNoService();
			
		} else if (command.equals("/board/delBoard.bo")) {
			service = new DeleteBoardService();
			
		} else if (command.equals("/board/reply.bo")) {
			service = new ReplyBoardService();
		} 
		
		return service;
	}
	
	
}
