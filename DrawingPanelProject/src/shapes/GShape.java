package shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

import main.GConstants.EAnchors;

public abstract class GShape implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Shape shape;
	private boolean bSelected;
	private GAnchors gAnchors;

	public GShape() {
		this.bSelected = false;
		this.gAnchors = new GAnchors();
	}

	public Shape getShape() {
		return this.shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public boolean onShape(Point p) {
		return shape.contains(p);
	}

	public void draw(Graphics2D graphics2D) {
		graphics2D.draw(shape);
		if (this.bSelected) {
			this.gAnchors.draw(graphics2D, this.shape.getBounds());
		}
	}

	public void setSelected(boolean bSelected) {
		this.bSelected = bSelected;
	}

	public EAnchors onShape(int x, int y) {
		if (this.bSelected) {
			EAnchors eAnchor = this.gAnchors.onShape(x, y);
			if (eAnchor != null) {
				return eAnchor;
			}
		}
		if (this.shape != null) {
			if (this.shape.contains(x, y)) {
				return EAnchors.MM;
			}
		}
		return null;
	}

	public abstract GShape clone();

	public abstract void setShape(int x1, int y1, int x2, int y2);

	public abstract void setPoint(int x, int y);

	public abstract void resizePoint(int x, int y);

	public abstract void movePoint(int x, int y);

	public void addPoint(int x, int y) {
	}

	public void getShape(Shape createTransformedShape) {
	}
}
