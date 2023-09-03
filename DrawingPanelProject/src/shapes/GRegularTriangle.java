package shapes;

import java.awt.Polygon;

public class GRegularTriangle extends GShape {
    private static final int SIDES = 4; // 평행사변형의 변 수
    private int[] xPoints;
    private int[] yPoints;

    public GRegularTriangle() {
        xPoints = new int[SIDES];
        yPoints = new int[SIDES];
    }

    @Override
    public GShape clone() {
        return new GRegularTriangle();
    }

    @Override
    public void setShape(int x1, int y1, int x2, int y2) {
        int width = x2 - x1;
        int height = y2 - y1;

        xPoints[0] = x1;
        yPoints[0] = y1;
        xPoints[1] = x2;
        yPoints[1] = y1;
        xPoints[2] = x2 - width;
        yPoints[2] = y2;
        xPoints[3] = x1 - width;
        yPoints[3] = y2;

        this.shape = new Polygon(xPoints, yPoints, SIDES);
    }

    @Override
    public void resizePoint(int x, int y) {
        int width = Math.abs(xPoints[1] - xPoints[0]);
        int height = Math.abs(yPoints[2] - yPoints[1]);

        if (x < xPoints[0]) {
            xPoints[1] = xPoints[0] + width;
            xPoints[2] = x;
            xPoints[3] = x - width;
        } else {
            xPoints[1] = x;
            xPoints[2] = x - width;
            xPoints[3] = xPoints[0] - width;
        }

        if (y < yPoints[0]) {
            yPoints[2] = yPoints[0] + height;
            yPoints[3] = yPoints[1] + height;
        } else {
            yPoints[2] = y;
            yPoints[3] = yPoints[1];
        }

        this.shape = new Polygon(xPoints, yPoints, SIDES);
    }

    @Override
    public void setPoint(int x, int y) {
        xPoints[0] = x;
        yPoints[0] = y;
        xPoints[1] = x;
        yPoints[1] = y;
        xPoints[2] = x;
        yPoints[2] = y;
        xPoints[3] = x;
        yPoints[3] = y;
    }

    @Override
    public void movePoint(int x, int y) {
        int dx = x - xPoints[0];
        int dy = y - yPoints[0];

        for (int i = 0; i < SIDES; i++) {
            xPoints[i] += dx;
            yPoints[i] += dy;
        }

        this.shape = new Polygon(xPoints, yPoints, SIDES);
    }
}
