import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TriangleRhombus extends JFrame {

    private BufferedImage buffer;
    private Graphics graphics;
    private ArrayList<Location> locations;
    private Figures g;

    public TriangleRhombus(){
        this.g = new Figures();
        setTitle("Triangulo y rombo");
        setSize(700, 600);
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
        // Crear un triángulo
        locations = g.triangle(new Location(90, 50), new Location(30, 140), new Location(145, 150));
        paintPoints(Color.red, locations);
        paintPoints(locations);
        // Crear un rombo
        locations = g.rhombus(new Location(300, 100), new Location(500, 300));
        paintPoints(Color.black, locations);
        paintPoints(locations);
    }
    private void paintPoints(ArrayList<Location> locations) {
        int[][] points = new int[locations.size()][2];
        for (int i = 0; i < locations.size(); i++) {
            points[i][0] = locations.get(i).pointX;
            points[i][1] = locations.get(i).pointY;
        }
        fillFigure(points);
    }
    private void fillFigure(int[][] points) {
        int n = points.length;
        int startX = Integer.MAX_VALUE;
        int startY = Integer.MAX_VALUE;
        int endX = Integer.MIN_VALUE;
        int endY = Integer.MIN_VALUE;

        // Find the bounding box of the rotated figure
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            startX = Math.min(startX, x);
            startY = Math.min(startY, y);
            endX = Math.max(endX, x);
            endY = Math.max(endY, y);
        }

        int direction = 0; // 0: right, 1: down, 2: left, 3: up
        int x = startX;
        int y = startY;

        while (startX <= endX && startY <= endY) {
            // Paint pixels in the current direction
            while (x >= startX && x <= endX && y >= startY && y <= endY) {
                if (isInsidePolygon(x, y, points)) {
                    putPixel(x, y, Color.red);
                }
                // Move to the next pixel in the current direction
                switch (direction) {
                    case 0:
                        x++;
                        break;
                    case 1:
                        y++;
                        break;
                    case 2:
                        x--;
                        break;
                    case 3:
                        y--;
                        break;
                }
            }

            // Update the bounding box based on the current direction
            switch (direction) {
                case 0:
                    startY++;
                    x--;
                    y++;
                    break;
                case 1:
                    endX--;
                    x--;
                    y--;
                    break;
                case 2:
                    endY--;
                    x++;
                    y--;
                    break;
                case 3:
                    startX++;
                    x++;
                    y++;
                    break;
            }

            // Change direction (right -> down -> left -> up)
            direction = (direction + 1) % 4;
        }
    }
    private boolean isInsidePolygon(int x, int y, int[][] points) {
        int n = points.length;
        boolean isInside = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            int xi = points[i][0];
            int yi = points[i][1];
            int xj = points[j][0];
            int yj = points[j][1];

            if (((yi > y) != (yj > y)) && (x < (xj - xi) * (y - yi) / (yj - yi) + xi)) {
                isInside = !isInside;
            }
        }

        return isInside;
    }
    private void paintPoints(Color color, ArrayList<Location> locations) {
        for (Location location: locations)
                putPixel(location.pointX,location.pointY,color);
    }

    private void putPixel(int x,int y,Color color){
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public static void main(String[] args) {
        new TriangleRhombus();
    }

}
