import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Curve extends JFrame {
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;
    private ArrayList<Location> locations;
    private Figures g;
    private int points;
    public Curve(int points){
        this.g = new Figures();
        color = Color.BLUE;
        setTitle("Lineas");
        setSize(500,500);
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
        this.parable();
    }
    private void parable(){
        ArrayList<Location> pointsXY = new ArrayList<>();
        int y;
        for (int x = 0; x < 180; x+=(180/points))
        {
            double radian = Math.PI/180 * x;
            y = (int) (Math.sin(radian) * -100);
            putPixel(x + 150, y + 250);
            pointsXY.add(new Location(x + 150, y + 250));
        }
        lines(pointsXY);
    }
    private void putPixel(int x,int y){
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public static void main(String[] args) {
        new Curve(8);
        new Curve(100);
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

}
