import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sun extends JFrame {

    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;
    private ArrayList<Location> locations;
    private Figures g;
    private int points;
    public Sun(int points){
        this.g = new Figures();
        color = Color.RED;
        setTitle("Sol "+points);
        setSize(700, 600);
        setLayout(null);
        setVisible(true);
        this.points = points;
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        this.drawFlower();
        graphics.drawImage(buffer, 0, 0, this);
    }
    private void drawFlower(){
        ArrayList<Location>  location= new ArrayList<>();
        int x, y;
        for (int t = 0; t < 2520; t++){
            double radian = Math.PI/180 * t;
            double doublex = (17 * Math.cos(radian) + (7 * Math.cos(2.4286 * radian))) * 10;
            double doubley = (17 * Math.sin(radian) - (7 * Math.sin(2.4286 * radian))) * 10;

            x = (int) doublex;
            y = (int) doubley;
            putPixel(x + 300, y + 300);
            location.add(new Location(x + 300, y + 300));
        }
        drawLines(location);
    }
    private void putPixel(int x,int y){
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public static void main(String[] args) {
        new Sun(100);
    }
    private void drawLines(ArrayList<Location> points){
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
}
