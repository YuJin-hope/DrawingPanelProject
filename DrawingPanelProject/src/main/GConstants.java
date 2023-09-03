package main;

import shapes.GLine;
import shapes.GOval;
import shapes.GParallelogram;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GRegularTriangle;
import shapes.GSelect;
import shapes.GShape;
import shapes.GTextBox;
import shapes.GTriangle;

public class GConstants {
	public class CMainFrame{
		static final int x = 200;
		static final int y = 100;
		static final int w = 800;
		static final int h = 600;
	}
	
	public enum EUserAction {
		e2Point,
		eNPoint
	}
	
	public enum EAnchors {
		NW,
		NN,
		NE,
		EE,
		SE,
		SS,
		SW,
		WW,
		RR,
		MM // move
	}
	
	public enum EShape { // EButtonShape 내 놓여진 순서대로 순서가 정해짐 -> 공유 영역으로 옮겨질 코드
		// selection tool
		eSelect("Select", new GSelect(), EUserAction.e2Point), 
		// drawing tool
		eRectangle("Rectangle", new GRectangle(), EUserAction.e2Point), 
		eOval("Oval", new GOval(), EUserAction.e2Point), 
		eLine("Line", new GLine(), EUserAction.e2Point),
		ePolygon("Polygon", new GPolygon(), EUserAction.eNPoint),
		eTriangle("Triangle", new GTriangle(), EUserAction.eNPoint),
		eRegularTriangle("RegularTriangle", new GRegularTriangle(), EUserAction.eNPoint),
		eParallelogram("평행사변형", new GParallelogram(), EUserAction.e2Point),
		eTextbox("Text", new GTextBox(), EUserAction.e2Point);

		private String name;
		private GShape gShape;
		private EUserAction eUserAction;
		
		private EShape(String name, GShape gShape, EUserAction eUserAction) {
			this.name = name;
			this.gShape = gShape;
			this.eUserAction = eUserAction;
		}
		public String getName() {
			return this.name;
		}
		public GShape getGShape() {
			return this.gShape;
		}
		public EUserAction getEUserAction() {
			return this.eUserAction;
		}
	}
}
