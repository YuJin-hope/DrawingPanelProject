package shapes;

import java.awt.Polygon;

public class GTriangle extends GShape {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] xPoints;
    private int[] yPoints;

    public GTriangle() {
        xPoints = new int[3];
        yPoints = new int[3];
    }

    @Override
    public GShape clone() {
        return new GTriangle();
    }

    @Override
    public void setShape(int x1, int y1, int x2, int y2) {
        xPoints[0] = x1;
        yPoints[0] = y1;
        xPoints[1] = x2;
        yPoints[1] = y2;
        xPoints[2] = x1;
        yPoints[2] = y2;

        this.shape = new Polygon(xPoints, yPoints, 3);
    }

    @Override
    public void resizePoint(int x, int y) {
        xPoints[1] = x;
        yPoints[1] = y;
        xPoints[2] = xPoints[0];
        yPoints[2] = y;
        this.shape = new Polygon(xPoints, yPoints, 3);
    }

    @Override
    public void setPoint(int x, int y) {
        xPoints[0] = x;
        yPoints[0] = y;
        xPoints[1] = x;
        yPoints[1] = y;
        xPoints[2] = x;
        yPoints[2] = y;
    }

    @Override
    public void movePoint(int x, int y) {
        int dx = x - xPoints[0];
        int dy = y - yPoints[0];

        for (int i = 0; i < 3; i++) {
            xPoints[i] += dx;
            yPoints[i] += dy;
        }

        this.shape = new Polygon(xPoints, yPoints, 3);
    }
}
