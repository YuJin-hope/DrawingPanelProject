package shapes;

import java.awt.geom.Line2D;
// 라인 두께 조정해서 마우스 포인트에 잡히도록 하기 intercept
public class GLine extends GShape{ // 목적 -> 그림(네모) 그리기
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GLine() { // 네 개의 점이 필요함 -> 필수적으로 필요한 위치 정보 
	}
	@Override
	public GShape clone() {
		// TODO Auto-generated method stub
		return new GLine();
	}
	@Override
	public void setShape(int x1, int y1, int x2, int y2) {
		this.shape = new Line2D.Double(x1, y1, x2, y2);
	}
	@Override
	public void resizePoint(int x2, int y2) { // line은 다르게 쓰겠다 
		Line2D line2D = (Line2D)shape;
		line2D.setLine(line2D.getX1(), line2D.getY1(), x2, y2);
	}
	@Override
	public void movePoint(int x, int y) {
		// TODO Auto-generated method stub
	}
	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub
	}	
}