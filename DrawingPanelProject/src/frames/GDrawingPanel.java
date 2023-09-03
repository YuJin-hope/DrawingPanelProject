package frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.GConstants;
import main.GConstants.EAnchors;
import main.GConstants.EShape;
import main.GConstants.EUserAction;
import shapes.GShape;
import shapes.GTextBox;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
import transformer.GRotator;
import transformer.GSelector;
import transformer.GShear;
import transformer.GTransformer;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private enum EDrawingEvent {
		eStart, eMoving, eCont, eEnd
	}

	private enum EDrawingState {
		eIdle, eTransforming
	}

	private EDrawingState eDrawingState;
	private Vector<GShape> shapes;
	private GShape currentShape;
	private Cursor cursor;

	// association
	private GToolBar toolBar;

	public void setToolBar(GToolBar toolBar) {
		this.toolBar = toolBar;
	}

	private GTransformer transformer;

	public GDrawingPanel() {
		super();
		this.eDrawingState = EDrawingState.eIdle;
		this.shapes = new Vector<GShape>();
		this.currentShape = null;

		this.setBackground(Color.WHITE);
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
		
		JMenuItem itemLoad = new JMenuItem("불러오기");
		itemLoad.addActionListener(e -> {
		    String fileName = "shapes.dat"; // 원하는 파일 이름을 설정하세요
		    GShape[] loadedShapes = ShapeFileHandler.loadShapesFromFile(fileName);
		    
		    if (loadedShapes != null) {
		        for (GShape shape : loadedShapes) {
		            shapes.add(shape);
		        }
		        repaint();
		    }
		});
		this.add(itemLoad);
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics2D g2 = (Graphics2D) graphics;
		for (GShape shape : this.shapes) {
			shape.draw(g2);
		}
	}

	private EAnchors onShape(int x, int y) {
		for (GShape gShape : this.shapes) {
			EAnchors eAnchor = gShape.onShape(x, y);
			if (eAnchor != null) {
				this.currentShape = gShape;
				return eAnchor;
			}
		}
		return null;
	}

	private void clearSelection() { // selection 지우기?
	}

	public void initTransforming(int x, int y) { // 어떤 트랜스포머를 쓸지를 여기서 판단함
		Graphics2D graphics2D = (Graphics2D) this.getGraphics();
		
		if (this.toolBar.getESelectedShape() == EShape.eSelect) {
			EAnchors eSelectedAnchor = onShape(x, y); // anchor -> eSelectedAnchor
			if (eSelectedAnchor == null) {
				this.clearSelection();
				this.currentShape = this.toolBar.getESelectedShape().getGShape().clone();
				this.transformer = new GSelector(this.currentShape);
				this.transformer.initTransform(x, y, graphics2D);
			} else {
				switch (eSelectedAnchor) {
				case RR: // rotator
					System.out.println("rotator");
					this.transformer = new GRotator(this.currentShape);
					this.transformer.initTransform(x, y, graphics2D);
					break;
				case NW, NN, EE, SE, SS, SW, WW: // resizer
					System.out.println("resizer");
					this.transformer = new GResizer(this.currentShape);
					this.transformer.initTransform(x, y, graphics2D);
					break;
				case NE: // shear
					System.out.println("shear");
					this.transformer = new GShear(this.currentShape);
					this.transformer.initTransform(x, y, graphics2D);
					break;
				default: // default -> move
					System.out.println("mover");
					this.transformer = new GMover(this.currentShape);
					this.transformer.initTransform(x, y, graphics2D);
					break;
				}
			}
		} else {
			// draw
			if (this.toolBar.getESelectedShape().getEUserAction() == EUserAction.e2Point) {
				this.currentShape = this.toolBar.getESelectedShape().getGShape().clone();
				this.transformer = new GDrawer(this.currentShape);
				this.transformer.initTransform(x, y, graphics2D);
			} else if(this.toolBar.getESelectedShape().getEUserAction() == EUserAction.eNPoint) {
				// shapes.add(currentShape);
				this.currentShape = this.toolBar.getESelectedShape().getGShape().clone();
				this.transformer = new GDrawer(this.currentShape);
				this.transformer.initTransform(x, y, graphics2D);
			}
		}
	}

	public void keepTransforming(int x, int y) { // 그리기 시작
		Graphics2D graphics2D = (Graphics2D) this.getGraphics();
		graphics2D.setXORMode(this.getBackground());
		this.transformer.keepTransform(x, y, graphics2D);
	}

	public void continueTransforming(int x, int y) { // 계속 그리기 (polygon)
		//if (this.transformer != null) {
			Graphics2D graphics2D = (Graphics2D) this.getGraphics();
			currentShape.addPoint(x, y);
			this.transformer.continueTransform(x, y, graphics2D);
		//}
	}

	public void finalizeTransforming(int x, int y) {
		if (this.transformer != null) {
			Graphics2D graphics2D = (Graphics2D) this.getGraphics();
			this.transformer.finalizeTransform(x, y, graphics2D, this.shapes);
		}
		if (this.toolBar.getESelectedShape() == EShape.eTextbox) {
			GTextBox textBox = (GTextBox) this.currentShape;
			JTextField textField = new JTextField();
			this.add(textField);
			textField.requestFocus(); // 텍스트 필드에 포커스 설정

			// 엔터 키를 누르면 입력 완료 처리
			textField.addActionListener(e -> {
				String inputText = textField.getText();
				if (inputText != null && !inputText.isEmpty()) {
					textBox.setText(inputText);
				}
				//this.remove(textField); // 텍스트 필드 제거
				this.revalidate();
				this.repaint();
			});
		}
		this.transformer = null; // 트랜스포머 초기화
		this.toolBar.resetESelectedShape();
	}

	private void changeCursors(int x, int y) { // 위에 겹치는 코드랑 둘 중 하나만 남기는 방향으로 /..
		// if 말고, 밑의 코드는 매번 확인하지 않아도 됨, 이니멀라이션??? 애니멀메이션? -> 콘스탄트로 옮기기, 커서 하나 집어넣어라 enum
		// EAnchors 로
		EAnchors eAnchor = this.onShape(x, y);
		// Cursor cursor = null;
		if (eAnchor == null) {
			cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		} else {
			switch (eAnchor) {
			case MM: // mover
				cursor = new Cursor(Cursor.CROSSHAIR_CURSOR); // 십자가 커서
				break;
			case RR:
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image cursorImage = toolkit.getImage("src/rotateCursor.png");
				Point RRSpot = new Point(0, 0);
				cursor = toolkit.createCustomCursor(cursorImage, RRSpot, "Custom Cursor");
				break;
			case NW:
				cursor = new Cursor(Cursor.NW_RESIZE_CURSOR);
				break;
			case NN:
				cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
				break;
			case NE:
				cursor = new Cursor(Cursor.NE_RESIZE_CURSOR);
				break;
			case EE:
				cursor = new Cursor(Cursor.E_RESIZE_CURSOR);
				break;
			case SE:
				cursor = new Cursor(Cursor.SE_RESIZE_CURSOR);
				break;
			case SS:
				cursor = new Cursor(Cursor.S_RESIZE_CURSOR);
				break;
			case SW:
				cursor = new Cursor(Cursor.SW_RESIZE_CURSOR); // 동그라미 커서 만들기
				break;
			case WW:
				cursor = new Cursor(Cursor.W_RESIZE_CURSOR); // 동그라미 커서 만들기
				break;
			default:
				cursor = new Cursor(Cursor.DEFAULT_CURSOR);
				break;
			}
		}
		this.setCursor(cursor);
	}

	private class MouseEventHandler implements MouseListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				mouse1Clicked(e);

			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);
			}
		}

		private void mouse1Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				if (toolBar.getESelectedShape().getEUserAction() == GConstants.EUserAction.eNPoint) { // 2포인트로 그리는 그림이
					initTransforming(e.getX(), e.getY());
					System.out.println("1click");
					eDrawingState = EDrawingState.eTransforming;
				}
			} else if (eDrawingState == EDrawingState.eTransforming) {
				if (toolBar.getESelectedShape().getEUserAction() == EUserAction.eNPoint) {
					System.out.println("1clickcontinue");
					continueTransforming(e.getX(), e.getY());
					System.out.println("1clickcontinue");
				}
			}
		}

		private void mouse2Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eTransforming) {
				if (toolBar.getESelectedShape().getEUserAction() == EUserAction.eNPoint) {
					System.out.println("2click");
					finalizeTransforming(e.getX(), e.getY());
					eDrawingState = EDrawingState.eIdle;
				}
			}
		}

		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.eTransforming) {
				if (toolBar.getESelectedShape().getEUserAction() == EUserAction.eNPoint) {
					keepTransforming(e.getX(), e.getY());
					System.out.println("move");
				}
			} else if (eDrawingState == EDrawingState.eIdle) {
				changeCursors(e.getX(), e.getY());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				if (toolBar.getESelectedShape().getEUserAction() == EUserAction.e2Point) {
					initTransforming(e.getX(), e.getY());
					eDrawingState = EDrawingState.eTransforming;
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.eTransforming) {
				keepTransforming(e.getX(), e.getY()); // drawing, move, resize
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (toolBar.getESelectedShape().getEUserAction() == EUserAction.e2Point) {
			if (eDrawingState == EDrawingState.eTransforming) {
				finalizeTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}
}