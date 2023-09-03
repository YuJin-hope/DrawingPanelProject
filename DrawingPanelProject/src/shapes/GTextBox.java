package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

public class GTextBox extends GShape {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int px, py;
    private String text;
    private Font font;
    private int fontSize;

    public GTextBox() {
        text = "";
        font = new Font("Arial", Font.PLAIN, 12);
        fontSize = 25;
        shape = new Rectangle();
    }

    @Override
    public GShape clone() {
        GTextBox textBox = new GTextBox();
        textBox.text = this.text;
        textBox.font = this.font;
        textBox.fontSize = this.fontSize;
        textBox.shape = new Rectangle(this.shape.getBounds());
        return textBox;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFont(String fontName, int fontSize) {
        this.font = new Font(fontName, Font.PLAIN, fontSize);
        this.fontSize = fontSize;
    }

    @Override
    public void setShape(int x1, int y1, int x2, int y2) {
        ((Rectangle) shape).setBounds(x1, y1, x2 - x1, y2 - y1);
    }

    @Override
    public void resizePoint(int x, int y) {
        Rectangle rectangle = shape.getBounds();
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
        shape = rectangle;
        this.px = x;
        this.py = y;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Rectangle textBoxShape = (Rectangle) shape.getBounds();

        // text box
        graphics2D.setColor(Color.white);
        graphics2D.draw(textBoxShape);

        // box 안 text
        graphics2D.setColor(Color.black);
        graphics2D.setFont(font);
        FontMetrics fm = graphics2D.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        int centerX = textBoxShape.x + (textBoxShape.width - textWidth) / 2;
        int centerY = textBoxShape.y + (textBoxShape.height - textHeight) / 2 + fm.getAscent();
        graphics2D.drawString(text, centerX, centerY);

        // dashed border 수정
        Stroke dashedStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 6 }, 2);
        graphics2D.setStroke(dashedStroke);
        graphics2D.setColor(Color.darkGray);
        graphics2D.drawRect((int) textBoxShape.getX(), (int) textBoxShape.getY(), (int) textBoxShape.getWidth(),
                (int) textBoxShape.getHeight());
    }
}
