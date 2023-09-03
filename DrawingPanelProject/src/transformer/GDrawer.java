package transformer;

import java.awt.Graphics2D;
import java.util.Vector;

import shapes.GShape;

public class GDrawer extends GTransformer {
	public GDrawer(GShape shape) {
		super(shape);
	}

	@Override
	public void initTransform(int x, int y, Graphics2D graphics2d) {
		this.shape.setShape(x, y, x, y);
	}
	
	@Override
	public void keepTransform(int x, int y, Graphics2D graphics2D) {
	    this.shape.draw(graphics2D);
	    this.shape.resizePoint(x, y);
	    this.shape.draw(graphics2D);
	}

//	@Override
//	public void finalizeTransform(int x, int y, Graphics2D graphics2D, Vector<GShape> shapes) {
//		//shapes.add(this.shape); // Vector에 처음 그릴 때만 추가하면 됨
//		this.shape.setSelected(true); // 그림 그리고나면 select가 됨
//		this.shape.draw(graphics2D);
//	}
//	
	
	// 마우스로 선택해야 setSElected 됨
	@Override
	public void finalizeTransform(int x, int y, Graphics2D graphics2D, Vector<GShape> shapes) {
	     // 계속 그리기
	    shapes.add(shape); // 폴리곤을 shapes 벡터에 추가
	    //this.shape = null; // shape 객체 초기화
	    this.shape.draw(graphics2D);
	}
	
	public void continueTransforming(int x, int y, Graphics2D graphics2D, Vector<GShape> shapes) {
	    shapes.add(shape); 
	    shape.addPoint(x, y);
	}

}
