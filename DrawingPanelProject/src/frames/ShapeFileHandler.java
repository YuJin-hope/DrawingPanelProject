package frames;

import java.io.*;

import shapes.GShape;

public class ShapeFileHandler {
    public static void saveShapesToFile(String fileName, GShape[] shapes) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(shapes);
            System.out.println("도형들이 파일에 저장되었습니다: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GShape[] loadShapesFromFile(String fileName) {
        GShape[] shapes = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            shapes = (GShape[]) inputStream.readObject();
            System.out.println("도형들이 파일에서 불러와졌습니다: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return shapes;
    }
}
