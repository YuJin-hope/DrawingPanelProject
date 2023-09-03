package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import frames.GDrawingPanel;
import frames.GMenuBar;
import frames.GToolBar;

public class GMainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GMenuBar menuBar; // 자식 3개 만듦
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;

	public GMainFrame() {
	// attribute
		// 속성 정리한 것, 내가 가진 특별한 값들
		// 자식 만들기 전에 내 속성부터 지정하는 것
		//this.setVisible(true); 
		this.setLocation(GConstants.CMainFrame.x, GConstants.CMainFrame.y);
		this.setSize(GConstants.CMainFrame.w, GConstants.CMainFrame.h);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// components
	// 자식 만들기 시작하기, add는 등록하는 것
	this.menuBar = new GMenuBar(); 
	this.setJMenuBar(menuBar);
	
	BorderLayout layout = new BorderLayout(); //순서대로 그려라는 뜻
	this.setLayout(layout);
	
	this.toolBar = new GToolBar();
	this.add(this.toolBar, BorderLayout.NORTH);
	
	this.drawingPanel = new GDrawingPanel();
	this.add(drawingPanel, BorderLayout.CENTER); //자식으로 만들기
	
	// association
	this.drawingPanel.setToolBar(toolBar); // 드로잉패널과 툴바 연결됨
}
}
