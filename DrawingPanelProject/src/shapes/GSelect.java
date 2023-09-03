package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GSelect extends GRectangle { // 목적 -> 그림(네모) 그리기
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GSelect() { // 네 개의 점이 필요함 -> 필수적으로 필요한 위치 정보
	}
	public void draw(Graphics graphics) { // 점선으로 
		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics2d.setColor(Color.BLUE);
		graphics2d.draw(shape);
	}
}