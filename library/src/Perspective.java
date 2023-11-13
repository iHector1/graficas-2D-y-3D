import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Perspective extends JFrame {
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;
    private ArrayList<Location> pointsXY = new ArrayList<Location>();
    private ArrayList<Location3D> pointsXYZ = new ArrayList<Location3D>();
    private Figures g;
    private Location3D position;
    public Perspective(){
        color = Color.red;
        setTitle("Proyección Perspectiva");
        setSize(450, 450);
        setLayout(null);
        setVisible(true);

        this.g = new Figures();
        //first square
        pointsXYZ.add(new Location3D(50, 150, 150));
        pointsXYZ.add(new Location3D(150, 150, 150));
        pointsXYZ.add(new Location3D(50, 250, 150));
        pointsXYZ.add(new Location3D(150, 250, 150));
        //second square
        pointsXYZ.add(new Location3D(50, 150, 250));
        pointsXYZ.add(new Location3D(150, 150, 250));
        pointsXYZ.add(new Location3D(50, 250, 250));
        pointsXYZ.add(new Location3D(150, 250, 250));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
    }
    private void putPixel(int x, int y){
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    private void drawCube(){
        position = new Location3D(4,5,-20);
        for (int i = 0; i < pointsXYZ.size(); i++){
            float aux = pointsXYZ.get(i).pointZ - position.pointZ;
            float u = (-position.pointZ) / aux;
            float x = position.pointX + ((pointsXYZ.get(i).pointX - position.pointX)*(u));
            float y = position.pointY + ((pointsXYZ.get(i).pointY - position.pointY)*(u));

            System.out.println("X: " + (int)x + " Y: " + (int)y);
            pointsXY.add(new Location((int) (x * 10)+100, (int) (y * 10)+50));
        }
        for (Location point: pointsXY){
            putPixel(point.pointX, point.pointY);
        }

        ArrayList<Location> pointsAB = g.bresenham(pointsXY.get(0), pointsXY.get(1));
        ArrayList<Location> pointsAC = g.bresenham(pointsXY.get(0), pointsXY.get(2));
        ArrayList<Location> pointsAE = g.bresenham(pointsXY.get(0), pointsXY.get(4));

        ArrayList<Location> pointsBD = g.bresenham(pointsXY.get(1), pointsXY.get(3));
        ArrayList<Location> pointsBF = g.bresenham(pointsXY.get(1), pointsXY.get(5));

        ArrayList<Location> pointsEG = g.bresenham(pointsXY.get(4), pointsXY.get(6));
        ArrayList<Location> pointsEF = g.bresenham(pointsXY.get(4), pointsXY.get(5));

        ArrayList<Location> pointsCG = g.bresenham(pointsXY.get(2), pointsXY.get(6));
        ArrayList<Location> pointsCD = g.bresenham(pointsXY.get(2), pointsXY.get(3));

        ArrayList<Location> pointsHG = g.bresenham(pointsXY.get(7), pointsXY.get(6));
        ArrayList<Location> pointsHF = g.bresenham(pointsXY.get(7), pointsXY.get(5));
        ArrayList<Location> pointsHD = g.bresenham(pointsXY.get(7), pointsXY.get(3));

        ArrayList<Location> points = pointsAB;
        points.addAll(pointsAC);
        points.addAll(pointsAE);
        points.addAll(pointsBD);
        points.addAll(pointsBF);
        points.addAll(pointsEG);
        points.addAll(pointsEF);
        points.addAll(pointsCG);
        points.addAll(pointsCD);
        points.addAll(pointsHG);
        points.addAll(pointsHF);
        points.addAll(pointsHD);

        for (Location point: points){
            putPixel(point.pointX, point.pointY);
        }

    }

    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        drawCube();
    }

    public static void main(String[] args) {
        new Perspective();
    }

}
