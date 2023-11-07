import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Espirall extends JFrame {
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    private ArrayList<Location> pointsXY = new ArrayList<Location>();
    private Location3D vector;
    private Figures g;
    public Espirall(){
        color = Color.red;
        setTitle("Proyección paralela oblicua");
        setSize(450, 450);
        setLayout(null);
        setVisible(true);

        this.g = new Figures();

        vector = new Location3D(1, 11, 37);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void putPixel(int x, int y){
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    private void drawCube(){
        double step = (8 * Math.PI) / 100;

        for (int i = 0; i <=100; i++){
            double u =  (double) (i) / vector.pointZ;
            double x =(double) (Math.cos(i) + (vector.pointX * u))*13;
            double y = (double) (Math.sin(i)+ (vector.pointY * u))*9;
            System.out.println("x: "+x+" y: "+y);
            pointsXY.add(new Location((int) x+200, (int) y+50));
            step+=step;
        }
        for (Location point: pointsXY){
            putPixel(point.pointX, point.pointY);
        }
        this.lines(pointsXY);
    }
    private void lines(ArrayList<Location> points){
        for (int i = 0; i < points.size()-1; i++)
        {
            ArrayList<Location> locations = g.bresenham(points.get(i),points.get(i+1));
            drawPoints(locations);
        }
    }
    public void drawPoints(ArrayList<Location> locations){
        for (Location point : locations){
            System.out.println((point.pointX)+" , "+(point.pointY));
            putPixel(point.pointX, point.pointY);
        }
    }
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        drawCube();
    }

    public static void main(String[] args) {
        new Espirall();
    }
}
