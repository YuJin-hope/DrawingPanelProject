package transformer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import shapes.GShape;

public class GShear extends GTransformer {
    private double shearX; // x축 전단 비율
    private double shearY; // y축 전단 비율

    public GShear(GShape shape) {
        super(shape);
        this.shearX = 0;
        this.shearY = 0;
    }

    @Override
    public void initTransform(int x, int y, Graphics2D graphics2d) {
        shape.setPoint(x, y);
        shape.draw(graphics2d);
    }

    @Override
    public void keepTransform(int x, int y, Graphics2D graphics2d) {
        shape.draw(graphics2d);
        shearPoint(x, y);
        shape.draw(graphics2d);
    }

    @Override
    public void finalizeTransform(int x, int y, Graphics2D graphics2d, Vector<GShape> shapes) {
        shape.draw(graphics2d);
    }

    private void shearPoint(int x, int y) {
        double shearDeltaX = (double) x - shape.getShape().getBounds().getCenterX();
        double shearDeltaY = (double) y - shape.getShape().getBounds().getCenterY();
        double maxShear = 0.2; // 원하는 최대 전단 비율
        shearX = shearDeltaX / shape.getShape().getBounds().getWidth();
        shearY = shearDeltaY / shape.getShape().getBounds().getHeight();

        // 비율을 조정하여 최대 전단 비율을 초과하지 않도록 함
        if (shearX > maxShear)
            shearX = maxShear;
        else if (shearX < -maxShear)
            shearX = -maxShear;
        if (shearY > maxShear)
            shearY = maxShear;
        else if (shearY < -maxShear)
            shearY = -maxShear;

        AffineTransform transform = new AffineTransform();
        transform.shear(shearX, shearY);
        shape.setShape(transform.createTransformedShape(shape.getShape()));
    }
    
//    private void shearPoint(int x, int y) {
//        double shearDeltaX = (double) x - shape.getShape().getBounds().getCenterX();
//        double shearDeltaY = (double) y - shape.getShape().getBounds().getCenterY();
//        shearX = shearDeltaX / shape.getShape().getBounds().getWidth();
//        shearY = shearDeltaY / shape.getShape().getBounds().getHeight();
//        AffineTransform transform = new AffineTransform();
//        transform.shear(shearX, shearY);
//        shape.setShape(transform.createTransformedShape(shape.getShape()));
//    }

}
