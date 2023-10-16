import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Flower extends JFrame {

    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;
    private ArrayList<Location> locations;
    private Figures g;
    public Flower(){
        this.g = new Figures();
        color = Color.RED;
        setTitle("Flower ");
        setSize(900, 500);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        this.drawFlower();
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    private void drawFlower(){
        ArrayList<Location> location = new ArrayList<>();
        int x, y;
        for (int t = 0; t < 360; t++)
        {
            double radian = Math.PI/180*t;
            double doublex = (Math.cos(radian) + (.5 * Math.cos(7 * radian)) + (.33 * Math.sin(17 * radian))) * 100;
            double doubley = (Math.sin(radian) + (.5 * Math.sin(7 * radian)) + (.33 * Math.cos(17 * radian))) * 100;

            x = (int) doublex;
            y = (int) doubley;
            putPixel(x + 300, y + 300);
            location.add(new Location(x + 300, y + 300));
        }
        lines(location);
        ArrayList<Location> lastPoint = new ArrayList<>();
        lastPoint.add(location.get(0));
        lastPoint.add(location.get(location.size()-1));
        lines(lastPoint);
    }
    private void putPixel(int x,int y){
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public static void main(String[] args) {
        new Flower();
    }
    private void lines(ArrayList<Location> points){
        for (int i = 0; i < points.size()-1; i++){
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
}
