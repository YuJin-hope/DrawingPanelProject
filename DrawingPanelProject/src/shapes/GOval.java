package shapes;

import java.awt.geom.Ellipse2D;

public class GOval extends GShape { // 목적 -> 그림(네모) 그리기
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int px, py;

	public GOval() { // 네 개의 점이 필요함 -> 필수적으로 필요한 위치 정보
	}

	@Override
	public GShape clone() {
		return new GOval();
	}

	@Override
	public void setShape(int x1, int y1, int x2, int y2) {
		this.shape = new Ellipse2D.Double(x1, y1, x2 - x1, y2 - y1);
	}

	@Override
	public void resizePoint(int x2, int y2) {
		double x = shape.getBounds2D().getX();
		double y = shape.getBounds2D().getY();
		double width = x2 - x;
		double height = y2 - y;
		Ellipse2D ellipse2D = new Ellipse2D.Double(x, y, width, height);
		shape = ellipse2D;
	}

	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}

	@Override
	public void movePoint(int x, int y) {
		Ellipse2D ellipse2D = (Ellipse2D) shape;
		ellipse2D.setFrame(ellipse2D.getX() + x - px, ellipse2D.getY() + y - py, ellipse2D.getWidth(),
				ellipse2D.getHeight());
		this.px = x;
		this.py = y;
	}
}