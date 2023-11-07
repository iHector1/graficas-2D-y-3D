import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Espirall extends JFrame {
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    private ArrayList<Location> pointsXY = new ArrayList<Location>();
    private ArrayList<Location3D> pointsXYZ = new ArrayList<Location3D>();
    private Location3D vector;
    private Figures g;
    public Espirall(){
        color = Color.red;
        setTitle("Proyección paralela oblicua");
        setSize(450, 450);
        setLayout(null);
        setVisible(true);

        this.g = new Figures();

        vector = new Location3D(8,7,20);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
    }
    private void putPixel(int x, int y){
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    private void drawCube(){
        double step = (8 * Math.PI) / 100;
        for (int i = 0; i <=100; i++){
            float u =  (float) (pointsXYZ.get(i).pointZ) / vector.pointZ;
            float x = pointsXYZ.get(i).pointX + (vector.pointX * u);
            float y = pointsXYZ.get(i).pointY + (vector.pointY * u);

            pointsXY.add(new Location((int) x, (int) y));
        }
        for (Location point: pointsXY){
            putPixel(point.pointX, point.pointY);
        }

/*
        for (Location point: points){
            putPixel(point.pointX, point.pointY);
        }*/

    }

    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        drawCube();
    }

    public static void main(String[] args) {
        new ParallelProyection();
    }
}
