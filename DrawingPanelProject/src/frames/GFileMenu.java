package frames;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import shapes.GLine;
import shapes.GOval;
import shapes.GRectangle;
import shapes.GShape;

public class GFileMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public GFileMenu(String title) {
        super(title);

        JMenuItem itemNew = new JMenuItem("새로 만들기");
        this.add(itemNew);

        JMenuItem itemSave = new JMenuItem("저장");
        itemSave.addActionListener(e -> {
            String fileName = "shapes.dat"; // 저장할 파일 이름
            GShape[] shapes = new GShape[2]; // 예시로 GShape 배열을 길이 2로 초기화

            // 도형을 생성하고 shapes 배열에 추가
            GShape shape1 = new GRectangle();
            //shape1.setShape(10, 10, 100, 50); // 좌표 (10, 10)에서 너비 100, 높이 50인 직사각형 설정
            shapes[0] = shape1;

            GShape shape2 = new GLine();
            //shape2.setShape(10, 10, 100, 50); // 다른 도형의 좌표와 크기 설정
            shapes[1] = shape2;

            ShapeFileHandler.saveShapesToFile(fileName, shapes);

        });
        this.add(itemSave);

        JMenuItem itemLoad = new JMenuItem("불러오기");
        itemLoad.addActionListener(e -> {
            String fileName = "shapes.dat"; // 원하는 파일 이름을 설정하세요
            GShape[] shapes = ShapeFileHandler.loadShapesFromFile(fileName);
            // 불러온 도형들을 캔버스나 원하는 곳에 표시합니다
        });
        this.add(itemLoad);
    }
}



//package frames;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//
//import javax.swing.JMenu;
//import javax.swing.JMenuItem;
//
//public class GFileMenu extends JMenu {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	@SuppressWarnings("unused")
//	private JMenuItem itemNew;
//	private File file; // 최대한 바깥으로 빼놓는 게 좋음, 상속 받아 쓰기 위해서, 재사용
//
//
//	public GFileMenu(String title) {
//		super(title);
//
//		JMenuItem itemNew = new JMenuItem("new");
//		this.add(itemNew);
//
//	}
//
//	public Object readObject(File file) {
//		this.file = file;
//
//		try {
//			FileInputStream fileInputStream = new FileInputStream(this.file);
//			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//
//			Object object = objectInputStream.readObject();
//
//			objectInputStream.close();
//
//			return object;
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//
//	public void saveObject(Object object, File file) {
//		this.file = file;
//
//		try {
//			FileOutputStream fileOutputStream = new FileOutputStream(this.file);
//			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//
//			objectOutputStream.writeObject(object);
//			objectOutputStream.close();
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}