package transformer;

import java.awt.Graphics2D;
import java.util.Vector;

import shapes.GShape;

public class GResizer extends GTransformer {

	public GResizer(GShape shape) {
		super(shape);
	}

	@Override
	public void initTransform(int x, int y, Graphics2D graphics2d) {
		shape.setPoint(x, y);
		shape.draw(graphics2d);
	}

	@Override
	public void keepTransform(int x, int y, Graphics2D graphics2d) {
		shape.draw(graphics2d);
		shape.resizePoint(x, y);
		shape.draw(graphics2d);
	}

	@Override
	public void finalizeTransform(int x, int y, Graphics2D graphics2d, Vector<GShape> shapes) {
		shape.draw(graphics2d);
	}
}
