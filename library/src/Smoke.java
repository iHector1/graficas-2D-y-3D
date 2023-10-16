import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Smoke extends JFrame {

    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;
    private ArrayList<Location> locations;
    private Figures g;

    public Smoke(){
        this.g = new Figures();
        color = Color.BLUE;
        setTitle("Smoke ");
        setSize(900, 450);
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
        this.drawSmoke();
    }
    private void drawSmoke(){
        ArrayList<Location> pointsXY = new ArrayList<>();
        int x;
        for (int y = 0; y > -360; y--)
        {
            double radian = Math.PI/180 *(y*4);
            x = (int) (Math.cos(radian) * -y);
            putPixel(x + 400, y + 400);
            pointsXY.add(new Location(x + 400, y + 400));
        }
        drawLines(pointsXY);
    }
    private void putPixel(int x,int y){
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public static void main(String[] args) {
        new Smoke();
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
