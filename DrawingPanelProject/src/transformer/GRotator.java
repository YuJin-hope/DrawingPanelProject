package transformer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import shapes.GShape;

public class GRotator extends GTransformer {
    private double RRAngle; // 회전 각도

    public GRotator(GShape shape) {
        super(shape);
        this.RRAngle = 0;
    }

    @Override
    public void initTransform(int x, int y, Graphics2D graphics2d) {
        shape.setPoint(x, y);
        graphics2d.setColor(Color.white);
        shape.draw(graphics2d);
    }

    @Override
    public void keepTransform(int x, int y, Graphics2D graphics2d) {
    	graphics2d.setColor(Color.white);
    	shape.draw(graphics2d);
    }

    @Override
    public void finalizeTransform(int x, int y, Graphics2D graphics2d, Vector<GShape> shapes) {
        double deltaX = x - ((Shape) shape.getShape()).getBounds().getCenterX();
        double deltaY = y - ((Shape) shape.getShape()).getBounds().getCenterY();
        double newRotationAngle = Math.atan2(deltaY, deltaX);
        double rotationDelta = newRotationAngle - RRAngle;
        RRAngle = newRotationAngle;

        double rotationAngle = Math.toDegrees(rotationDelta);
        rotationAngle = Math.round(rotationAngle / 15) * 15;
        rotationDelta = Math.toRadians(rotationAngle);

        AffineTransform transform = new AffineTransform();
        transform.rotate(rotationDelta, ((Shape) shape.getShape()).getBounds().getCenterX(),
                ((Shape) shape.getShape()).getBounds().getCenterY());

        Shape transformedShape = transform.createTransformedShape(shape.getShape());

        // 회전 중간 과정 그리기
        //graphics2d.setColor(Color.WHITE); // 배경색으로 채우기 위해 흰색으로 설정
        //graphics2d.fill(transformedShape);

        shape.setShape(transformedShape);
        //graphics2d.setColor(Color.black);
        shape.draw(graphics2d);
    }
}
