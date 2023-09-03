package shapes;

import java.awt.Polygon;

public class GPolygon extends GShape { // 목적 -> 그림(네모) 그리기
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int px, py;

	public GPolygon() { // 네 개의 점이 필요함 -> 필수적으로 필요한 위치 정보
	}

	@Override
	public GShape clone() {
		return new GPolygon();
	}

	@Override
	public void setShape(int x1, int y1, int x2, int y2) { // 좌표만 잡아야 함
		this.shape = new Polygon();
		Polygon polygon = (Polygon) shape;
		polygon.addPoint(x1, y1);
		polygon.addPoint(x2, y2);
	}

	public void addPoint(int x, int y) {
		Polygon polygon = (Polygon) shape;
		polygon.addPoint(x, y);
	}

	@Override
	public void resizePoint(int x, int y) { // 마지막 점을 움직이면 된다
		Polygon polygon = (Polygon) shape;
		polygon.xpoints[polygon.npoints - 1] = x; // 0에서 9까지기 때문에
		polygon.ypoints[polygon.npoints - 1] = y;
		shape = polygon;
	}

	@Override
	public void setPoint(int x, int y) {
		px = x;
		py = y;
	}

	@Override
	public void movePoint(int x, int y) {
		Polygon polygon = (Polygon) shape;
		Polygon newPolygon = new Polygon(); // newPolygon 만들어서 계속 쓰기 -> 기존대로 바꿔야, 지금 문제 : 계속 그리기 하면 폴리곤 좌표가 계속 변경되는 문제
		for (int i = 0; i < polygon.npoints; i++) {
			newPolygon.addPoint(polygon.xpoints[i] + x - px, polygon.ypoints[i] + y - py);
//			newPolygon.xpoints[i] = polygon.xpoints[i] + x - px; // 0에서 9까지기 때문에
//			newPolygon.ypoints[i] = polygon.ypoints[i] + y - py;
		}
		this.shape = newPolygon;
//		this.shape = polygon;
		px = x;
		py = y;
	}
}