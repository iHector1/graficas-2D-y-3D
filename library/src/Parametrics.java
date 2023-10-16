import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Parametrics extends JFrame {

    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;
    private ArrayList<Location> locations;
    private Figures g;
    private int points;

    public Parametrics(int points) {
        this.g = new Figures();
        color = Color.BLUE;
        setTitle("Parametricas " + points);
        setSize(900, 450);
        setLayout(null);
        setVisible(true);
        this.points = points;
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        this.drawSmoke();
    }

    private void drawSmoke() {
        ArrayList<Location> pointsXY = new ArrayList<>();
        for (double t = 0; t <=this.points; t++) {
            double x = (t - 3 * Math.sin(t) )*10;
            double y = (4 - 3 * -Math.cos(t) )*10;
            putPixel((int) x+100, (int) y+100);
            pointsXY.add(new Location((int) x+100, (int) y+100));
        }
        drawLines(pointsXY);
    }

    private void putPixel(int x, int y) {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public static void main(String[] args) {
        new Parametrics(50);
    }

    private void drawLines(ArrayList<Location> points) {
        for (int i = 0; i < points.size() - 1; i++) {
            ArrayList<Location> locations = g.bresenham(points.get(i), points.get(i + 1));
            drawPoints(locations);
        }
    }

    public void drawPoints(ArrayList<Location> locations) {
        for (Location point : locations) {
            System.out.println((point.pointX) + " , " + (point.pointY));
            putPixel(point.pointX, point.pointY);
        }
    }
}