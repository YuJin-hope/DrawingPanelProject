package transformer;

//import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;

import shapes.GShape;

public class GSelector extends GTransformer {
    private Color backgroundColor;
    //private AlphaComposite composite;

    public GSelector(GShape shape) {
        super(shape);
    }

    @Override
    public void initTransform(int x, int y, Graphics2D graphics2D) {
        this.shape.setShape(x, y, x, y);
        backgroundColor = Color.WHITE; // 배경색을 흰색으로 설정
        graphics2D.setXORMode(backgroundColor);
    }

    @Override
    public void keepTransform(int x, int y, Graphics2D graphics2D) {
        this.shape.draw(graphics2D);
        this.shape.resizePoint(x, y);
        this.shape.draw(graphics2D); // 그리고 지우기
        
        // 색을 채우는 부분 추가
//        composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f); // 투명도 설정
//        graphics2D.setColor(Color.white); // 셀렉터 내부 색 지정해주기
//        graphics2D.setComposite(composite);
//        graphics2D.fill(this.shape.getShape()); // 도형의 내부를 채움
    }

    @Override
    public void finalizeTransform(int x, int y, Graphics2D graphics2D, Vector<GShape> shapes) {
        graphics2D.setXORMode(backgroundColor); // 배경색으로 XOR 모드 설정
        this.shape.draw(graphics2D); // 그리고 지우기
        
        // 색을 원래대로 되돌림
//        graphics2D.setComposite(AlphaComposite.SrcOver); // 투명도 원래대로 설정
//        graphics2D.setColor(backgroundColor); // 배경색으로 색상 설정
//        graphics2D.fill(this.shape.getShape()); // 도형의 내부를 채움
    }
}
