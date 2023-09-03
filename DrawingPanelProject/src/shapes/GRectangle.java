package shapes;

import java.awt.Rectangle;

public class GRectangle extends GShape {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int px, py;

    public GRectangle() {
    }

    @Override
    public GShape clone() {
        return new GRectangle();
    }

    @Override
    public void setShape(int x1, int y1, int x2, int y2) {
        this.shape = new Rectangle(x1, y1, x2 - x1, y2 - y1);
    }

    @Override
    public void resizePoint(int x, int y) {
        Rectangle rectangle = (Rectangle) shape.getBounds();
        rectangle.setSize(x - rectangle.x, y - rectangle.y);
        shape = rectangle;
    }

    @Override
    public void setPoint(int x, int y) {
        this.px = x;
        this.py = y;
    }

    @Override
    public void movePoint(int x, int y) {
        Rectangle rectangle = (Rectangle) shape;
        rectangle.setLocation(rectangle.x + x - px, rectangle.y + y - py);
        this.px = x;
        this.py = y;
    }
}